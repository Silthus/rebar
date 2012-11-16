package com.sk89q.rebar.config.types;

import com.sk89q.rebar.config.Builder;
import com.sk89q.rebar.config.ConfigurationNode;
import com.sk89q.rebar.config.Loader;
import com.sk89q.rebar.util.MapBuilder.ObjectMapBuilder;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector;

public class VectorLoaderBuilder implements Loader<Vector>, Builder<Vector> {
    
    private final boolean asBlock;
    
    public VectorLoaderBuilder() {
        this(false);
    }
    
    public VectorLoaderBuilder(boolean asBlock) {
        this.asBlock = asBlock;
    }

    @Override
    public Object write(Vector value) {
        return new ObjectMapBuilder()
            .put("x", value.getX())
            .put("y", value.getY())
            .put("z", value.getZ())
            .map();
    }

    @Override
    public Vector read(Object value) {
        ConfigurationNode node = new ConfigurationNode(value);
        Double x = node.getDouble("x");
        Double y = node.getDouble("y");
        Double z = node.getDouble("z");

        if (x == null || y == null || z == null) {
            return null;
        }

        return asBlock ? new BlockVector(x, y, z) : new Vector(x, y, z);
    }

}
