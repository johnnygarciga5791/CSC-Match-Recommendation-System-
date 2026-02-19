package jsjf;

/**
 * StackADT defines the basic operations (the "what") of a stack.
 */
public interface StackADT<T> {
    /**
     * Place the provided element on the top of the stack.
     * 
     * @param element element to push on to the stack.
     */
    public void push(T element);

    /**
     * Remove the top element from the stack and return it.
     * 
     * @return the top element from the stack.
     * @throws EmptyCollectionException if stack is empty.
     */
    public T pop() throws EmptyCollectionException;

    /**
     * Return a copy of the top element from the stack without removing it.
     * 
     * @return the top element from the stack.
     * @throws EmptyCollectionException if stack is empty.
     */
    public T peek() throws EmptyCollectionException;

    /**
     * {@return true if the stack is empty, false otherwise.}
     */
    public boolean isEmpty();

    /**
     * {@return the number of elements on the stack.}
     */
    public int size();

    /**
     * Return a printable representation of the elements on the stack. toString is
     * not a Stack-specific operation. It is included here as a reminder that it is
     * a best practice to provide toString for any class.
     * 
     * @return printable representation of the stack.
     */
    public String toString();
}
