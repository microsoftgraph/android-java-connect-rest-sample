/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph.vo;

import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;

public class MessageWrapper {

    @SerializedName("Message")
    public MessageVO mMessage;

    @SuppressLint("unused")
    public MessageWrapper() {}

    public MessageWrapper(MessageVO msg) {
        mMessage = msg;
    }
}