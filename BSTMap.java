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
        if (key == null) {
            return false;
        }

        return containsKey(key, root);
    }

    private boolean containsKey(K key, Node<K, V> curNode) {
        if (comparator.compare(key, curNode.getKey()) > 0) {
            if (curNode.right != null) {
                return containsKey(key, curNode.right);
            } else {
                return false;
            }
        } else if (comparator.compare(key, curNode.getKey()) < 0) {
            if (curNode.left != null) {
                return containsKey(key, curNode.left);
            } else {
                return false;
            }
        } else {
            return true;
        }
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

    private Node<K, V> getNode(K key, Node<K, V> cur) {
        if (comparator.compare(key, cur.getKey()) < 0) {
            if (cur.left != null) {
                return getNode(key, cur.left);
            } else {
                return null;
            }
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            if (cur.right != null) {
                return getNode(key, cur.right);
            } else {
                return null;
            }
        } else {
            return cur;
        }
    }

    @Override
    public V remove(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public ArrayList<K> keySet() {
        if (size() == 0) {
            return new ArrayList<>();
        }

        ArrayList<K> output = new ArrayList<>();
        return keySet(root, output);
    }

    private ArrayList<K> keySet(Node<K, V> curNode, ArrayList<K> output) {
        if (curNode == null) {
            return null;
        }

        keySet(curNode.left, output);
        output.add(curNode.getKey());
        keySet(curNode.right, output);

        return output;
    }

    @Override
    public ArrayList<V> values() {
        if (size() == 0) {
            return new ArrayList<>();
        }

        ArrayList<V> output = new ArrayList<>();
        return values(root, output);
    }

    private ArrayList<V> values(Node<K, V> curNode, ArrayList<V> output) {
        if (curNode == null) {
            return null;
        }

        values(curNode.left, output);
        output.add(curNode.getValue());
        values(curNode.right, output);

        return output;
    }

    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        if (size() == 0) {
            return new ArrayList<>();
        }

        ArrayList<KeyValuePair<K, V>> output = new ArrayList<>();
        return entrySet(root, output);
    }

    private ArrayList<KeyValuePair<K, V>> entrySet(Node<K, V> curNode, ArrayList<KeyValuePair<K, V>> output) {
        if (curNode == null) {
            return null;
        }

        entrySet(curNode.left, output);
        output.add(new KeyValuePair<K, V>(curNode.getKey(), curNode.getValue()));
        entrySet(curNode.right, output);

        return output;
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
        if (size == 0) {
            return 0;
        }

        int curDepth = 0;
        return maxDepth(root, ++curDepth, ++curDepth);
    }

    private int maxDepth(Node<K, V> curNode, int curDepth, int curMax) {
        if (curNode == null) {
            curDepth--;

            if (curDepth > curMax) {
                curMax = curDepth;
            }

            return curMax;
        }

        int leftMax = maxDepth(curNode.left, ++curDepth, curMax);
        curDepth--;
        int rightMax = maxDepth(curNode.right, ++curDepth, curMax);

        return Math.max(leftMax, rightMax);
    }

    public String toString() {

    }

    public static void main(String[] args) {
        BSTMap<Integer, String> myTree = new BSTMap<>();

        myTree.put(4, "Michael");
        myTree.put(2, "Tracy");
        myTree.put(3, "Vanessa");
        myTree.put(1, "Vanessa");
        myTree.put(6, "Vanessa");
        myTree.put(5, "Vanessa");

        System.out.println(myTree.maxDepth());
    }
}