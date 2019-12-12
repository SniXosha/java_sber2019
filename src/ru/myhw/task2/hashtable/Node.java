package ru.myhw.task2.hashtable;

class Node<K, V> {
    private final K key;
    private final V value;

    private Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    static <K, V> Node<K,V> of(K key, V value) {
        return new Node<>(key, value);
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }
}
