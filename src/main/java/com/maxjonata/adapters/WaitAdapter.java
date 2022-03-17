package com.maxjonata.adapters;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitAdapter implements com.maxjonata.domain.WaitAdapter {
    private WebDriverWait instance;

    public WaitAdapter(WebDriverWait waitDriver) {
        this.instance = waitDriver;
    }

    public WebDriverWait getInstance() {
        return instance;
    }

    public void until(ExpectedCondition EC) {
        instance.until(EC);
    }
}
