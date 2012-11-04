package com.sk89q.rebar.util;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Utility classes for java.util.logging.*;
 */
public class LoggerUtils {
    
    private LoggerUtils() {
    }
    
    /**
     * Get a logger that sets a prefix for all messages.
     * 
     * @param name name of the logger
     * @param prefix prefix to set, or null for none
     * @return a logger
     */
    public static Logger getLogger(String name, final String prefix) {
        Logger logger = Logger.getLogger(name);
        if (prefix != null) {
            logger.setFilter(new Filter() {
                @Override
                public boolean isLoggable(LogRecord record) {
                    record.setMessage(prefix + record.getMessage());
                    return true;
                }
            });
        }
        return logger;
    }
    
    /**
     * Get a logger that sets a prefix for all messages. The name of the logger will be
     * the classes' canonical name.
     * 
     * @param clazz the class
     * @param prefix prefix to set, or null for none
     * @return a logger
     */
    public static Logger getLogger(Class<?> clazz, final String prefix) {
        return getLogger(clazz.getCanonicalName(), prefix);
    }
    
    /**
     * Get a logger for a class. The name of the logger will be the
     * classes' canonical name.
     * 
     * @param clazz the class
     * @return a logger
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getCanonicalName(), null);
    }

}
