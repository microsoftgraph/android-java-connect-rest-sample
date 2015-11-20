/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

import com.microsoft.office365.connectmicrosoftgraph.vo.BodyVO;
import com.microsoft.office365.connectmicrosoftgraph.vo.EmailAddressVO;
import com.microsoft.office365.connectmicrosoftgraph.vo.MessageVO;
import com.microsoft.office365.connectmicrosoftgraph.vo.MessageWrapper;
import com.microsoft.office365.connectmicrosoftgraph.vo.ToRecipientsVO;

import retrofit.Callback;


/**
 * Handles the creation of the message and contacting the
 * mail service to send the message. The app must have
 * connected to Office 365 and discovered the mail service
 * endpoints before using the createDraftMail method.
 */
public class MSGraphAPIController {

    private MSGraphAPIService mMSGraphAPIService;

    public MSGraphAPIController() {
        mMSGraphAPIService = new RESTHelper()
                .getRestAdapter()
                .create(MSGraphAPIService.class);
    }

    /**
     * Sends an email message using the Microsoft Graph API on Office 365. The mail is sent
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
        // create the email
        MessageWrapper msg = createMailPayload(subject, body, emailAddress);

        // send it using our service
        mMSGraphAPIService.sendMail("application/json", msg, callback);
    }


    private MessageWrapper createMailPayload(
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

        return new MessageWrapper(sampleMsg);
    }

}