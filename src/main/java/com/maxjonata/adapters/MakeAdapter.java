package com.maxjonata.adapters;

import com.maxjonata.controllers.LoggerDisabled;
import com.maxjonata.domain.Logger;

import java.util.Objects;

public class MakeAdapter {
    public Logger logger(Logger logger){
        return Objects.requireNonNullElse(logger, new LoggerDisabled());
    }
}