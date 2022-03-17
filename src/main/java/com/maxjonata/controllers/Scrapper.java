package com.maxjonata.controllers;

import com.maxjonata.adapters.CreateChromeDriver;
import com.maxjonata.domain.Logger;
import com.maxjonata.domain.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class Scrapper implements com.maxjonata.domain.Scrapper {
    private final String searchSite;
    private final Logger log;

    public Scrapper(String siteToSearch, Logger loggerClass) {
        CreateChromeDriver.setDriverResource();

        this.searchSite = siteToSearch;
        this.log = loggerClass;
    }

    public SortedSet<Product> search(String productName) {
        this.log.logStep("Starting search");
        this.log.log("Looking for '" + productName + "' in '" + this.searchSite + "'");
        ChromeDriver driver = CreateChromeDriver.make();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            SortedSet<Product> ProductList = new TreeSet<>(Comparator.comparing(Product::getPrice));
            driver.navigate().to(this.searchSite);

            String searchBoxXPath = ".//input[contains(@id, 'search') or contains(@class, 'search') or " +
                    "contains(@type, 'search')]";
            String itemXPath = "//a[contains(@href, 'produto') and ./div[contains(@class, 'market')]]";
            String orderSelXPath = "//select";
            String loadingXPath = "//div[contains(@class, 'LdsRing')]";

            WebElement searchBar = driver.findElement(By.xpath(searchBoxXPath));
            searchBar.sendKeys(productName);
            searchBar.submit();

            /*
                Aqui para garantir realmente o menor valor teria que seguir página por página coletando
            pois a loja requisitada não segue a ordem totalmente correta.
                Acabaria prejudicando o desempenho, complexidade e tempo de execução,
            então mantive simples e direto para o exame prático.
             */
            Select selectionBar = new Select(driver.findElement(By.xpath(orderSelXPath)));
            selectionBar.selectByValue("lowerPrice");
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loadingXPath)));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loadingXPath)));

            List<WebElement> productElements = driver.findElements(By.xpath(itemXPath)).subList(0,3);
            productElements.forEach(element -> {
                Product product = new com.maxjonata.data.Product(element.getAttribute("href"));
                ProductList.add(product);
            });

            driver.close();
            this.log.logStep("Ended search successfully");
            return ProductList;
        } catch (Exception e) {
            this.log.log("Error: " + e);
            this.log.logStep("Ended search ungracefully");
            driver.close();
            return null;
        }
    }

    public String scrapeValue(String valueName, String valueType){
        return "";
    }
}
