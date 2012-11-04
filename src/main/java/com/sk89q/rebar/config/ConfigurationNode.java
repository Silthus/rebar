package com.sk89q.rebar.config;

import java.util.HashMap;
import java.util.Map;

/**
 * A configuration node with various methods to access properties of it. The underlying
 * structure is a {@link Map}.
 *
 * @author sk89q
 */
public class ConfigurationNode extends ConfigurationObject implements Cloneable {

    public static final String ROOT = "";
    
    private ConfigurationNode parent;

    /**
     * Construct the node from the given map.
     *
     * @param root root node
     */
    public ConfigurationNode(Map<Object, Object> root) {
        super(root);
    }

    /**
     * Attempt to construct a node from the given object. If the object is
     * not a map, then the {@link ConfigurationNode} will consist of a new
     * empty map. This will not make a shallow copy of the map.
     *
     * @param object an object to construct a node from
     */
    public ConfigurationNode(Object object) {
        super(makeMap(object));
    }

    /**
     * Get the parent configuration node. A parent node is used when accessing an element
     * that is not found in this node. The same also applies when trying to set a value
     * to this node. If there is a parent, and this node does not contain the location,
     * then the parent will be written to.
     * </p>
     * Parent nodes are not used when loading or saving configurations
     * (see {@link YamlConfigurationFile}).
     * 
     * @return parent node or null
     */
    public ConfigurationNode getParent() {
        return parent;
    }

    /**
     * Set the parent node.
     * 
     * @see #getParent()
     * @param parent node or null
     */
    public void setParent(ConfigurationNode parent) {
        this.parent = parent;
    }

    /**
     * Clear all nodes.
     */
    public void clear() {
        getUnderlyingMap().clear();
    }

    @Override
    protected Object get(String[] parts) {
        // If this node has a parent, redirect to the parent if this node doesn't
        // contain the given path
        if (getParent() != null) {
            if (!super.contains(parts)) {
                return getParent().get(parts);
            }
        }
        
        return super.get(parts);
    }

    @Override
    protected void set(String[] parts, Object value) {
        // If this node has a parent, redirect to the parent if this node doesn't
        // contain the given path
        if (getParent() != null) {
            if (!super.contains(parts)) {
                getParent().set(parts, value);
                return;
            }
        }
        
        super.set(parts, value);
    }

    @Override
    public void remove(String[] parts) {
        // If this node has a parent, redirect to the parent if this node doesn't
        // contain the given path
        if (getParent() != null) {
            if (!super.contains(parts)) {
                getParent().remove(parts);
                return;
            }
        }
        
        super.remove(parts);
    }

    @Override
    public boolean contains(String[] parts) {
        // If this node has a parent, redirect to the parent if this node doesn't
        // contain the given path
        if (getParent() != null) {
            if (!super.contains(parts)) {
                return getParent().contains(parts);
            }
        }
        
        return super.contains(parts);
    }

    /**
     * Get the number of elements in this node.
     *
     * @return size
     */
    public int size() {
        return getUnderlyingMap().size();
    }

    /**
     * Shallow copy the node.
     */
    @Override
    public ConfigurationNode clone() {
        return new ConfigurationNode(shallowClone(getUnderlyingMap()));
    }

    /**
     * Get the underlying map.
     *
     * @return map
     */
    @SuppressWarnings("unchecked")
    public Map<Object, Object> getUnderlyingMap() {
        return (Map<Object, Object>) get(ROOT);
    }

    /**
     * Used to coerce the given object into a map (if possible), otherwise
     * an empty map is returned.
     *
     * @param object object to try it on
     * @return a map
     */
    @SuppressWarnings("unchecked")
    private static Map<Object, Object> makeMap(Object object) {
        if (object instanceof Map) {
            return (Map<Object, Object>) object;
        } else {
            return new HashMap<Object, Object>();
        }
    }

    /**
     * Get the shallow clone of a map.
     *
     * @param original original map
     * @return cloned map
     */
    private static Map<Object, Object> shallowClone(Map<Object, Object> original) {
        Map<Object, Object> cloned = new HashMap<Object, Object>();
        for (Map.Entry<Object, Object> entry : original.entrySet()) {
            cloned.put(entry.getKey(), entry.getValue());
        }
        return cloned;
    }

}
