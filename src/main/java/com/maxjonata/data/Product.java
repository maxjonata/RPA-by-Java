package com.maxjonata.data;

public class Product implements com.maxjonata.domain.Product {
    String name;
    String Url;
    double price;
    float rate;

    public Product(String url) {
        Url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return Url;
    }

    public double getPrice() {
        return price;
    }

    public float getRate() {
        return rate;
    }
}
