package com.sk89q.rebar.config.types;

import java.util.Map;

import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.ConfigurationNode;
import com.sk89q.rebar.config.Loader;

public class NodeLoaderBuilder implements Loader<ConfigurationNode>, Builder<ConfigurationNode> {

    @Override
    public Object write(ConfigurationNode value) {
        return value;
    }

    @Override
    public ConfigurationNode read(Object value) {
        if (value instanceof Map) {
            return new ConfigurationNode(value);
        } else {
            return null;
        }
    }

}
