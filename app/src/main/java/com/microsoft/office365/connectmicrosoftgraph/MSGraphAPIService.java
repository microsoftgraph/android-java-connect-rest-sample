/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

import com.microsoft.office365.connectmicrosoftgraph.vo.MessageWrapper;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;


public interface MSGraphAPIService {
    @POST("/me/microsoft.graph.sendmail")
    void sendMail(
            @Header("Content-type") String contentTypeHeader,
            @Body MessageWrapper mail,
            Callback<Void> callback);
}