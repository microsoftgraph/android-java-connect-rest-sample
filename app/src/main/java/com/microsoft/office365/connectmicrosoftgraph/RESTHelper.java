/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RESTHelper {

    /**
     * Returns a retrofit rest adaptor class. The adaptor is created in calling code.
     *
     * @return A new RestAdapter instance.
     */
    public RestAdapter getRestAdapter() {
        //This method catches outgoing REST calls and injects the Authorization and host headers before
        //sending to REST endpoint
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                final String token = AuthenticationManager.getInstance().getAccessToken();
                if (null != token) {
                    request.addHeader("Authorization", "Bearer " + token);
                }
            }
        };

        //Sets required properties in rest adaptor class before it is created.
        return new RestAdapter.Builder()
                .setEndpoint(Constants.MICROSOFT_GRAPH_API_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(requestInterceptor)
                .build();
    }

}