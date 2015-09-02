/*
 *  Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license. See full license at the bottom of this file.
 */
package com.microsoft.office365.connect;

import android.util.Log;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.outlookservices.BodyType;
import com.microsoft.outlookservices.EmailAddress;
import com.microsoft.outlookservices.ItemBody;
import com.microsoft.outlookservices.Message;
import com.microsoft.outlookservices.Recipient;
import com.microsoft.outlookservices.odata.OutlookClient;
import com.microsoft.services.odata.impl.ADALDependencyResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import retrofit.Callback;


/**
 * Handles the creation of the message and contacting the
 * mail service to send the message. The app must have
 * connected to Office 365 and discovered the mail service
 * endpoints before using the sendMail method.
 */
public class UnifiedAPIController {

    private static final String TAG = "UnifiedAPIController";
    private static UnifiedAPIController INSTANCE;

    public static synchronized UnifiedAPIController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UnifiedAPIController();
            // TODO get access token and initialize the Builder() to get a RestAdapter.
            // Or initialize in the constructor (might be better there).
        }
        return INSTANCE;
    }


    /**
     * Sends an email message using the Unified API on Office 365. The mail is sent
     * from the address of the signed in user.
     *
     * @param emailAddress The recipient email address.
     * @param subject      The subject to use in the mail message.
     * @param body         The body of the message.
     */
    public void sendMail(final String emailAddress, final String subject, final String body, Callback<MailVO> callback) {

            // Prepare the message.
            //TODO prepare message by storing it in MailVO object


            // Use the Unified API service on Office 365 to create the message.
            //TODO create the draft message on UnifiedAPIService. This requires a callback I think

            // Use the Unified API service on Office 365 to send the message.
            //TODO send the draft message on UnifiedAPIService. This happens in the callback onsuccess I think
    }
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
