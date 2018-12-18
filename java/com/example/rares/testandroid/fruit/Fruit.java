package com.example.rares.testandroid.fruit;

public class Fruit {

    private int id;
    private String name;
    private float price;
    private int quantity;
    private boolean isChecked;

    public Fruit() {}

    public Fruit(int id, String name, float price, int quantity, boolean isChecked) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isChecked = isChecked;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
