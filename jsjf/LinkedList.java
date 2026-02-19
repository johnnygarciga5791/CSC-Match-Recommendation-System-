package jsjf;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LinkedList<T> implements ListADT<T>, Iterable<T>, Serializable {
    protected int count;
    protected int changes;
    protected LinearNode<T> head, tail;

    public LinkedList() {
        count = 0;
        head = tail = null;
        changes = 0;
    }

    @Override
    public T removeFirst() {
        mustNotBeEmpty();
        T removedNode = head.getElement();
        count--;
        if (count == 0) {
            head = tail = null;
        } else {
            head = head.getNext();
        }
        changes++;
        return removedNode;
    }

    @Override
    public T removeLast() {
        mustNotBeEmpty();
        T removedNode = tail.getElement();
        count--;
        if (count == 0) {
            head = tail = null;
        } else {
            TargetLocation tl = find(count);
            tl.previousNode.setNext(null);
            tail = tl.previousNode;
        }
        changes++;
        return removedNode;
    }

    @Override
    public T remove(T element) {
        TargetLocation tl = find(element);

        if (tl == null) {
            throw new ElementNotFoundException("list");
        }
        count--;
        if (count == 0) {
            head = tail = null;
        } else {
            if (tl.previousNode == null) {
                head = head.getNext();
            } else {
                tl.previousNode.setNext(tl.targetNode.getNext());
                if (tail == tl.targetNode) {
                    tail = tl.previousNode;
                }
            }
        }
        changes++;
        return tl.targetNode.getElement();
    }

    @Override
    public T first() {
        mustNotBeEmpty();
        return head.getElement();
    }

    @Override
    public T last() {
        mustNotBeEmpty();
        return tail.getElement();
    }

    @Override
    public boolean contains(T element) {
        return find(element) != null;
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
        String result = "head [";
        boolean first = true;

        for (T element : this) {
            if (first) {
                first = false;
            } else {
                result += ",";
            }
            result += " " + element;
        }

        result += " ] tail";
        return result;
    }

    protected void mustNotBeEmpty() {
        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        }
    }

    protected void mustNotBeNull(T element) {
        if (element == null) {
            throw new NullPointerException("LinkedList null element not allowed");
        }
    }

    protected TargetLocation find(int n) {
        int i;
        TargetLocation tl = new TargetLocation();

        for (i = 0, tl.previousNode = null, tl.targetNode = head; i < n
                && tl.targetNode != null; i++, tl.previousNode = tl.targetNode, tl.targetNode = tl.targetNode
                        .getNext()) {
        }
        return tl.targetNode != null ? tl : null;
    }

    protected TargetLocation find(T element) {
        TargetLocation tl = new TargetLocation();

        for (tl.previousNode = null, tl.targetNode = head; tl.targetNode != null && !tl.targetNode.getElement()
                .equals(element); tl.previousNode = tl.targetNode, tl.targetNode = tl.targetNode.getNext()) {
        }
        return tl.targetNode != null ? tl : null;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    protected class TargetLocation {
        public LinearNode<T> previousNode = null;
        public LinearNode<T> targetNode = null;
    }

    private class LinkedListIterator implements Iterator<T> {
        private int iteratorChanges;
        private LinearNode<T> previousNode;
        private LinearNode<T> currentNode;
        private LinearNode<T> nextNode;
        private boolean removed;

        public LinkedListIterator() {
            iteratorChanges = changes;
            previousNode = null;
            currentNode = null;
            nextNode = head;
            removed = true;
        }

        public boolean hasNext() throws ConcurrentModificationException {
            if (iteratorChanges != changes) {
                throw new ConcurrentModificationException();
            }

            return nextNode != null;
        }

        public T next() throws ConcurrentModificationException {
            if (!hasNext())
                throw new NoSuchElementException();

            if (!removed) {
                previousNode = currentNode;
            }
            currentNode = nextNode;
            nextNode = nextNode.getNext();
            removed = false;
            return currentNode.getElement();
        }

        public void remove() throws ConcurrentModificationException, IllegalStateException {
            if (iteratorChanges != changes) {
                throw new ConcurrentModificationException();
            }

            if (removed) {
                throw new IllegalStateException("LinkedList");
            }

            // If previousNode is null, this will be the new front
            if (previousNode == null) {
                head = nextNode;
            } else {
                previousNode.setNext(nextNode);
            }

            // Make a note that remove has been called in case the user tries
            // to do this twice without first calling next()
            removed = true;

            // If the current node is the rear, then the previous node is the new rear
            if (tail == currentNode) {
                tail = previousNode;
            }

            count--;
            changes++;
            iteratorChanges = changes;
        }
    }
}
