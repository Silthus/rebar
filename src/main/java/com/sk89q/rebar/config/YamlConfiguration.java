package com.sk89q.rebar.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.reader.UnicodeReader;
import org.yaml.snakeyaml.representer.Representer;

/**
 * Loads and saves configurations based off of YAML.
 */
public abstract class YamlConfiguration extends ConfigurationNode {
    
    private Yaml yaml;
    private String header = null;

    /**
     * Construct the configuration with the given map.
     * 
     * @param root root
     */
    protected YamlConfiguration(Map<Object, Object> root) {
        super(root);

        DumperOptions options = new DumperOptions();
        options.setIndent(4);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.AUTO);

        yaml = new Yaml(new SafeConstructor(), new Representer(), options);
    }
    
    /**
     * Get the underlying YAML object.
     * 
     * @return YAML object
     */
    protected Yaml getYaml() {
        return yaml;
    }

    /**
     * Set the header for the file as a series of lines that are terminated
     * by a new line sequence.
     *
     * @param headerLines header lines to prepend
     */
    public void setHeader(String... headerLines) {
        StringBuilder header = new StringBuilder();

        for (String line : headerLines) {
            if (header.length() > 0) {
                header.append("\r\n");
            }
            header.append(line);
        }

        setHeader(header.toString());
    }

    /**
     * Set the header for the file. A header can be provided to prepend the
     * YAML data output on configuration save. The header is
     * printed raw and so must be manually commented if used. A new line will
     * be appended after the header, however, if a header is provided.
     *
     * @param header header to prepend
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Return the set header.
     *
     * @return the header text block
     */
    public String getHeader() {
        return header;
    }

    /**
     * Loads the configuration file.
     *
     * @throws IOException on I/O error
     */
    public void load() throws IOException {
        InputStream stream = null;
        
        try {
            stream = getInputStream();
            read(getYaml().load(new UnicodeReader(stream)));
        } catch (ConfigurationException e) {
            setRoot(new HashMap<Object, Object>());
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
            }
        }
    }
    
    /**
     * Called by {@link #load()} in order to load the YAML data. If this method is
     * not supported, throw an {@link UnsupportedOperationException}.
     * 
     * @return input stream
     */
    protected abstract InputStream getInputStream() throws IOException;

    /**
     * Saves the configuration to disk.
     *
     * @throws IOException on I/O error
     */
    public void save() throws IOException {
        OutputStream stream = null;

        try {
            stream = getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
            
            if (header != null) {
                writer.append(header);
                writer.append("\r\n");
            }
            
            yaml.dump(getRoot(), writer);
        } catch (UnsupportedEncodingException e) {
            throw new IOException("Unsupported encoding", e);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {}
        }
    }
    
    /**
     * Called by {@link #save()} in order to save the YAML data. If this method is
     * not supported, throw an {@link UnsupportedOperationException}.
     * 
     * @return output stream
     */
    protected abstract OutputStream getOutputStream() throws IOException;

    /**
     * Attempt to convert the given input object into a map, and then set the root
     * of this {@link ConfigurationNode} as the map. If it's not a map, throw a
     * {@link ConfigurationException}.
     * 
     * @param input input object
     * @throws ConfigurationException thrown if not a map
     */
    @SuppressWarnings("unchecked")
    private void read(Object input) throws ConfigurationException {
        try {
            if (input == null) {
                setRoot(new HashMap<Object, Object>());
            } else {
                setRoot((Map<Object, Object>) input);
            }
        } catch (ClassCastException e) {
            throw new ConfigurationException("Root document must be an key-value structure");
        }
    }

}
