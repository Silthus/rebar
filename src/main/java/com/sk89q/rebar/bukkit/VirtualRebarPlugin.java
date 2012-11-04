package com.sk89q.rebar.bukkit;

import java.io.File;

import org.bukkit.plugin.Plugin;

import com.sk89q.rebar.Rebar;
import com.sk89q.rebar.RebarInstance;

/**
 * A virtual plugin for when Rebar is shaded.
 */
public class VirtualRebarPlugin implements Rebar {

    private File dataDir;
    
    private VirtualRebarPlugin(Plugin plugin) {
        dataDir = new File(plugin.getDataFolder().getParentFile(), "Rebar");
        
        RebarInstance.setInstance(this);
    }

    @Override
    public File getDataDirectory() {
        return dataDir;
    }
    
    /**
     * Set up the virtual plugin if necessary.
     * 
     * @param plugin another plugin to start with
     */
    public static void setup(Plugin plugin) {
        if (RebarInstance.getInstance() == null) {
            new VirtualRebarPlugin(plugin);
        }
    }

}
