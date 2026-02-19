package jsjf;

/**
 * LinkedStack implements the five operations of a stack (the "how") using
 * linear nodes.
 */
public class LinkedStack<T> implements StackADT<T> {
    int count;
    LinearNode<T> top;

    public LinkedStack() {
        top = null;
        count = 0;
    }

    @Override
    public void push(T element) {
        LinearNode<T> node = new LinearNode<T>();
        node.setElement(element);
        node.setNext(top);
        top = node;
        count++;
    }

    @Override
    public T pop() {
        T element;

        if (isEmpty()) {
            throw new EmptyCollectionException("stack");
        }
        element = top.getElement();
        top = top.getNext();
        count--;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyCollectionException("stack");
        }
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        String result = "top [";
        LinearNode<T> node;
        boolean first = true;

        for (node = top; node != null; node = node.getNext()) {
            if (first) {
                first = false;
            } else {
                result += ",";
            }
            result += " " + node.getElement();
        }

        result += " ]";
        return result;
    }

}
