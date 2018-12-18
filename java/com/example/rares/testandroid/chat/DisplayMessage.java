package com.example.rares.testandroid.chat;

import java.io.Serializable;
import java.util.Date;

public class DisplayMessage implements Serializable {

    private String name;
    private String message;
    private Date date;

    public DisplayMessage(String name, String message, Date date) {
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
