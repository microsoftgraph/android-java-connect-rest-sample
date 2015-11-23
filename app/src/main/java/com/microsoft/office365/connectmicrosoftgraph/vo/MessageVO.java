/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Mail Value Object for holding values in an email
 */
public class MessageVO {

    @SerializedName("Subject")
    public String mSubject;

    @SerializedName("Body")
    public BodyVO mBody;

    @SerializedName("ToRecipients")
    public ToRecipientsVO[] mToRecipients;

}