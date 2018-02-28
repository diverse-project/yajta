package fr.inria.yajta.processor.util;

import java.util.Iterator;

public class MySet<T> implements Iterable<T> {

    private MyMap<Integer, T> dico = new MyMap<>();

    public MySet() {}

    public boolean contains(T el) {
        if(el == null) return false;
        return dico.containsKey(el.hashCode());
    }

    public void add(T el) {
        dico.put(el.hashCode(),el);
    }

    @Override
    public Iterator<T> iterator() {
        return dico.valueList().iterator();
    }

    public int size() {
        return dico.size();
    }
}
