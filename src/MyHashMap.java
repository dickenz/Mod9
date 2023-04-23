import java.util.Arrays;
public class MyHashMap<E, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private final Node<E, V>[] table;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        table = (Node<E, V>[]) new Node[capacity];
        size = 0;
    }

    public void put(E key, V value) {
        int index = hash(key);
        Node<E, V> newNode = new Node<>(key, value);
        Node<E, V> node = table[index];
        if (node == null) {
            table[index] = newNode;
            size++;
        } else {
            while (node.next != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }
            if (node.key.equals(key)) {
                node.value = value;
            } else {
                node.next = newNode;
                size++;
            }
        }
    }

    public void remove(E key) {
        int index = hash(key);
        Node<E, V> node = table[index];
        if (node != null && node.key.equals(key)) {
            table[index] = node.next;
            size--;
        } else {
            Node<E, V> prevNode = node;
            while (node != null && !node.key.equals(key)) {
                prevNode = node;
                node = node.next;
            }
            if (node != null) {
                prevNode.next = node.next;
                size--;
            }
        }
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(E key) {
        int index = hash(key);
        Node<E, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    private int hash(E key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private static class Node<E, V> {
        private final E key;
        private V value;
        private Node<E, V> next;

        private Node(E key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);

        int size = map.size();
        System.out.println("Size: " + size);

        Integer value1 = map.get(1);
        System.out.println("Value for key 1: " + value1);

        map.remove(2);
        System.out.println("Size after removing key 2: " + map.size());

        map.clear();
        System.out.println("Size after clearing: " + map.size());
    }

}
