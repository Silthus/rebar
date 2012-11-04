package com.sk89q.rebar.bukkit;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.rebar.Rebar;
import com.sk89q.rebar.RebarInstance;

public class RebarPlugin extends JavaPlugin implements Rebar {
    
    private static final Logger logger = Logger.getLogger(RebarPlugin.class.getCanonicalName());
    
    public RebarPlugin() {
        // onEnable() is too late
        RebarInstance.setInstance(this);
    }

    @Override
    public void onEnable() {
        logger.info("Rebar: Loaded.");
    }

    @Override
    public File getDataDirectory() {
        return getDataFolder();
    }

}
