package com.sk89q.rebar.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * A YAML configuration from a resource.
 */
public class YamlConfigurationResource extends YamlConfiguration {

    private final Class<?> clazz;
    private final String path;
    
    /**
     * Construct a loader that uses the given class and path to load the file.
     * 
     * @param clazz
     * @param path
     */
    protected YamlConfigurationResource(Class<?> clazz, String path) {
        super(new HashMap<Object, Object>());
        
        this.clazz = clazz;
        this.path = path;
    }

    @Override
    protected InputStream getInputStream() throws IOException {
        return clazz.getResourceAsStream(path);
    }

    @Override
    protected OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException("Can't save back to a resource");
    }

}
