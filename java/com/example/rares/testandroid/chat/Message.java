package com.example.rares.testandroid.chat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable {

    @SerializedName("message")
    private String messsage;

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
