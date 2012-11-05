package com.sk89q.rebar.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Various reflection utility functions.
 */
public class ReflectionUtils {

    private ReflectionUtils() {
    }
    
    public static Class<?> cls(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }
    
    public static boolean isOf(Object obj, String name) {
        return obj.getClass().getCanonicalName().equals(name);
    }
    
    public static Object field(Class<?> cls, Object obj, String name)
            throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
        Field field = cls.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(obj);
    }
    
    public static Object field(Object obj, String name)
            throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(obj);
    }
    
    public static Object invokeStatic(Class<?> cls, String name)
            throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Method method = cls.getDeclaredMethod(name);
        method.setAccessible(true);
        return method.invoke(null);
    }
    
}
