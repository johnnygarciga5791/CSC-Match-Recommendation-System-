package jsjf;

public class CircularArrayQueue<T> implements QueueADT<T> {
    private static final int DEFAULT_CAPACITY = 100;
    private int front, rear, count;
    private T[] queue;

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int initialCapacity) {
        front = rear = count = 0;
        queue = (T[]) new Object[initialCapacity];
    }

    @Override
    public void enqueue(T element) {
        if (count == queue.length) {
            expandCapacity();
        }

        queue[rear] = element;
        rear = (rear + 1) % queue.length;
        count++;
    }

    private void expandCapacity() {
        @SuppressWarnings("unchecked")
        T[] larger = (T[]) new Object[queue.length * 2];

        /*
         * Transfer all of the elements out of the old array and into a new one. During
         * the transfer, we will move the front of the queue to 0 and the rear to count,
         * so these need to be reset when we switch out the old array to the new one.
         */
        for (int scan = 0; scan < count; scan++) {
            larger[scan] = queue[front];
            front = (front + 1) % queue.length;
        }

        front = 0;
        rear = count;
        queue = larger;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }

        T result = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;
        return result;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }

        return queue[front];
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
        String result = "front [";
        boolean first = true;

        for (int idx = front, remain = count; remain > 0; remain--) {
            if (first) {
                first = false;
            } else {
                result += ",";
            }
            result += " " + queue[idx];
            idx = (idx + 1) % queue.length;
        }

        result += " ] rear";
        return result;
    }
}
