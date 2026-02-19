package jsjf;

public interface OrderedListADT<T extends Comparable<T>> extends ListADT<T> {
    /**
     * Add an element to the list at its ordered location.
     * 
     * @param element The element to add.
     */
    public void add(T element);
}
