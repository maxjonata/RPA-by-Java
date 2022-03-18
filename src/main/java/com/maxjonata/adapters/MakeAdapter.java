package com.maxjonata.adapters;

import com.maxjonata.controllers.LoggerDisabled;
import com.maxjonata.domain.Logger;

import javax.annotation.Nullable;
import java.util.Objects;

public class MakeAdapter {
    public Logger logger(@Nullable Logger logger){
        return Objects.requireNonNullElse(logger, new LoggerDisabled());
    }
}