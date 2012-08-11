package com.sk89q.rebar.config.types;

import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.ConfigurationNode;
import com.sk89q.rebar.config.Loader;
import com.sk89q.rebar.util.MapBuilder.ObjectMapBuilder;
import com.sk89q.worldedit.BlockVector2D;

public class BlockVector2dLoaderBuilder implements Loader<BlockVector2D>, Builder<BlockVector2D> {

    @Override
    public Object write(BlockVector2D value) {
        return new ObjectMapBuilder()
            .put("x", value.getBlockX())
            .put("z", value.getBlockZ())
            .map();
    }

    @Override
    public BlockVector2D read(Object value) {
        ConfigurationNode node = new ConfigurationNode(value);
        Double x = node.getDouble("x");
        Double z = node.getDouble("z");

        if (x == null || z == null) {
            return null;
        }

        return new BlockVector2D(x, z);
    }

}
