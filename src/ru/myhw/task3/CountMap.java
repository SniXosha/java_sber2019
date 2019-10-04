package ru.myhw.task3;

import java.util.Map;

public interface CountMap<K> {

    void add(K key);

    int getCount(K key);

    int remove(K key);

    int size();

    void addAll(CountMap<? extends K> source);

    Map<? extends K, ? extends Integer> toMap();

    void toMap(Map<? super K, ? super Integer> destination);
}
