package com.sk89q.rebar.config;

import org.yaml.snakeyaml.DumperOptions.FlowStyle;

/**
 * A style for formatting YAML data.
 */
public class YamlStyle {

    private final FlowStyle style;
    private final int indent;
    
    public YamlStyle() {
        style = FlowStyle.AUTO;
        indent = 4;
    }
    
    public YamlStyle(FlowStyle style) {
        this.style = style;
        this.indent = 4;
    }
    
    public YamlStyle(FlowStyle style, int indent) {
        this.style = style;
        this.indent = indent;
    }
    
    public FlowStyle getStyle() {
        return style;
    }
    
    public int getIndent() {
        return indent;
    }
    
}
