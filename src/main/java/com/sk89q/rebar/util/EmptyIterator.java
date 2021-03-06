package com.sk89q.rebar.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIterator<V> implements Iterator<V> {
    
    public EmptyIterator() {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public V next() {
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not allowed");
    }
    
}
