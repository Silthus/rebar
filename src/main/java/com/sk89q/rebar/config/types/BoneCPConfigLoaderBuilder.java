package com.sk89q.rebar.config.types;

import com.jolbox.bonecp.BoneCPConfig;
import com.sk89q.rebar.config.AbstractNodeLoader;
import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.ConfigurationNode;
import com.sk89q.rebar.config.LoaderBuilderException;

public class BoneCPConfigLoaderBuilder extends AbstractNodeLoader<BoneCPConfig>
        implements Builder<BoneCPConfig> {

    @Override
    public Object write(BoneCPConfig config) throws LoaderBuilderException {
        ConfigurationNode node = new ConfigurationNode();
        node.set("dsn", config.getJdbcUrl());
        node.set("username", config.getUsername());
        node.set("password", config.getPassword());
        
        return node;
    }

    @Override
    public BoneCPConfig read(ConfigurationNode node) {
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(node.getString("dsn", ""));
        config.setUsername(node.getString("username", "root"));
        config.setPassword(node.getString("password", ""));
        
        return config;
    }

}
