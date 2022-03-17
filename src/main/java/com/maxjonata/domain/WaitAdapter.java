package com.maxjonata.domain;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface WaitAdapter {
    void until(ExpectedCondition EC);
}
