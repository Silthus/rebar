package com.sk89q.rebar.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Various utilities for loading defaults.
 */
public class DefaultsUtils {

    private DefaultsUtils() {
    }
    
    /**
     * Create a default configuration file from the .jar.
     *
     * @param clazz class to get files from (via its .jar or directory)
     * @param actual the destination file
     * @param path the name of the file inside the jar's defaults folder
     */
    public static void createDefaultConfiguration(Class<?> clazz, File actual, String path) {
        // Make parent directories
        File parent = actual.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }

        if (actual.exists()) {
            return;
        }

        InputStream input = clazz.getResourceAsStream(path);
        if (input == null) {
            LoggerUtils.getLogger(clazz).warning(
                    "Unable to read default configuration '" + path + "' of "
                            + clazz.getCanonicalName());
        }

        if (input != null) {
            FileOutputStream output = null;

            try {
                output = new FileOutputStream(actual);
                byte[] buf = new byte[8192];
                int length = 0;
                while ((length = input.read(buf)) > 0) {
                    output.write(buf, 0, length);
                }

                LoggerUtils.getLogger(clazz).info("Default configuration file written: "
                        + actual.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException ignore) {
                }

                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ignore) {
                }
            }
        }
    }

}
