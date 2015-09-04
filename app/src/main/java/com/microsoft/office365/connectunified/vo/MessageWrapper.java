package com.microsoft.office365.connectunified.vo;

import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;

public class MessageWrapper {

    @SuppressLint("unused")
    public MessageWrapper() {}

    public MessageWrapper(MessageVO msg) {
        mMessage = msg;
    }

    @SerializedName("Message")
    public MessageVO mMessage;
}
