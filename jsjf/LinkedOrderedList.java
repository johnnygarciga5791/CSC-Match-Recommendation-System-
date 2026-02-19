package jsjf;

import java.io.Serializable;

public class LinkedOrderedList<T extends Comparable<T>> extends LinkedList<T>
        implements OrderedListADT<T>, Serializable {
    @Override
    public void add(T element) {
        mustNotBeNull(element);
        LinearNode<T> node = new LinearNode<T>(element);
        TargetLocation tl = new TargetLocation();
        int compareResult;

        //@formatter:off
        for (tl.previousNode = null, tl.targetNode = head;
                 tl.targetNode != null;
                 tl.previousNode = tl.targetNode, tl.targetNode = tl.targetNode.getNext()) {
            compareResult = tl.targetNode.getElement().compareTo(element);
            if (compareResult > 0) {
                break;
            }
        }
        //@formatter:on

        node.setNext(tl.targetNode);

        if (tl.previousNode == null) {
            head = node;
        } else {
            tl.previousNode.setNext(node);
        }

        if (tl.targetNode == null) {
            tail = node;
        }

        count++;
        changes++;
    }
}
