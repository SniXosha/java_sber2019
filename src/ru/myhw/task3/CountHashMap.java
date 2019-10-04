package ru.myhw.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CountHashMap<K> implements CountMap<K> {

    private HashMap<K, Integer> hashMap;

    CountHashMap() {
        this.hashMap = new HashMap<K, Integer>();
    }

    @Override
    public void add(K key) {
        hashMap.merge(key, 1, Integer::sum);
    }

    @Override
    public int getCount(K key) {
        Integer count = hashMap.get(key);
        return Objects.requireNonNullElse(count, 0);
    }

    @Override
    public int remove(K key) {
        Integer count = hashMap.remove(key);
        return Objects.requireNonNullElse(count, 0);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public void addAll(CountMap<? extends K> source) {
        Map<? extends K, ? extends Integer> sourceMap = source.toMap();
        for (Map.Entry<? extends K, ? extends Integer> entry : sourceMap.entrySet()) {
            hashMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
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
