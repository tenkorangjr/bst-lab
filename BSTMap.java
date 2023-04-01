import java.util.ArrayList;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class BSTMap<K, V> implements MapSet<K, V> {

    private static class Node<K, V> extends KeyValuePair<K, V> {
        Node<K, V> left, right;

        public Node(K key, V value) {
            super(key, value);
            left = null;
            right = null;
        }
    }

    private int size;
    private Node<K, V> root;
    private Comparator<K> comparator;

    public BSTMap(Comparator<K> comparator) {
        size = 0;
        root = null;
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = new Comparator<K>() {

                @Override
                public int compare(K o1, K o2) {
                    return ((Comparable<K>) o1).compareTo(o2);
                }

            };
        }
    }

    public BSTMap() {
        this(null);
    }

    @Override
    public V put(K key, V value) {
        if (size == 0) {
            root = new Node<>(key, value);
            size++;
            return null;
        } else {
            return put(key, value, root);
        }
    }

    public V put(K key, V value, Node<K, V> curNode) {
        if (comparator.compare(key, curNode.getKey()) < 0) {
            if (curNode.left == null) {
                curNode.left = new Node<>(key, value);
                size++;
                return null;
            } else {
                return put(key, value, curNode.left);
            }
        } else if (comparator.compare(key, curNode.getKey()) > 0) {
            if (curNode.right == null) {
                curNode.right = new Node<>(key, value);
                size++;
                return null;
            } else {
                return put(key, value, curNode.right);
            }
        } else {
            value = curNode.getValue();
            curNode.setValue(value);
            return value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null){
            return false;
        }

        if ()
    }

    @Override
    public V get(K key) {
        if (key == root.getKey()) {
            return root.getValue();
        } else {
            return get(key, root);
        }
    }

    private V get(K key, Node<K, V> cur) {
        if (comparator.compare(key, cur.getKey()) < 0) {
            if (cur.left != null) {
                return get(key, cur.left);
            } else {
                return null;
            }
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            if (cur.right != null) {
                return get(key, cur.right);
            } else {
                return null;
            }
        } else {
            return cur.getValue();
        }
    }

    @Override
    public V remove(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public ArrayList<K> keySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keySet'");
    }

    @Override
    public ArrayList<V> values() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    @Override
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public int maxDepth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxDepth'");
    }
}