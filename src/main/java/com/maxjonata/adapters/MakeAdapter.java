package com.maxjonata.adapters;

import com.maxjonata.controllers.LoggerDisabled;
import com.maxjonata.domain.Logger;

public class MakeAdapter {
    public Logger logger(Logger logger){
        try {
            return logger;
        } catch (Exception e) {
            return new LoggerDisabled();
        }
    }
}