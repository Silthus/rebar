package com.sk89q.rebar.config.types;

import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.Loader;

public class LowercaseStringLoaderBuilder implements Loader<String>, Builder<String> {

    @Override
    public Object write(String value) {
        return String.valueOf(value);
    }

    @Override
    public String read(Object value) {
        return String.valueOf(value).toLowerCase();
    }

}
