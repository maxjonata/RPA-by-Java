package com.maxjonata.controllers;

public class LoggerDisabled implements com.maxjonata.domain.Logger {
    public Boolean log(String message) {
        return true;
    }
    public Boolean logStep(String message) {
        return true;
    }
}
