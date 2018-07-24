package com.example.nyxulric.simpleappui;

public class ProductDetail {
    public String brand;
    public String price;
    public String description;
    public String listName;

    public ProductDetail(){

    }

    public ProductDetail(String brand, String price, String description, String listName) {
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.listName = listName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}

