package jsjf;

import java.util.Iterator;

public interface BinaryTreeADT<T> extends Iterable<T> {
    /**
     * Provide the root element of the tree.
     * 
     * @return Root element.
     */
    public T getRootElement();

    /**
     * Determines if the tree is empty.
     * 
     * @return True if the tree is empty, false if not.
     */
    public boolean isEmpty();

    /**
     * Determines the number of elements in the tree.
     *
     * @return The number of elements in the tree.
     */
    public int size();

    /**
     * Determines if the tree contains a particular elemenet.
     * 
     * @param targetElement The element to locate.
     * @return True if the element is in the tree, false if not.
     */
    public boolean contains(T targetElement);

    /**
     * Find the target element and return it.
     * 
     * @param targetElement Target element to find.
     * @return Found copy of the element.
     * @throws ElementNotFoundException Thrown if the element is not contained in
     *                                  the tree.
     */
    public T find(T targetElement) throws ElementNotFoundException;

    /**
     * Provide the default iterator for the tree which is an inorder iterator.
     * 
     * @return Default iterator for the tree.
     */
    public Iterator<T> iterator();

    /**
     * Provides an inorder iterator for the tree.
     * 
     * @return Inorder iterator.
     */
    public Iterator<T> iteratorInOrder();

    /**
     * Provides a preorder iterator for the tree.
     * 
     * @return Preorder iterator.
     */
    public Iterator<T> iteratorPreOrder();

    /**
     * Provides a postorder iterator for the tree.
     * 
     * @return Postorder iterator.
     */
    public Iterator<T> iteratorPostOrder();

    /**
     * Provides a level order iterator for the tree.
     * 
     * @return Level order iterator.
     */
    public Iterator<T> iteratorLevelOrder();
}
