package com.sk89q.rebar;

import java.io.File;

/**
 * Main framework interface.
 * 
 * @see RebarInstance
 */
public interface Rebar {
    
    /**
     * Get the directory used to store Rebar-related configuration.
     * 
     * @return directory used to store configuration
     */
    File getDataDirectory();

}
