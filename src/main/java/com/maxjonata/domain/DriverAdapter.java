package com.maxjonata.domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface DriverAdapter {
    void navigate_to(String url);
    WebElement findElement(By by);
    List<WebElement> findElements(By by);
}
