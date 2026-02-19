package jsjf;

public interface BinarySearchTreeADT<T extends Comparable<T>>
        extends BinaryTreeADT<T>, OrderedListADT<T> {
    /**
     * Remove all elements that match the target element.
     * 
     * @param targetElement Target element to match.
     * @throws ElementNotFoundException Thrown if target element does not exist in
     *                                  tree.
     */
    public void removeAll(T targetElement) throws ElementNotFoundException;
}
