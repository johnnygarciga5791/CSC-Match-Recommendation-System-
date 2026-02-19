package jsjf;

/**
 * You will complete this class as part of a lab activity.
 */
public class ArrayQueue<T> implements QueueADT<T> {
    private final static int DEFAULT_CAPACITY = 100000;
    private T[] queue;
    private int rear;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        rear = 0;
        queue = (T[]) new Object[initialCapacity];
    }

    @Override
    public void enqueue(T element) {
        queue[rear++] = element;
    }

    @Override
    public T dequeue() {

        // Remove the following line when you implement this method.

        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }

        T result = queue[0];

        for (int i = 0; i < rear - 1; ++i) {
            queue[i] = queue[i + 1];
        }

        queue[--rear] = null;
        return result;
    }

    @Override
    public T first() {

        // Remove the following line when you implement this method.

        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }
        return queue[0];
    }

    @Override
    public boolean isEmpty() {
        // Remove the following line when you implement this method.
        return rear == 0;
    }

    @Override
    public int size() {
        // Remove the following line when you implement this method.
        return rear;
    }

    @Override
    public String toString() {
        String result = "front [";
        boolean first = true;

        for (int idx = 0; idx < rear; idx++) {
            if (first) {
                first = false;
            } else {
                result += ",";
            }
            result += " " + queue[idx];
        }
        result += " ] rear";
        return result;
    }
}
