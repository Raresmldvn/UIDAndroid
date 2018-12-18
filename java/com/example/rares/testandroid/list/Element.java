package com.example.rares.testandroid.list;

import java.io.Serializable;

public class Element implements Serializable {

    private int id;
    private String name;
    private String description;
    private boolean isFavorite;

    public Element() {}

    public Element(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Element(int id, String name, String description, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
