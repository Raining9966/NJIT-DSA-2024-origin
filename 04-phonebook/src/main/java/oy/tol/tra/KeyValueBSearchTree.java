package oy.tol.tra;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private TreeNode<K, V> root = null;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        StringBuilder status = new StringBuilder("Tree has max depth of " + maxTreeDepth + ".\n");
        status.append("Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n");
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        status.append("Min path height to bottom: " + visitor.minHeight + "\n");
        status.append("Max path height to bottom: " + visitor.maxHeight + "\n");
        status.append("Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n");
        return status.toString();
    }

    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }
        if (root == null) {
            root = new TreeNode<>(key, value);
            count++;
            return true;
        } else {
            count += root.insert(key, value, key.hashCode());
            return true;
        }
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
    }

    public Pair<K, V>[] toSortedArray() {
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
        root.accept(visitor);
        Pair<K, V>[] sorted = visitor.getArray();
        Algorithms.mergeSort(sorted);
        return sorted;
    }

    public V find(K key) {
        return root.find(key, key.hashCode());
    }

    @Override
    public void compress() throws OutOfMemoryError {
    }
}

