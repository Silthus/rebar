package com.sk89q.rebar.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionOf {
    
    Class<?> value();
    
    @SuppressWarnings("rawtypes")
    Class<? extends Collection> type() default ArrayList.class;

}
