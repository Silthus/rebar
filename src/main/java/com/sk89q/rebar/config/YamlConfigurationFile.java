// $Id$
/*
 * WorldGuard
 * Copyright (C) 2010 sk89q <http://www.sk89q.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package com.sk89q.rebar.config;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Loads and saves YAML-based configurations from/to a file. When saving the
 * configuration, the parent folder to contain the file will be created recursively.
 */
public class YamlConfigurationFile extends YamlConfiguration {
    
    private File file;

    /**
     * Create a new YAML configuration from a file. Be aware that {@link #load()} still
     * has to be called at least once before anything is loaded from the file.
     * 
     * @param file the file
     */
    public YamlConfigurationFile(File file) {
        super(new HashMap<Object, Object>());
        
        this.file = file;
    }

    @Override
    protected InputStream getInputStream() throws IOException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    @Override
    protected OutputStream getOutputStream() throws IOException {
        File dir = file.getParentFile();
        
        // Make parent folder if it doesn't exist
        if (!dir.exists()) {
            file.getParentFile().mkdirs();
        }
        
        return new BufferedOutputStream(new FileOutputStream(file));
    }
}
