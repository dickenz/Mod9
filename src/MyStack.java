import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class MyStack<T> {
    private T[] data;
    private int size;

    public MyStack() {
        data = createArray(10);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int size) {
        return (T[]) Array.newInstance(Object.class, size);
    }

    public void push(T value) {
        if (size == data.length) {
            T[] newData = createArray(data.length * 2);
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        data[size++] = value;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
    }

    public void clear() {
        data = createArray(10);
        size = 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data[size - 1];
    }

    @SuppressWarnings("unused")
    public T pop() {
        T value = peek();
        size--;
        data[size] = null;
        return value;
    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");

        System.out.println("Stack after push:");
        printStack(stack);

        stack.remove(2);

        System.out.println("Stack after remove(2):");
        printStack(stack);

        stack.clear();

        System.out.println("Stack after clear:");
        printStack(stack);
    }

    private static void printStack(MyStack<?> stack) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.data[i]);
        }
        System.out.println();
    }
}