package jsjf;

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Add an element to the front of the list.
     * 
     * @param element The element to add.
     */
    public void addToFront(T element);

    /**
     * Add an element to the rear of the list.
     * 
     * @param element The element to add.
     */
    public void addToRear(T element);

    /**
     * Add an element to the list after a target element.
     * 
     * @param element The element to add.
     * @param target  The target after which to add the element.
     * @throws ElementNotFoundException Thrown if the target does not exist in the
     *                                  list.
     */
    public void addAfter(T element, T target);
}
