package se.kth.castor.yajta.processor.util;

import java.util.Arrays;

public class MyStack<E> {
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private Object elements[];

    public MyStack() {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(E e) {
        if (size == elements.length) {
            ensureCapa();
        }
        elements[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        E e = (E) elements[--size];
        elements[size] = null;
        return e;
    }

    public int size() {
        return size;
    }

    public E peek() {
        if(size != 0)
            return (E) elements[size-1];
        else return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapa() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }
}
