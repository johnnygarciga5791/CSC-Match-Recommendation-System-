package jsjf;

import java.util.Arrays;

/**
 * ArrayStack implements the five operations of a stack (the "how") using an
 * array.
 */
public class ArrayStack<T> implements StackADT<T> {
    private final static int DEFAULT_CAPACITY = 100;

    private int top;
    private T[] stack;

    /**
     * Create an ArrayStack with the default capacity.
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Create an ArrayStack with the specified initial capacity.
     * 
     * @param initialCapacity initial capacity for ArrayStack.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        top = 0;
        stack = (T[]) new Object[initialCapacity];
    }

    @Override
    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity();
        }
        stack[top++] = element;
    }

    /**
     * Creates a new array with twice the capacity of the old one and copies
     * existing elements into the new array.
     */
    private void expandCapacity() {
        stack = Arrays.copyOf(stack, stack.length * 2);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyCollectionException("stack");
        }
        T result = stack[--top];
        stack[top] = null;
        return result;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyCollectionException("stack");
        }
        return stack[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public String toString() {
        String result = "top [";
        boolean first = true;

        for (int idx = top - 1; idx >= 0; idx--) {
            if (first) {
                first = false;
            } else {
                result += ",";
            }
            result += " " + stack[idx];
        }

        result += " ]";
        return result;
    }
}
