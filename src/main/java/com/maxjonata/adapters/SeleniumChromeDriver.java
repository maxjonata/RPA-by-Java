package com.maxjonata.adapters;

import com.maxjonata.domain.DriverAdapter;
import com.maxjonata.domain.DriverManagerAdapter;
import com.maxjonata.domain.WaitAdapter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumChromeDriver implements DriverManagerAdapter {
    private final ChromeDriver driverInstance;
    private final WebDriverWait waitInstance;

    public SeleniumChromeDriver() {
        CreateChromeDriver.setDriverResource();
        this.driverInstance = CreateChromeDriver.make();
        this.waitInstance = new WebDriverWait(this.driverInstance, Duration.ofSeconds(30));
    }

    public DriverAdapter driver() {
        return new com.maxjonata.adapters.DriverAdapter(this.driverInstance);
    }
    public WaitAdapter waits() {
        return new com.maxjonata.adapters.WaitAdapter(this.waitInstance);
    }

    public void quit() {
        this.driverInstance.quit();
    }
    public void close() {
        this.driverInstance.close();
    }

}
