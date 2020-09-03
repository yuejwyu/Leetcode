package Laicode;

import java.util.Arrays;

public class MyHashMap<K, V> {
    public static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final float SCALE_FACTOR = 1.5f;

    private Node<K, V>[] array;
    private int size;
    private float loadFactor;

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive!");
        }
        this.array = (Node<K, V>[]) (new Node[capacity]);
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized void clear() {
        Arrays.fill(this.array, null);
        size = 0;
    }

    public synchronized boolean containsValue(V value) {
        if (isEmpty()) return false;
        for (Node<K, V> node : array) {
            while (node != null) {
                if (equalsValue(value, node.value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public synchronized boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (eqaulsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public synchronized V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (eqaulsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public synchronized V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = array[index];
        Node<K, V> node = head;
        while (node != null) {
            if (eqaulsKey(key, node.key)) {
                V res = node.value;
                node.value = value;
                return res;
            }
            node = node.next;
        }
        Node<K, V> newNode = new Node<K, V>(key, value);
        newNode.next = head;
        array[index] = newNode;
        size++;
        if (needRehashing()) rehashing();
        return null;
    }

    public synchronized V remove(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        Node<K, V> pre = null;
        while (node != null) {
            if (eqaulsKey(node.key, key)) {
                if (pre != null) {
                    pre.next = node.next;
                } else {
                    array[index] = node.next;
                }
                size--;
                return node.value;
            }
            pre = node;
            node = node.next;
        }
        return null;
    }

    private int hash(K key) {
        if (key == null) return 0;
        return key.hashCode() & 0x7FFFFFFF;
    }

    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    private boolean equalsValue(V v1, V v2) {
        return v1 == v2 || (v1 != null && v1.equals(v2));
    }

    private boolean eqaulsKey(K k1, K k2) {
        return k1 == k2 || (k1 != null && k1.equals(k2));
    }

    private boolean needRehashing() {
        float ratio = (size + 0f) / array.length;
        return ratio >= loadFactor;
    }

    private void rehashing() {
        Node<K, V>[] oldArray = array;
        this.array = (Node<K, V>[]) (new Node[(int) SCALE_FACTOR * array.length]);
        for (Node<K, V> node : oldArray) {
            while (node != null) {
                Node<K, V> next = node.next;
                int index = getIndex(node.key);
                node.next = array[index];
                array[index] = node;
                node = next;
            }
        }
    }
}
