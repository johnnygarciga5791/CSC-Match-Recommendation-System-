package jsjf;

// We will cover what it means to be Serializable later in the course.
import java.io.Serializable;

/**
 * Represents a node in a linear linked list.
 */
public class LinearNode<T> implements Serializable {
    private LinearNode<T> next;
    private T element;

    /** Create an empty node. */
    public LinearNode() {
        next = null;
        element = null;
    }

    /**
     * Create a node containing the specified element.
     * 
     * @param elem element to be stored.
     */
    public LinearNode(T elem) {
        next = null;
        element = elem;
    }

    /** {@return the node that follows this one.} */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     * Set the node that follows this one.
     * 
     * @param node node to follow this one.
     */
    public void setNext(LinearNode<T> next) {
        this.next = next;
    }

    /** {@return the element stored in this node.} */
    public T getElement() {
        return element;
    }

    /**
     * Set the element stored in this node.
     * 
     * @param element element to store in this node.
     */
    public void setElement(T element) {
        this.element = element;
    }
}
