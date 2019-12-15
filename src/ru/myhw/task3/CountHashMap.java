package ru.myhw.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CountHashMap<K> implements CountMap<K> {

    private HashMap<K, Integer> hashMap;

    CountHashMap() {
        this.hashMap = new HashMap<>();
    }

    @Override
    public void add(K key) {
        hashMap.merge(key, 1, Integer::sum);
    }

    @Override
    public int getCount(K key) {
        return Objects.requireNonNullElse(hashMap.get(key), 0);
    }

    @Override
    public int remove(K key) {
        return Objects.requireNonNullElse(hashMap.remove(key), 0);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public void addAll(CountMap<? extends K> source) {
        source.toMap().forEach((key, value) -> hashMap.merge(key, value, Integer::sum));
    }

    @Override
    public Map<K, Integer> toMap() {
        HashMap<K, Integer> countMap = new HashMap<>();
        toMap(countMap);
        return countMap;
    }

    @Override
    public void toMap(Map<? super K, ? super Integer> destination) {
        destination.putAll(hashMap);
    }

}
