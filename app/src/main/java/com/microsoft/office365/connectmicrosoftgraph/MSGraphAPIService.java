/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

import com.microsoft.office365.connectmicrosoftgraph.vo.MessageWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface MSGraphAPIService {
    @POST("/v1.0/me/microsoft.graph.sendmail")
    Call<Void> sendMail(
            @Header("Content-type") String contentTypeHeader,
            @Body MessageWrapper mail);
}