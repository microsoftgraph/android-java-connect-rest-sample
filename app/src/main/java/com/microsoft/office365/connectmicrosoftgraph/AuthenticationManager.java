/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.lnikkila.oidc.OIDCAccountManager;
import com.lnikkila.oidc.OIDCRequestManager;
import com.lnikkila.oidc.security.UserNotAuthenticatedWrapperException;

import java.io.IOException;

/**
 * Handles setup of ADAL Dependency Resolver for use in API clients.
 */

public class AuthenticationManager {

    private static final String TAG = "AuthenticationManager";
    private static AuthenticationManager INSTANCE;

    private OIDCAccountManager mAccountManager;

    private Activity mContextActivity;

    private AuthenticationManager() {
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
    public String getAccessToken() throws AuthenticatorException, IOException, OperationCanceledException, UserNotAuthenticatedWrapperException {
        return getAccountManager().getAccessToken(getAccountManager().getAccounts()[0], null);
    }

    /**
     *
     * @param authenticationCallback The callback to notify when the processing is finished.
     */
    public void connect(final AuthenticationCallback<String> authenticationCallback) {
        switch (getAccountManager().getAccounts().length) {
            // No account has been created, let's create one now
            case 0:
                getAccountManager().createAccount(mContextActivity, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> futureManager) {
                        // Unless the account creation was cancelled, try logging in again
                        // after the account has been created.
                        if (!futureManager.isCancelled()) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Account account = getAccountManager().getAccounts()[0];
                                    try {
                                        authenticationCallback.onSuccess(getAccountManager().getIdToken(account.name, null));
                                    } catch (AuthenticatorException | UserNotAuthenticatedWrapperException | OperationCanceledException | IOException e) {
                                        authenticationCallback.onError(e);
                                    }
                                }
                            }).start();
                        } else {
                            authenticationCallback.onError(new AuthenticatorException("Flow was canceled"));
                        }
                    }
                });
                break;
            case 1:
                // if we have an user endpoint we try to get userinfo with the receive token
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Account account = getAccountManager().getAccounts()[0];
                        try {
                            authenticationCallback.onSuccess(getAccountManager().getIdToken(account.name, null));
                        } catch (AuthenticatorException | UserNotAuthenticatedWrapperException | OperationCanceledException | IOException e) {
                            authenticationCallback.onError(e);
                        }
                    }
                }).start();
                break;
        }
    }

    /**
     * Gets Android AccountManager.
     *
     * @return the AccountManager, if successful.
     */
    public OIDCAccountManager getAccountManager() {
        if (mAccountManager == null) {
            mAccountManager = new OIDCAccountManager(mContextActivity);
            SharedPreferences sharedPreferences = mContextActivity.getSharedPreferences("oidc_clientconf", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("oidc_loadfromprefs", true);
            editor.putBoolean("oidc_oauth2only", false);
            editor.putString("oidc_clientId", Constants.CLIENT_ID);
            editor.putString("oidc_redirectUrl", Constants.REDIRECT_URI);
            editor.putString("oidc_scopes", Constants.SCOPES);
            editor.putString("oidc_flowType", OIDCRequestManager.Flows.Code.name());

            editor.apply();
        }
        return mAccountManager;
    }

    /**
     * Disconnects the app from Office 365 by clearing the token cache, setting the client objects
     * to null, and removing the user id from shred preferences.
     */
    public void disconnect() {
        getAccountManager().removeAccount(getAccountManager().getAccounts()[0]);
        // Reset the AuthenticationManager object
        AuthenticationManager.resetInstance();
    }
}