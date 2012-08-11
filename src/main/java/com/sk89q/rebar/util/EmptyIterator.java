package com.sk89q.rebar.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIterator<V> implements Iterator<V> {
    
    public EmptyIterator() {
    }

    public boolean hasNext() {
        return false;
    }

    public V next() {
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException("Not allowed");
    }
    
}
