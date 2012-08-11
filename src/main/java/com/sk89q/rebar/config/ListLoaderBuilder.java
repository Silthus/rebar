package com.sk89q.rebar.config;

import java.util.List;

import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.Loader;

class ListLoaderBuilder implements Loader<List<Object>>, Builder<List<Object>> {

    @Override
    public Object write(List<Object> value) {
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> read(Object value) {
        if (value instanceof List) {
            return (List<Object>) value;
        } else {
            return null;
        }
    }

}
