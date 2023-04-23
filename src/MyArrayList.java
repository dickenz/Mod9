import java.util.Arrays;

public class MyArrayList<E> {
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[10];
        size = 0;
    }

    public void add(E value) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = value;
    }

    private void resize() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.clear();
        list.add("John");
        list.add("Bill");
        list.add("Julie");
        list.remove(0);
        System.out.println(list.get(0));
        System.out.println(list.size());
    }
}