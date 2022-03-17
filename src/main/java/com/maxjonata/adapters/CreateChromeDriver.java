package com.maxjonata.adapters;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class CreateChromeDriver {
    public static void setDriverResource() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\RPA-by-Java\\src\\main\\resources\\chromedriver.exe");
    }
    public static ChromeDriver make() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");

        ChromeDriver driver = new ChromeDriver(options);

        return driver;
    }
}
