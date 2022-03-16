package com.maxjonata.controllers;
import com.maxjonata.domain.Product;
import java.util.*;

public abstract class Scrapper implements com.maxjonata.domain.Scrapper {
    private final String searchSite;

    public Scrapper(String siteToSearch) {
        searchSite = siteToSearch;
    }

    public SortedSet<Product> search(String productName) {
        SortedSet<Product> ProductList = new TreeSet<>(Comparator.comparing(Product::getPrice));
        return ProductList;
    }

    public String scrapeValue(String valueName, String valueType){
        return "";
    }
}
