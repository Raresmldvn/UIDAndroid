package com.example.rares.testandroid.chat;

public class UserCredentials {
    private  static final UserCredentials ourInstance = new UserCredentials();

    public static UserCredentials getInstance() {
        return ourInstance;
    }

    private String token;
    private String display;

    private UserCredentials() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
