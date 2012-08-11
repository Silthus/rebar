package com.sk89q.rebar.config.types;

import java.util.logging.Logger;

import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.Loader;

public class EnumLoaderBuilder<T extends Enum<T>> implements Loader<T>, Builder<T> {

    private final Logger logger = Logger.getLogger(EnumLoaderBuilder.class.getCanonicalName());
    private final Class<T> enumType;

    public EnumLoaderBuilder(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T read(Object value) {
        String stringValue = String.valueOf(value);

        try {
            return Enum.valueOf(enumType, stringValue);
        } catch (IllegalArgumentException e) {
        }

        try {
            return Enum.valueOf(enumType, stringValue.toUpperCase());
        } catch (IllegalArgumentException e) {
        }

        try {
            String normalized = normalize(stringValue);

            for (T object : enumType.getEnumConstants()) {
                if (normalize(object.name()).equalsIgnoreCase(normalized)) {
                    return object;
                }
            }
            return Enum.valueOf(enumType, stringValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warning("EnumLoaderBuilder: Could not find '" + stringValue + "' in " +
                    enumType.getCanonicalName());
        }

        return null;
    }

    @Override
    public Object write(T value) {
        return value.name();
    }

    private static String normalize(String str) {
        return str.replace("_", "");
    }

}