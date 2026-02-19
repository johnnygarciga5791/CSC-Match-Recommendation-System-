package jsjf;

public interface QueueADT<T> {
    /**
     * Place the provided element at the rear of the queue.
     * 
     * @param element element to insert into the queue.
     */
    public void enqueue(T element);

    /**
     * Remove the front element from the queue and return it.
     * 
     * @return front element from the queue.
     * @throws EmptyCollectionException if queue is empty.
     */
    public T dequeue() throws EmptyCollectionException;

    /**
     * Return a copy of the first element from the queue without removing it.
     * 
     * @return front element from the queue.
     * @throws EmptyCollectionException if queue is empty.
     */
    public T first() throws EmptyCollectionException;

    /**
     * {@return true if the queue is empty, false otherwise.}
     */
    public boolean isEmpty();

    /**
     * {@return the number of elements in the queue.}
     */
    public int size();

    /**
     * Return a printable representation of the elements in the queue. toString is
     * not a queue-specific operation. It is included here as a reminder that it is
     * a best practice to provide toString for any class.
     * 
     * @return printable representation of the queue.
     */
    public String toString();
}
