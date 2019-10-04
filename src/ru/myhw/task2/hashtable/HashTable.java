package ru.myhw.task2.hashtable;

public class HashTable<K, V> {

    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int DEFAULT_CAPACITY = 16;

    private int capacity;
    private int size;
    private final double maxLoadFactor;
    private Node<K, V>[] storage;

    public HashTable(int capacity, double maxLoadFactor) {
        this.size = 0;
        this.capacity = capacity;
        this.maxLoadFactor = maxLoadFactor;
        initStorage();
    }

    public HashTable(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public void put(K key, V value) {
        int ind = findElement(storage, key);
        if (storage[ind] != null) {
            storage[ind] = new Node<>(key, value);
            return;
        }
        double currentLoadFactor = (double) (size + 1) / capacity;
        if (currentLoadFactor > maxLoadFactor) {
            rehash();
            ind = findElement(storage, key);
        }
        storage[ind] = new Node<>(key, value);
        size++;
    }

    public V get(K key) {
        int ind = findElement(storage, key);
        if (storage[ind] == null) {
            return null;
        } else {
            return storage[ind].getValue();
        }
    }

    public void remove(K key) {
        int ind = findElement(storage, key);
        if (storage[ind] == null) {
            return;
        }
        int toDelete = ind;
        int toMove = ind;
        boolean finished = false;
        while (true) {
            storage[toDelete] = null;
            int hash;
            do {
                toMove = (toMove + 1) % capacity;
                if (storage[toMove] == null) {
                    finished = true;
                    break;
                }
                hash = getHashIndex(storage[toMove].getKey(), capacity);
            } while ((toDelete <= toMove) ?
                    (toDelete < hash && hash <= toMove) :
                    (toDelete < hash || hash <= toMove));
            if (finished)
                break;
            storage[toDelete] = storage[toMove];
            toDelete = toMove;
        }
        size--;
    }

    public boolean contains(K key) {
        int ind = findElement(storage, key);
        return storage[ind] != null;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void initStorage() {
        this.storage = new Node[capacity];
    }

    private int getHashIndex(Object element, int n) {
        int hash = element.hashCode();
        return (hash * hash) % n;
    }

    private int findElement(Node<K, V>[] s, K key) {
        int ind = getHashIndex(key, s.length);
        while (s[ind] != null && !s[ind].getKey().equals(key)) {
            ind = (ind + 1) % s.length;
        }
        return ind;
    }

    private void insertIntoStorage(Node<K, V>[] s, Node<K, V> kvNode) {
        int ind = findElement(s, kvNode.getKey());
        s[ind] = kvNode;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int newCapacity = capacity * 2;
        Node<K, V>[] newStorage = new Node[newCapacity];

        for (int i = 0; i < capacity; i++) {
            if (storage[i] != null)
                insertIntoStorage(newStorage, storage[i]);
        }
        storage = newStorage;
        capacity = newCapacity;
    }



}
