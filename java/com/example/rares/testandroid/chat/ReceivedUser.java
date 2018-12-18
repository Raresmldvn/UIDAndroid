package com.example.rares.testandroid.chat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReceivedUser implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("display")
    private String name;

    @SerializedName("token")
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
