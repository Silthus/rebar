package com.sk89q.rebar.config.types;

import java.util.ArrayList;
import java.util.List;

import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.LoaderBuilderException;

public class ListBuilder<V> implements Builder<List<V>> {
    
    private final Builder<V> builder;
    private boolean keepNulls;

    public ListBuilder(Builder<V> builder) {
        this(builder, false);
    }

    public ListBuilder(Builder<V> builder, boolean keepNulls) {
        this.builder = builder;
        this.keepNulls = keepNulls;
    }

    @Override
    public Object write(List<V> value) throws LoaderBuilderException {
        List<Object> newList = new ArrayList<Object>();
        for (V v : value) {
            Object o = builder.write(v);
            if (o == null && !keepNulls) {
                continue;
            }
            newList.add(o);
        }
        return newList;
    }

}
