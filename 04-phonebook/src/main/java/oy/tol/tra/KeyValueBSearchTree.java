package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private TreeNode<K, V> root;
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
        String toReturn = "Tree has max depth of " + calculateDepth(root) + ".\n";
        toReturn += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        toReturn += "Min path height to bottom: " + visitor.minHeight + "\n";
        toReturn += "Max path height to bottom: " + visitor.maxHeight + "\n";
        toReturn += "Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n";
        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if(key==null||value==null){
            throw new IllegalArgumentException("the key and value can not be null");
        }
        int result=0;
        if(root==null){
            root=new TreeNode<K,V>(key, value);
            count++;
        }else{
            result=root.insert(key, value, key.hashCode());
        }
            .
        
        if(result==1){
            count++;
        }
        return true;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if(key==null){
            throw new IllegalArgumentException("the key can not be null");
        }
        return root.find(key, key.hashCode());
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
 
    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
        root.accept(visitor);
        Pair<K, V>[] sorted = visitor.getArray();
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @Override
    public void compress() throws OutOfMemoryError {

    private int calculateDepth(TreeNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = calculateDepth(node.left);
        int rightDepth = calculateDepth(node.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}

