package com.maxjonata.data;

import com.maxjonata.adapters.MakeAdapter;
import com.maxjonata.domain.Logger;
import com.maxjonata.domain.Scrapper;

public class Product implements com.maxjonata.domain.Product {
    String name;
    String url;
    double price;
    float rate;
    Logger log;
    Scrapper scrapper;

    public Product(String url, Scrapper scrapper) {
        this.scrapper = scrapper;
        this.log = new MakeAdapter().logger(null);
        this.getAttributes(url);
    }

    public Product(String url, Scrapper scrapper, Logger loggerClass){
        this.scrapper = scrapper;
        this.log = new MakeAdapter().logger(loggerClass);
        this.getAttributes(url);
    }

    private void getAttributes(String url) {
        this.url = url;

        this.log.logStep("Starting product data search");
        try {
            String XPath_name = "//h1[contains(@class, 'product-title')]";
            String XPath_price = "//div[contains(@class, 'product-offer')]//div[text()[contains(.,'R$')]]";
            String XPath_rate = "//span[contains(@class, 'RatingValue')]";

            this.name = this.scrapper.scrapeInnerText(XPath_name, url);
            this.price = Double.parseDouble(this.scrapper.scrapeInnerText(XPath_price, url).replaceAll("\\D+","")) / 100;
            this.rate = Float.parseFloat(this.scrapper.scrapeInnerText(XPath_rate, url).replaceAll("\\D+","")) / 10;

            this.log.logStep("Ended product data search successfully");
        } catch (Exception e) {
            this.log.log("Error: " + e);
            this.log.logStep("Ended product data search ungracefully");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public double getPrice() {
        return this.price;
    }

    public float getRate() {
        return this.rate;
    }
}
