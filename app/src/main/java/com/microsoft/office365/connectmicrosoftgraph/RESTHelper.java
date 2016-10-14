/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.util.Log;

import com.lnikkila.oidc.security.UserNotAuthenticatedWrapperException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RESTHelper {
    private static final String TAG = "RESTHelper";

    /**
     * Returns a retrofit rest adaptor class. The adaptor is created in calling code.
     *
     * @return A new RestAdapter instance.
     */
    public Retrofit getRetrofit() {
        //This method catches outgoing REST calls and injects the Authorization and host headers before
        //sending to REST endpoint
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                try {
                    final String token = AuthenticationManager.getInstance().getAccessToken();
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            // This header has been added to identify this sample in the Microsoft Graph service.
                            // If you're using this code for your project please remove the following line.
                            .addHeader("SampleID", "android-java-connect-rest-sample")
                            .build();

                    Response response = chain.proceed(request);
                    return response;
                } catch (AuthenticatorException | IOException | UserNotAuthenticatedWrapperException | OperationCanceledException e) {
                    Log.e(TAG, e.getMessage());
                    return null;
                }
            }
        };

        return getRetrofit(interceptor);
    }

    public Retrofit getRetrofit(Interceptor interceptor) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .build();

        //Sets required properties in rest adaptor class before it is created.
        return new Retrofit.Builder()
                .baseUrl(Constants.MICROSOFT_GRAPH_API_ENDPOINT_RESOURCE_ID)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
