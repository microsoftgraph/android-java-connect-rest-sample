/*
 *  Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license. See full license at the bottom of this file.
 */

package com.microsoft.office365.connectunified;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.mime.TypedString;


public interface UnifiedAPIService {

    //See https://msdn.microsoft.com/office/office365/HowTo/examples-of-office-365-unified-api-calls#msg_eg_manage_me_send_mail_message_draft
    //for more information on creating and sending mail using the Unified API.

    @POST("/me/messages")
    void createDraftMail(
            @Header("Content-type") String contentTypeHeader,
            @Body TypedString mail,
            Callback<MailVO> callback);

    @POST("/me/messages/{messageID}")
    void SendDraftMail(
            @Path("messageID") String messageID,
            Callback<MailVO> callback);

    @POST("/me/sendMail")
    void sendMail(
            @Header("Content-type") String contentTypeHeader,
            @Body TypedString mail,
            Callback<MailVO> callback);


}
// *********************************************************
//
// O365-Android-Unified-API-Connect, https://github.com/OfficeDev/O365-Android-Unified-API-Connect.git
//
// Copyright (c) Microsoft Corporation
// All rights reserved.
//
// MIT License:
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
// *********************************************************

