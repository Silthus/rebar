package com.sk89q.rebar.config.types;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.sk89q.rebar.config.Loader;
import com.sk89q.rebar.config.LoaderBuilderException;

public class StaticFieldLoaderBuilder<T> implements Loader<T> {

    private final Class<?> type;
    private final Map<String, Field> fields = new HashMap<String, Field>();

    public StaticFieldLoaderBuilder(Class<?> type, Class<T> fieldType) {
        this.type = type;

        for (Field field : type.getFields()) {
            if (Modifier.isStatic(field.getModifiers())
                    && fieldType.isAssignableFrom(field.getType())) {
                fields.put(normalize(field.getName()), field);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public StaticFieldLoaderBuilder(Class<?> type) {
        this(type, (Class<T>) type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T read(Object value) {
        String stringValue = String.valueOf(value);
        String normalized = normalize(stringValue);
        
        Field field = fields.get(normalized);
        if (field != null) {
            try {
                return (T) field.get(null);
            } catch (IllegalArgumentException e) {
                throw new LoaderBuilderException(e);
            } catch (IllegalAccessException e) {
                throw new LoaderBuilderException(e);
            }
        }

        throw new LoaderBuilderException("Did not find " + stringValue + " in "
                + type.getCanonicalName());
    }
    
    private static String normalize(String name) {
        return name.replace("_", "").toLowerCase();
    }

}