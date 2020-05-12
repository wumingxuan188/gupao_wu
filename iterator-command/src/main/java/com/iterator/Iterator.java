package com.iterator;

import java.util.List;

public interface Iterator<E> {
    E next();
    boolean hasNext();
}
