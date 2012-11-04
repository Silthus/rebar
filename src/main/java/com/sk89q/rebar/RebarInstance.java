package com.sk89q.rebar;

/**
 * Holds a global instance of {@link Rebar}. An instance should be set automatically
 * during loading and should not be set by users of the framework.
 */
public class RebarInstance {

    private static Rebar rebar;
    
    private RebarInstance() {
    }
    
    /**
     * Get an instance of the framework.
     * 
     * @return the framework
     */
    public static Rebar getInstance() {
        if (rebar == null) {
            throw new RuntimeException("Uh oh, Rebar is not loaded as a plugin, but its API is bieng used!");
        }
        
        return rebar;
    }
    
    /**
     * Set the instance of the framework. This should only be called by Rebar itself.
     * 
     * @param rebar the framework
     */
    public static void setInstance(Rebar rebar) {
        RebarInstance.rebar = rebar;
    }
    
}
