package jsjf;

import java.util.Iterator;

/**
 * LinkedBinaryTree implements the BinaryTreeInterface with links. Adapted from
 * code by Lewis and Chase.
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected BinaryTreeNode<T> root;

    public LinkedBinaryTree() {
        root = null;
    }

    protected BinaryTreeNode<T> getRootNode() throws EmptyCollectionException {
        if (root != null) {
            return root;
        }

        throw new EmptyCollectionException("LinkedBinaryTree");
    }

    @Override
    public T getRootElement() throws EmptyCollectionException {
        return getRootNode().getElement();
    }

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public int size() {
        int s = 0;

        if (root != null) {
            s = root.numChildren() + 1;
        }

        return s;
    }

    public int getHeight() {
        int h = 0;

        if (root != null) {
            h = root.getHeight();
        }

        return h;
    }

    @Override
    public boolean contains(T targetElement) {
        return findNode(targetElement, root) != null;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findNode(targetElement, root);

        if (current == null) {
            throw new ElementNotFoundException("LinkedBinaryTree");
        }

        return current.getElement();
    }

    private BinaryTreeNode<T> findNode(T targetElement, BinaryTreeNode<T> next) {
        if (next == null)
            return null;

        if (next.getElement().equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findNode(targetElement, next.getLeft());

        if (temp == null) {
            temp = findNode(targetElement, next.getRight());
        }

        return temp;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("inorder [");
        boolean first = true;
        Iterator<T> iter = iteratorInOrder();

        while (iter.hasNext()) {
            T element = iter.next();
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(" ").append(element);
        }
        result.append(" ]");
        return result.toString();
    }

    // All of these iterators create an UnorderedListADT with the element values
    // in the relevant order. The use of this separate collection makes these
    // iterators all fail-safe, not fail-fast, since the underlying ArrayList is
    // not exposed and thus cannot be corrupted by other changes to the tree.

    @Override
    public Iterator<T> iterator() {
        return iteratorInOrder();
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedListADT<T> tempList = new LinkedUnorderedList<>();
        inOrder(root, tempList);
        return new TreeIterator(tempList.iterator());
    }

    protected void inOrder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            inOrder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inOrder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedListADT<T> tempList = new LinkedUnorderedList<>();
        preOrder(root, tempList);
        return new TreeIterator(tempList.iterator());
    }

    protected void preOrder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preOrder(node.getLeft(), tempList);
            preOrder(node.getRight(), tempList);
        }
    }

    public Iterator<T> iteratorPostOrder() {
        UnorderedListADT<T> tempList = new LinkedUnorderedList<>();
        postOrder(root, tempList);
        return new TreeIterator(tempList.iterator());
    }

    protected void postOrder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            postOrder(node.getLeft(), tempList);
            postOrder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    public Iterator<T> iteratorLevelOrder() {
        UnorderedListADT<BinaryTreeNode<T>> nodes = new LinkedUnorderedList<>();
        UnorderedListADT<T> tempList = new LinkedUnorderedList<>();
        BinaryTreeNode<T> current;

        nodes.addToRear(root);

        while (!nodes.isEmpty()) {
            current = nodes.removeFirst();

            if (current != null) {
                tempList.addToRear(current.getElement());
                if (current.getLeft() != null)
                    nodes.addToRear(current.getLeft());
                if (current.getRight() != null)
                    nodes.addToRear(current.getRight());
            } else {
                tempList.addToRear(null);
            }
        }

        return new TreeIterator(tempList.iterator());
    }

    // We wrap this inner class around the UnorderedListADT iterator that was
    // created by the various tree iterators. The reason for this separate
    // class is to block the remove since we are working on a copy and don't
    // want the user to think that remove's success means the node was removed
    // from the tree.
    private class TreeIterator implements Iterator<T> {
        private Iterator<T> iter;

        public TreeIterator(Iterator<T> iter) {
            this.iter = iter;
        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public T next() {
            return iter.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
