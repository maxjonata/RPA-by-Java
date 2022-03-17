package com.maxjonata.controllers;

public class LoggerSout implements com.maxjonata.domain.Logger {
    public Boolean log(String message) {
        System.out.println(message);
        return true;
    }
    public Boolean logStep(String message) {
        System.out.println("---------- " + message + "----------");
        return true;
    }
}
