package com.maxjonata.domain;

public interface DriverManagerAdapter {
    DriverAdapter driver();
    WaitAdapter waits();
    void quit();
    void close();
}
