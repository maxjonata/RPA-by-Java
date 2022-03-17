package com.maxjonata.adapters;

public class Logger implements com.maxjonata.domain.Logger {
    public Boolean log(String message) {
        System.out.println(message);
        return true;
    }
    public Boolean logStep(String message) {
        System.out.println("---------- " + message + "----------");
        return true;
    }
}
