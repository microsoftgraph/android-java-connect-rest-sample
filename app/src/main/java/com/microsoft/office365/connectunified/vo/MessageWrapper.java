package com.microsoft.office365.connectunified.vo;

import com.google.gson.annotations.SerializedName;

public class MessageWrapper {

    public MessageWrapper() {}

    public MessageWrapper(MessageVO msg) {
        mMessage = msg;
    }

    @SerializedName("Message")
    public MessageVO mMessage;
}
