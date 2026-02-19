package jsjf;

/**
 * LinkedBinarySearchTree implements the BinarySearchTreeInterface and
 * OrderedListInterface with links. Adapted from code by Lewis and Chase.
 */
public class LinkedBinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T>
        implements BinarySearchTreeADT<T> {

    public LinkedBinarySearchTree() {
        super();
    }

    @Override
    public void add(T element) {
        if (isEmpty()) {
            root = new BinaryTreeNode<T>(element);
        } else {
            add(element, root);
        }
    }

    private void add(T element, BinaryTreeNode<T> node) {
        if (element.compareTo(node.getElement()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryTreeNode<T>(element));
            } else {
                add(element, node.getLeft());
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new BinaryTreeNode<T>(element));
            } else {
                add(element, node.getRight());
            }
        }
    }

    @Override
    public T remove(T targetElement) throws ElementNotFoundException {
        return remove(targetElement, root, null);
    }

    private T remove(T targetElement, BinaryTreeNode<T> node, BinaryTreeNode<T> parent)
            throws ElementNotFoundException {
        T result = null;
        int compare;

        if (node == null) {
            throw new ElementNotFoundException("LinkedBinarySearchTree");
        }

        compare = targetElement.compareTo(node.element);
        if (compare == 0) {
            result = node.element;
            BinaryTreeNode<T> temp = replacement(node);
            // If we are at the root of the tree (parent null), just replace the root
            if (parent == null) {
                root = temp;
            } else {
                if (parent.right == node) {
                    parent.right = temp;
                } else {
                    parent.left = temp;
                }
            }
        } else {
            parent = node;
            if (compare < 0) {
                result = remove(targetElement, node.getLeft(), parent);
            } else {
                result = remove(targetElement, node.getRight(), parent);
            }
        }

        return result;
    }

    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;

        if ((node.left == null) && (node.right == null)) {
            result = null;
        } else if ((node.left != null) && (node.right == null)) {
            result = node.left;
        } else if ((node.left == null) && (node.right != null)) {
            result = node.right;
        } else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;

            while (current.left != null) {
                parent = current;
                current = current.left;
            }

            current.left = node.left;
            if (node.right != current) {
                parent.left = current.right;
                current.right = node.right;
            }

            result = current;
        }

        return result;
    }

    @Override
    public void removeAll(T targetElement) throws ElementNotFoundException {
        boolean found = false;

        try {
            while (true) {
                remove(targetElement);
                found = true;
            }
        } catch (ElementNotFoundException e) {
            // If we didn't find any value, rethrow the exception
            if (!found) {
                throw e;
            }
        }
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        T result = null;

        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedBinarySearchTree");
        }

        if (root.left == null) {
            result = root.element;
            root = root.right;
        } else {
            BinaryTreeNode<T> parent = root;
            BinaryTreeNode<T> current = root.left;
            while (current.left != null) {
                parent = current;
                current = current.left;
            }
            result = current.element;
            parent.left = current.right;
        }

        return result;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        T result = null;

        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedBinarySearchTree");
        }

        if (root.right == null) {
            result = root.element;
            root = root.left;
        } else {
            BinaryTreeNode<T> parent = root;
            BinaryTreeNode<T> current = root.right;
            while (current.right != null) {
                parent = current;
                current = current.right;
            }
            result = current.element;
            parent.right = current.left;
        }

        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedBinarySearchTree");
        }

        BinaryTreeNode<T> current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.element;
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedBinarySearchTree");
        }

        BinaryTreeNode<T> current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.element;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findNode(targetElement, root);

        if (current == null)
            throw new ElementNotFoundException("LinkedBinarySearchTree");

        return current.getElement();
    }

    private BinaryTreeNode<T> findNode(T targetElement, BinaryTreeNode<T> next) {
        BinaryTreeNode<T> result;
        int compare;

        if (next == null) {
            result = null;
        } else {
            compare = targetElement.compareTo(next.getElement());
            if (compare == 0) {
                result = next;
            } else if (compare < 0) {
                result = findNode(targetElement, next.getLeft());
            } else {
                result = findNode(targetElement, next.getRight());
            }
        }

        return result;
    }
}
