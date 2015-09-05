/*
 *  Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license. See full license at the bottom of this file.
 */
package com.microsoft.office365.connectunified;

import com.google.gson.Gson;
import com.microsoft.office365.connectunified.vo.BodyVO;
import com.microsoft.office365.connectunified.vo.EmailAddressVO;
import com.microsoft.office365.connectunified.vo.MessageVO;
import com.microsoft.office365.connectunified.vo.MessageWrapper;
import com.microsoft.office365.connectunified.vo.ToRecipientsVO;

import retrofit.Callback;
import retrofit.mime.TypedString;


/**
 * Handles the creation of the message and contacting the
 * mail service to send the message. The app must have
 * connected to Office 365 and discovered the mail service
 * endpoints before using the createDraftMail method.
 */
public class UnifiedAPIController {

    private static UnifiedAPIController INSTANCE;
    private RESTHelper mRESTHelper;
    private UnifiedAPIService mUnifiedAPIService;

    public static synchronized UnifiedAPIController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UnifiedAPIController();
        }
        return INSTANCE;
    }

    private UnifiedAPIController() {
        mRESTHelper = new RESTHelper(AuthenticationManager.getInstance().getAccessToken());
    }


    /**
     * Sends an email message using the Unified API on Office 365. The mail is sent
     * from the address of the signed in user.
     *
     * @param emailAddress The recipient email address.
     * @param subject      The subject to use in the mail message.
     * @param body         The body of the message.
     * @param callback     UI callback to be invoked by Retrofit call when
     *                     operation completed
     */
    public void sendMail(
            final String emailAddress,
            final String subject,
            final String body,
            Callback<Void> callback) {
        ensureService();
        // Use the Unified API service on Office 365 to create the message.
        mUnifiedAPIService.sendMail(
                "application/json",
                createMailPayload(
                        subject,
                        body,
                        emailAddress),
                callback);
    }


    private TypedString createMailPayload(
            String subject,
            String body,
            String address) {
        EmailAddressVO emailAddressVO = new EmailAddressVO();
        emailAddressVO.mAddress = address;

        ToRecipientsVO toRecipientsVO = new ToRecipientsVO();
        toRecipientsVO.emailAddress = emailAddressVO;

        BodyVO bodyVO = new BodyVO();
        bodyVO.mContentType = "HTML";
        bodyVO.mContent = body;

        MessageVO sampleMsg = new MessageVO();
        sampleMsg.mSubject = subject;
        sampleMsg.mBody = bodyVO;
        sampleMsg.mToRecipients = new ToRecipientsVO[]{toRecipientsVO};

        MessageWrapper wrapper = new MessageWrapper(sampleMsg);

        TypedString typedEmail = new TypedString(new Gson().toJson(wrapper));

        return typedEmail;
    }

    //Creates a unified endpoint service interface if it does not exist.
    private void ensureService() {
        if (mUnifiedAPIService == null) {
            mUnifiedAPIService = mRESTHelper
                    .getRestAdapter()
                    .create(UnifiedAPIService.class);
        }
    }
}

// *********************************************************
//
// O365-Android-Unified-API-Connect, https://github.com/OfficeDev/O365-Android-Unified-API-Connect
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
