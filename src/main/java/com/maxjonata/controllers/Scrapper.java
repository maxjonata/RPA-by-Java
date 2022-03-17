package com.maxjonata.controllers;

import com.maxjonata.adapters.MakeAdapter;
import com.maxjonata.domain.DriverManagerAdapter;
import com.maxjonata.domain.Logger;
import com.maxjonata.domain.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.Nullable;
import java.util.*;

public class Scrapper implements com.maxjonata.domain.Scrapper {
    private final String searchSite;
    private final Logger log;
    private DriverManagerAdapter driverManager;

    public Scrapper(String siteToSearch, @Nullable Logger loggerClass, DriverManagerAdapter driverManager) {
        this.driverManager = driverManager;
        this.searchSite = siteToSearch;
        this.log = new MakeAdapter().logger(loggerClass);
    }

    public SortedSet<Product> search(String productName) {
        this.log.logStep("Starting search");
        this.log.log("Looking for '" + productName + "' in '" + this.searchSite + "'");

        try {
            SortedSet<Product> ProductList = new TreeSet<>(new Comparator<>() {
                @Override
                public int compare(Product p1, Product p2) {
                    int compareValue = Double.compare(p1.getPrice(), p2.getPrice());
                    return compareValue == 0 ? 1 : compareValue;
                }
            });

            this.driverManager.driver().navigate_to(this.searchSite);
            String searchBoxXPath = ".//input[contains(@id, 'search') or contains(@class, 'search') or " +
                    "contains(@type, 'search')]";
            String itemXPath = "//a[contains(@href, 'produto') and ./div[contains(@class, 'market')]]";
            String orderSelXPath = "//select";
            String loadingXPath = "//div[contains(@class, 'LdsRing')]";

            WebElement searchBar = this.driverManager.driver().findElement(By.xpath(searchBoxXPath));
            searchBar.sendKeys(productName);
            searchBar.submit();
            /*
                Aqui para garantir realmente o menor valor teria que seguir página por página coletando
            pois a loja requisitada não segue a ordem totalmente correta.
                Acabaria prejudicando o desempenho, complexidade e tempo de execução,
            então mantive simples e direto para o exame prático.
             */

            Select selectionBar = new Select(this.driverManager.driver().findElement(By.xpath(orderSelXPath)));
            selectionBar.selectByValue("lowerPrice");

            this.driverManager.waits().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loadingXPath)));
            this.driverManager.waits().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loadingXPath)));

            List<WebElement> productElements = this.driverManager.driver().findElements(By.xpath(itemXPath)).subList(0,3);
            List<String> productLinks = new ArrayList<>();
            productElements.forEach(webEl -> productLinks.add(webEl.getAttribute("href")));
            productLinks.forEach(link -> ProductList.add(new com.maxjonata.data.Product(link, this)));

            this.driverManager.quit();
            this.log.logStep("Ended search successfully");
            return ProductList;
        } catch (Exception e) {
            this.log.log("Error: " + e);
            this.log.logStep("Ended search ungracefully");

            this.driverManager.quit();
            return null;
        }
    }

    public String scrapeInnerText(String XPath, String url){
        String text = "";

        this.log.logStep("Starting inner text scrapping");
        try {
            this.driverManager.driver().navigate_to(url);
            this.driverManager.waits().until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
            text = this.driverManager.driver().findElement(By.xpath(XPath)).getText();
            this.log.logStep("Ended inner text scrapping successfully");
        } catch (Exception e) {
            this.log.log("Error: " + e);
            this.log.logStep("Ended inner text scrapping ungracefully");
        }
        
        return text;
    }
}
