/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.lnikkila.oidc.OIDCAccountManager;
import com.lnikkila.oidc.OIDCRequestManager;
import com.lnikkila.oidc.security.UserNotAuthenticatedWrapperException;
import com.microsoft.aad.adal.ADALError;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationException;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.AuthenticationResult.AuthenticationStatus;
import com.microsoft.aad.adal.AuthenticationSettings;
import com.microsoft.aad.adal.PromptBehavior;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Handles setup of ADAL Dependency Resolver for use in API clients.
 */

public class AuthenticationManager {

    private static final String TAG = "AuthenticationManager";
    private static final String PREFERENCES_FILENAME = "ConnectFile";
    private static final String USER_ID_VAR_NAME = "userId";
    private static final int RENEW_REFRESH_TOKEN = 2016;
    private static AuthenticationManager INSTANCE;

    private OIDCAccountManager mAccountManager;

    static {
        // Devices with API level lower than 18 must setup an encryption key.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2 &&
                AuthenticationSettings.INSTANCE.getSecretKeyData() == null) {
            AuthenticationSettings.INSTANCE.setSecretKey(generateSecretKey());
        }
    }

    private AuthenticationContext mAuthenticationContext;
    private Activity mContextActivity;
    private String mAccessToken;

    private AuthenticationManager() {
    }

    /**
     * Generates an encryption key for devices with API level lower than 18 using the
     * ANDROID_ID value as a seed.
     * In production scenarios, you should come up with your own implementation of this method.
     * Consider that your algorithm must return the same key so it can encrypt/decrypt values
     * successfully.
     *
     * @return The encryption key in a 32 byte long array.
     */
    private static byte[] generateSecretKey() {
        byte[] key = new byte[32];
        byte[] android_id;

        try {
            android_id = Settings.Secure.ANDROID_ID.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "generateSecretKey - " + e.getMessage());
            throw new RuntimeException(e);
        }

        for (int i = 0; i < key.length; i++) {
            key[i] = android_id[i % android_id.length];
        }

        return key;
    }

    public static synchronized AuthenticationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthenticationManager();
        }
        return INSTANCE;
    }

    public static synchronized void resetInstance() {
        INSTANCE = null;
    }

    /**
     * Set the context activity before connecting to the currently active activity.
     *
     * @param contextActivity Currently active activity which can be utilized for interactive
     *                        prompt.
     */
    public void setContextActivity(final Activity contextActivity) {
        mContextActivity = contextActivity;
    }

    /**
     * Returns the access token obtained in authentication
     *
     * @return mAccessToken
     */
    public String getAccessToken() {
        return mAccessToken;
    }


    /**
     * Calls {@link AuthenticationManager#authenticatePrompt(AuthenticationCallback)} if no user id is stored in the shared preferences.
     * Calls {@link AuthenticationManager#authenticateSilent(AuthenticationCallback)} otherwise.
     *
     * @param authenticationCallback The callback to notify when the processing is finished.
     */
    public void connect(final AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        switch (getAccountManager().getAccounts().length) {
            // No account has been created, let's create one now
            case 0:
                getAccountManager().createAccount(mContextActivity, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> futureManager) {
                        // Unless the account creation was cancelled, try logging in again
                        // after the account has been created.
                        if (!futureManager.isCancelled()) {
                            try {
                                mAccessToken = getAccountManager().getAccessToken(getAccountManager().getAccounts()[0], null);
                                Log.i(TAG, mAccessToken);
                            } catch (OperationCanceledException | IOException | AuthenticatorException e) {
                                Log.e(TAG, "Couldn't renew tokens", e);
                            } catch (UserNotAuthenticatedWrapperException e) {
                                Log.e(TAG, "Couldn't renew tokens", e);
                            }
                        }
                    }
                });
                break;

