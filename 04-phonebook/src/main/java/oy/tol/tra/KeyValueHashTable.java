package oy.tol.tra;

import java.util.Arrays;

public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private Pair<K, V>[] values = null;
    private int count = 0;
    private int collisionCount = 0;
    private int maxProbingSteps = 0;
    private int reallocationCount = 0;
    private static final double LOAD_FACTOR = 0.45;
    private static final int DEFAULT_CAPACITY = 1000;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        values = (Pair<K, V>[]) new Pair[(int) ((double) capacity * (1.0 + LOAD_FACTOR))];
        reallocationCount = 0;
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hash table load factor is %.2f%n", LOAD_FACTOR));
        builder.append(String.format("Hash table capacity is %d%n", values.length));
        builder.append(String.format("Current fill rate is %.2f%%%n", (count / (double)values.length) * 100.0));
        builder.append(String.format("Hash table had %d collisions when filling the hash table.%n", collisionCount));
        builder.append(String.format("Hash table had to probe %d times in the worst case.%n", maxProbingSteps));
        builder.append(String.format("Hash table had to reallocate %d times.%n", reallocationCount));
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }
        if (((double) count * (1.0 + LOAD_FACTOR)) >= values.length) {
            reallocate((int) ((double) (values.length) * (1.0 / LOAD_FACTOR)));
        }

        int index = Math.abs(key.hashCode()) % values.length;
        if (values[index] == null) {
            values[index] = new Pair<>(key, value);
            count++;
        } else if (values[index].getKey().equals(key)) {
            values[index].setValue(value);
        } else {
            for (int i = index + 1; i < values.length; i++) {
                if (values[i] == null) {
                    values[i] = new Pair<>(key, value);
                    count++;
                    break;
                } else if (values[i].getKey().equals(key)) {
                    values[i].setValue(value);
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        int index = Math.abs(key.hashCode()) % values.length;
        if (values[index] == null) {
            return null;
        } else if (values[index].getKey().equals(key)) {
            return values[index].getValue();
        } else {
            for (int i = index + 1; i < values.length; i++) {
                if (values[i] == null) {
                    return null;
                } else if (values[i].getKey().equals(key)) {
                    return values[i].getValue();
                }
            }
        }
        return null;
    }

    private void reallocate(int newCapacity) throws OutOfMemoryError {
        Pair<K, V>[] oldValues = values;
        values = (Pair<K, V>[]) new Pair[newCapacity];
        reallocationCount++;
        count = 0;
        for (Pair<K, V> pair : oldValues) {
            if (pair != null) {
                add(pair.getKey(), pair.getValue());
            }
        }
    }
}

