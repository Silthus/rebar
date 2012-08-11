package com.sk89q.rebar.config.types;

import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.Loader;

public class FloatLoaderBuilder implements Loader<Float>, Builder<Float> {

    @Override
    public Object write(Float value) {
        return value;
    }

    @Override
    public Float read(Object value) {
        return valueOf(value);
    }

    public static Float valueOf(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Float) {
            return (Float)o;
        } else if (o instanceof Double) {
            return (Float)o;
        } else if (o instanceof Byte) {
            return (float)(Byte)o;
        } else if (o instanceof Integer) {
            return (float)(Integer)o;
        } else if (o instanceof Long) {
            return (float)(Long)o;
        } else {
            return null;
        }
    }

}
