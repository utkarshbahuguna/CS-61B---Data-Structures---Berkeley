// https://fa20.datastructur.es/materials/lab/lab8/lab8

import java.util.*;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Pair<K, V> {
        K key;
        V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    LinkedList<Pair<K, V>>[] table;
    double loadFactor;
    int size;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.loadFactor = loadFactor;
        this.size = 0;
        table = (LinkedList<Pair<K, V>>[]) new LinkedList[initialSize];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    /** Returns the key-value pair with key if present in this map, otherwise returns null. */
    private Pair<K, V> pairWithKey(K key) {
        LinkedList<Pair<K, V>> bucket = table[Math.floorMod(key.hashCode(), table.length)];
        for (Pair<K, V> p : bucket) {
            if (p.key.equals(key)) {
                return p;
            }
        }
        return null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return pairWithKey(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        for (Pair<K, V> p : table[Math.floorMod(key.hashCode(), table.length)]) {
            if (p.key.equals(key)) {
                return p.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            pairWithKey(key).value = value;
            return;
        }
        if(((double) size + 1) / table.length > loadFactor) {
            resize(2);
        }
        LinkedList<Pair<K, V>> bucket = table[Math.floorMod(key.hashCode(), table.length)];
        bucket.add(new Pair(key, value));
        size += 1;
    }

    /** Resizes the hash table to the given factor. */
    private void resize(double factor) {
        Set<Pair<K, V>> SetOfPairs = pairs();
        table = (LinkedList<Pair<K, V>>[]) new LinkedList[(int) Math.ceil(table.length * factor)];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
        for (Pair<K, V> p : SetOfPairs) {
            put(p.key, p.value);
        }
    }

    /** Returns a set of all the key value pairs contained in this map. */
    private Set<Pair<K, V>> pairs() {
        Set<Pair<K, V>> pairs = new HashSet<>();
        for(LinkedList<Pair<K, V>> bucket : table) {
                pairs.addAll(bucket);
            }
        return pairs;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Pair<K, V> p : pairs()) {
            keys.add(p.key);
        }
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Returns the value associated with key, if key doesn't exist in map, return null.
     */
    @Override
    public V remove(K key) {
        return remove(key, get(key));
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Returns the value associated with key regardless, null if key doesn't exist.
     */
    @Override
    public V remove(K key, V value) {
        if (containsKey(key)) {
            LinkedList<Pair<K, V>> bucket = table[Math.floorMod(key.hashCode(), table.length)];
            Pair<K, V> p = pairWithKey(key);
            if(p.value == value) {
                bucket.remove(p);
            }
            return p.value;
        }
        return null;
    }

    private class HashMapIterator implements Iterator<K> {
        int nextBucket;
        int processed = 0;
        List<K> keys = new ArrayList<>();

        public HashMapIterator() {
            nextBucket = 0;
            addBucket();
        }

        public void addBucket() {
            while (keys.size() == 0 && nextBucket < table.length) {
                for (Pair<K, V> p : table[nextBucket]) {
                    keys.add(p.key);
                }
                nextBucket += 1;
            }
        }

        @Override
        public boolean hasNext() {
            return processed < size;
        }

        @Override
        public K next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            K next = keys.remove(0);
            processed += 1;
            if (hasNext()) { addBucket(); }
            return next;
        }
    }

    /** Returns an iterator over the keys of this map. */
    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }


    public static void main(String[] args) {
        MyHashMap<String, Integer> b = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            b.put("hi" + i, i);
        }
        for (String key : b) {
            System.out.println(key + " : " + b.get(key));
        }
    }
}
