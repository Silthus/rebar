package com.sk89q.rebar.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PairedKeyValueOf {
    
    Class<?> keys();
    
    Class<?> values();

    @SuppressWarnings("rawtypes")
    Class<? extends Map> type() default HashMap.class;

}
