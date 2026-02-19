package jsjf;

import java.io.Serializable;

public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedListADT<T>, Serializable {
    @Override
    public void addToFront(T element) {
        mustNotBeNull(element);
        LinearNode<T> node = new LinearNode<T>(element);
        if (count == 0) {
            head = tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
        count++;
        changes++;
    }

    @Override
    public void addToRear(T element) {
        mustNotBeNull(element);
        LinearNode<T> node = new LinearNode<T>(element);
        if (count == 0) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        count++;
        changes++;
    }

    @Override
    public void addAfter(T element, T target) {
        mustNotBeNull(element);
        mustNotBeNull(target);
        TargetLocation tl = find(target);
        if (tl == null) {
            throw new ElementNotFoundException("LinkedUnorderedList");
        }
        LinearNode<T> node = new LinearNode<T>(element);
        node.setNext(tl.targetNode.getNext());
        tl.targetNode.setNext(node);
        // If new node added at the tail position, make it the new tail
        if (node.getNext() == null) {
            tail = node;
        }
        count++;
        changes++;
    }
}
