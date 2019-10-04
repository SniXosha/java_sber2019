package ru.myhw.task2;

import ru.myhw.task2.hashtable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer, String> hashTable = new HashTable<>();
        hashTable.put(5, "1");
        hashTable.put(3, "2");
        hashTable.remove(5);
        System.out.println(hashTable.contains(3));
    }
}