//            // There's just one account, let's use that
//            case 1:
//                // if we have an user endpoint we try to get userinfo with the receive token
//                if (!TextUtils.isEmpty(userInfoEndpoint)) {
//                    new LoginTask().execute(availableAccounts[0]);
//                }
//                break;
//
//            // Multiple accounts, let the user pick one
//            default:
//                String name[] = new String[availableAccounts.length];
//
//                for (int i = 0; i < availableAccounts.length; i++) {
//                    name[i] = availableAccounts[i].name;
//                }
//
//                new AlertDialog.Builder(this)
//                        .setTitle("Choose an account")
//                        .setAdapter(new ArrayAdapter<>(this,
//                                        android.R.layout.simple_list_item_1, name),
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int selectedAccount) {
//                                        selectedAccountIndex = selectedAccount;
//
//                                        // if we have an user endpoint we try to get userinfo with the receive token
//                                        if (!TextUtils.isEmpty(userInfoEndpoint)) {
//                                            new LoginTask().execute(availableAccounts[selectedAccountIndex]);
//                                        }
//                                    }
//                                })
//                        .create()
//                        .show();
        }
    }

    /**
     * Calls acquireTokenSilentAsync with the user id stored in shared preferences.
     * In case of an error, it falls back to {@link AuthenticationManager#authenticatePrompt(AuthenticationCallback)}.
     *
     * @param authenticationCallback The callback to notify when the processing is finished.
     */
    private void authenticateSilent(final AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        getAuthenticationContext().acquireTokenSilentAsync(
                Constants.MICROSOFT_GRAPH_API_ENDPOINT_RESOURCE_ID,
                Constants.CLIENT_ID,
                getUserId(),
                new AuthenticationCallback<AuthenticationResult>() {
                    @Override
                    public void onSuccess(final AuthenticationResult authenticationResult) {
                        if (authenticationResult != null) {
                            if (authenticationResult.getStatus() == AuthenticationStatus.Succeeded) {
                                mAccessToken = authenticationResult.getAccessToken();
                                authenticationCallback.onSuccess(authenticationResult);
                            } else {
                                authenticationCallback.onError(
                                        new Exception(authenticationResult.getErrorDescription()));
                            }
                        } else {
                            // I could not authenticate the user silently,
                            // falling back to prompt the user for credentials.
                            authenticatePrompt(authenticationCallback);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        // I could not authenticate the user silently,
                        // falling back to prompt the user for credentials.
                        authenticatePrompt(authenticationCallback);
                    }
                }
        );
    }

    /**
     * Calls acquireToken to prompt the user for credentials.
     *
     * @param authenticationCallback The callback to notify when the processing is finished.
     */
    private void authenticatePrompt(final AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        getAuthenticationContext().acquireToken(
                mContextActivity,
                Constants.MICROSOFT_GRAPH_API_ENDPOINT_RESOURCE_ID,
                Constants.CLIENT_ID,
                Constants.REDIRECT_URI,
                PromptBehavior.Always,
                new AuthenticationCallback<AuthenticationResult>() {
                    @Override
                    public void onSuccess(final AuthenticationResult authenticationResult) {
                        if (authenticationResult != null) {
                            if (authenticationResult.getStatus() == AuthenticationStatus.Succeeded) {
                                setUserId(authenticationResult.getUserInfo().getUserId());
                                mAccessToken = authenticationResult.getAccessToken();
                                authenticationCallback.onSuccess(authenticationResult);
                            } else {
                                // We need to make sure that there is no data stored with the failed auth
                                AuthenticationManager.getInstance().disconnect();
                                // This condition can happen if user signs in with an MSA account
                                // instead of an Office 365 account
                                authenticationCallback.onError(
                                        new AuthenticationException(
                                                ADALError.AUTH_FAILED,
                                                authenticationResult.getErrorDescription()));
                            }
                        } else {
                            // I could not authenticate the user silently,
                            // falling back to prompt the user for credentials.
                            authenticatePrompt(authenticationCallback);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        // We need to make sure that there is no data stored with the failed auth
                        AuthenticationManager.getInstance().disconnect();
                        authenticationCallback.onError(e);
                    }
                }
        );
    }

    /**
     * Gets authentication context for Azure Active Directory.
     *
     * @return an authentication context, if successful.
     */
    public AuthenticationContext getAuthenticationContext() {
        if (mAuthenticationContext == null) {
            try {
                mAuthenticationContext =
                        new AuthenticationContext(
                                mContextActivity,
                                Constants.AUTHORITY_URL,
                                false
                        );
            } catch (Throwable t) {
                Log.e(TAG, t.toString());
            }
        }
        return mAuthenticationContext;
    }
    public OIDCAccountManager getAccountManager() {
        if (mAccountManager == null) {
            mAccountManager = new OIDCAccountManager(mContextActivity);
            SharedPreferences sharedPreferences = mContextActivity.getSharedPreferences("oidc_clientconf", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("oidc_loadfromprefs", true);
            editor.putBoolean("oidc_oauth2only", true);
            editor.putString("oidc_clientId", Constants.CLIENT_ID);
            editor.putString("oidc_clientSecret", "");
            editor.putString("oidc_redirectUrl", Constants.REDIRECT_URI);
            editor.putString("oidc_scopes", "openid profile User.Read Mail.Send offline_access");
            editor.putString("oidc_flowType", OIDCRequestManager.Flows.Code.name());
            editor.putString("oidc_issuerId", "https://login.microsoftonline.com/" + Constants.CLIENT_ID + "/v2.0");

            editor.apply();
        }
        return mAccountManager;
    }

    /**
     * Disconnects the app from Office 365 by clearing the token cache, setting the client objects
     * to null, and removing the user id from shred preferences.
     */
    public void disconnect() {
        // Clear tokens.
        if (getAuthenticationContext().getCache() != null) {
            getAuthenticationContext().getCache().removeAll();
        }

        mAccessToken = null;
        // Reset the AuthenticationManager object
        AuthenticationManager.resetInstance();

        // Forget the user
        removeUserId();
    }

    private boolean verifyAuthenticationContext() {
        if (null == mContextActivity) {
            Log.e(TAG, "Must set context activity");
            return false;
        }
        return true;
    }

    private SharedPreferences getSharedPreferences() {
        return mContextActivity.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE);
    }

    private boolean isConnected() {
        return getSharedPreferences().contains(USER_ID_VAR_NAME);
    }

    private String getUserId() {
        return getSharedPreferences().getString(USER_ID_VAR_NAME, "");
    }

    private void setUserId(String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(USER_ID_VAR_NAME, value);
        editor.apply();
    }

    private void removeUserId() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(USER_ID_VAR_NAME);
        editor.apply();
    }

    private class LoginTask extends AsyncTask<Account, Void, Map> {
        /**
         * Makes the API request. We could use the OIDCRequestManager.getUserInfo() method, but we'll do it
         * like this to illustrate making generic API requests after we've logged in.
         */
        @Override
        protected Map doInBackground(Account... args) {
            Account account = args[0];
            // See https://github.com/kalemontes/OIDCAndroidLib/issues/4
            try {
                getAccountManager().getAccessToken(account, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            Bundle bundle = future.getResult();
                            Intent launch = (Intent) bundle.get(AccountManager.KEY_INTENT);
                            if (launch != null) {
                                launch.setFlags(0);
                                mContextActivity.startActivityForResult(launch, RENEW_REFRESH_TOKEN);
                            }
                        } catch (OperationCanceledException | IOException | AuthenticatorException e) {
                            Log.e(TAG, "Couldn't extract AuthenticationActivity launch intent", e);
                        }
                    }
                });
            } catch (OperationCanceledException | IOException | AuthenticatorException e) {
                Log.e(TAG, "Couldn't renew tokens", e);
            } catch (UserNotAuthenticatedWrapperException e) {
                //FIXME: we gotta handle this somehow
            }


            return null;
        }
    }
}