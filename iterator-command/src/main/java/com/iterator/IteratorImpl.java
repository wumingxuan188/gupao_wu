package com.iterator;

import java.util.List;

/**
 * 迭代器的实现
 * @param <E>
 */
public class IteratorImpl<E> implements Iterator<E> {

    private List<E> list;
    private int cursor;
    private E element;

    public IteratorImpl(List<E> list) {
        this.list = list;
    }

    public E next() {
        element = list.get(cursor);
        cursor++;
        return element;
    }

    public boolean hasNext() {
        if (cursor > list.size() - 1) {
            return false;
        }
        return true;
    }
}
