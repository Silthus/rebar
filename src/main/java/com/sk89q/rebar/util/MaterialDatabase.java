package com.sk89q.rebar.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sk89q.rebar.RebarInstance;
import com.sk89q.rebar.config.ConfigurationException;
import com.sk89q.rebar.config.PairedKeyValueLoaderBuilder;
import com.sk89q.rebar.config.YamlConfigurationFile;
import com.sk89q.rebar.config.types.LowercaseStringLoaderBuilder;
import com.sk89q.rebar.config.types.MaterialPatternLoaderBuilder;
import com.sk89q.worldedit.blocks.ItemType;

public class MaterialDatabase {

    private static MaterialDatabase instance;

    private static final Logger logger = Logger.getLogger(MaterialDatabase.class.getCanonicalName());
    private final File file;
    private Map<String, MaterialPattern> patterns = new HashMap<String, MaterialPattern>();

    public static MaterialDatabase getInstance() {
        if (instance == null) {
            instance = new MaterialDatabase();
        }
        return instance;
    }

    private MaterialDatabase() {
        file = new File(RebarInstance.getInstance().getDataDirectory(), "materials.yml");

        try {
            loadMaterials();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to load materials database", e);
        } catch (ConfigurationException e) {
            logger.log(Level.WARNING, "Failed to load materials database", e);
        }
    }

    public void loadMaterials() throws IOException, ConfigurationException {
        if (!file.exists()) {
            return;
        }

        YamlConfigurationFile config = new YamlConfigurationFile(file);
        config.load();

        PairedKeyValueLoaderBuilder<String, MaterialPattern> loader =
                PairedKeyValueLoaderBuilder.build(
                        new LowercaseStringLoaderBuilder(),
                        new MaterialPatternLoaderBuilder(null));
        patterns = config.mapOf("materials", loader);
    }

    public MaterialPattern getPattern(String name) {
        MaterialPattern pattern = patterns.get(name.toLowerCase());
        if (pattern != null) {
            return pattern;
        }
        
        // Search WorldEdit
        ItemType type = ItemType.lookup(name.replace("_", ""));
        if (type != null) {
            return new MaterialPattern(type.getID());
        }
        
        return null;
    }

    public static void reload() {
        try {
            getInstance().loadMaterials();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to load materials database", e);
        } catch (ConfigurationException e) {
            logger.log(Level.WARNING, "Failed to load materials database", e);
        }
    }

}
