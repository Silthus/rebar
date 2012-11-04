package com.sk89q.rebar.config;

import java.io.FileNotFoundException;
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
     * @param clazz the class to load from
     * @param path the path of the file
     */
    public YamlConfigurationResource(Class<?> clazz, String path) {
        super(new HashMap<Object, Object>(), new YamlStyle());
        
        this.clazz = clazz;
        this.path = path;
    }

    @Override
    protected InputStream getInputStream() throws IOException {
        InputStream stream = clazz.getResourceAsStream(path);
        if (stream == null) {
            throw new FileNotFoundException(
                    "Could not find '" + path + "' inside class " + clazz.getCanonicalName());
        }
        return stream;
    }

    @Override
    protected OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException("Can't save back to a resource");
    }

}
