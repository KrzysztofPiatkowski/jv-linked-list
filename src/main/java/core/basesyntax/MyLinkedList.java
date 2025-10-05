package core.basesyntax;

import java.util.List;
import java.util.Objects;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node<T> newNode = new Node<T>(value);
        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            } else {
                tail = newNode;
            }
            head = newNode;
        } else if (index == size) {
            add(value);
        } else {
            if (index < size / 2) {
                Node<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                Node<T> prev = current.prev;
                newNode.prev = prev;
                newNode.next = current;
                prev.next = newNode;
                current.prev = newNode;
            } else {
                Node<T> current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
                Node<T> prev = current.prev;
                newNode.prev = prev;
                newNode.next = current;
                prev.next = newNode;
                current.prev = newNode;
            }
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (T element : list) {
            add(element);
        }
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        if (index < size / 2) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        } else {
            Node<T> current = tail;
            for (int i = size - 1; i > index ; i--) {
                current = current.prev;
            }
            return current.value;
        }
    }

    @Override
    public T set(T value, int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        if (index < size / 2) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            T oldValue = current.value;
            current.value = value;
            return oldValue;
        } else {
            Node<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            T oldValue = current.value;
            current.value = value;
            return oldValue;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        T oldValue = current.value;

        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }

        size--;
        return oldValue;
    }


    @Override
    public boolean remove(T object) {
        Node<T> current = head;

        while (current != null) {
            if (Objects.equals(current.value, object)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
