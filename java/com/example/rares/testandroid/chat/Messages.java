package com.example.rares.testandroid.chat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Messages implements Serializable {

    @SerializedName("messages")
    private List<ReceivedMessage> messages;

    public List<ReceivedMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ReceivedMessage> messages) {
        this.messages = messages;
    }
}
