package jsjf;

public interface ListADT<T> extends Iterable<T> {
    /**
     * Remove the first element from the list.
     * 
     * @throws EmptyCollectionException Thrown if the list is empty.
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Remove the last element from the list.
     * 
     * @throws EmptyCollectionException Thrown if the list is empty.
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Remove a particular element from the list.
     * 
     * @param element The element to remove.
     * @throws ElementNotFoundException Thrown if the element is not in the list.
     */
    public T remove(T element) throws ElementNotFoundException;

    /**
     * Examine the element at the front of the list.
     * 
     * @return The element to remove.
     * @throws EmptyCollectionException Thrown if the list is empty.
     */
    public T first() throws EmptyCollectionException;

    /**
     * Examine the element at the rear of the list.
     * 
     * @return The element at the rear of the list.
     * @throws EmptyCollectionException Thrown if the list is empty.
     */
    public T last() throws EmptyCollectionException;

    /**
     * Determines if the list contains a particular element.
     * 
     * @param element The element to locate.
     * @return True if the element is in the list, false if not.
     */
    public boolean contains(T element);

    /**
     * Determines the number of elements on the list.
     * 
     * @return The number of elements on the list.
     */
    public int size();

    /**
     * Determines if the list is empty.
     * 
     * @return True if the list is empty, false if not.
     */
    public boolean isEmpty();

}
