package ru.myhw.task2.hashtable;

class Node<K,V> {
    private final K key;
    private final V value;


    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }
}
