import java.util.NoSuchElementException;

public class MyQueue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public void clear() {
        front = rear = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (front == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
    }

    public T poll() {
        if (front == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();

        queue.add("first");
        queue.add("second");
        queue.add("third");

        System.out.println("First element in the queue " + queue.peek());

        System.out.println("Size of the queue " + queue.size());

        while (queue.size() > 0) {
            System.out.println("Removed element " + queue.poll());
        }

        System.out.println("Is the queue empty " + (queue.size() == 0));

        queue.add("fourth");
        queue.add("fifth");

        queue.clear();

        System.out.println("Is the queue empty after clearing it " + (queue.size() == 0));
    }
}