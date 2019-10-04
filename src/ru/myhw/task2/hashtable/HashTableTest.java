package ru.myhw.task2.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void putAndGet() {
        int n = 25600;
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        for (int i = 0; i < n; ++i) {
            hashTable.put(i, i);
        }
        for (int i = 0; i < n; ++i) {
            assertEquals(i, hashTable.get(i));
        }
        assertEquals(n, hashTable.size());
    }

    @Test
    void putAndRemove() {
        int m = 12800;
        int n = 25600;
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        for (int i = 0; i < n; ++i) {
            hashTable.put(i, i);
        }
        for (int i = 0; i < m; ++i) {
            hashTable.remove(i);
        }
        for (int i = 0; i < m; ++i) {
            assertFalse(hashTable.contains(i));
        }
        for (int i = m; i < n; ++i) {
            assertEquals(i, hashTable.get(i));
        }
        assertEquals(n - m, hashTable.size());
    }

    @Test
    void putManyRemoveMany() {
        int n = 25600;
        int left = 2;
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        for (int i = 0; i < n; ++i) {
            hashTable.put(i, i);
        }
        for (int i = 0; i < n; ++i) {
            if (i != left)
                hashTable.remove(i);
        }
        assertEquals(1, hashTable.size());
        assertTrue(hashTable.contains(left));
        for (int i = 0; i < n; ++i) {
            if (i != left)
                assertFalse(hashTable.contains(i));
        }
    }

}