/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph.vo;

import com.google.gson.annotations.SerializedName;

public class MessageWrapper {

    @SerializedName("Message")
    public MessageVO mMessage;

    public MessageWrapper(MessageVO msg) {
        mMessage = msg;
    }
}