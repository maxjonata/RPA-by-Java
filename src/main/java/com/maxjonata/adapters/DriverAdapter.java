package com.maxjonata.adapters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class DriverAdapter implements com.maxjonata.domain.DriverAdapter {
    private ChromeDriver instance;

    public DriverAdapter(ChromeDriver webDriver) {
        this.instance = webDriver;
    }

    public WebElement findElement(By by) {
        return this.instance.findElement(by);
    }
    public List<WebElement> findElements(By by) {
        return this.instance.findElements(by);
    }

    public void navigate_to(String url) {
        this.instance.navigate().to(url);
    }

    public ChromeDriver getInstance() {
        return instance;
    }
}
