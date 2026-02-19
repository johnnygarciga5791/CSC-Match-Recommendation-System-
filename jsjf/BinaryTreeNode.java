package jsjf;

/**
 * BinaryTreeNode implements a binary tree node with links. Adapted from code by
 * Lewis and Chase.
 */

public class BinaryTreeNode<T> {
    protected BinaryTreeNode<T> left, right;
    protected T element;

    public BinaryTreeNode(T obj) {
        element = obj;
        left = null;
        right = null;
    }

    public BinaryTreeNode(T obj, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right) {
        element = obj;

        if (left == null) {
            this.left = null;
        } else {
            this.left = left.getRootNode();
        }

        if (right == null) {
            this.right = null;
        } else {
            this.right = right.getRootNode();
        }
    }

    public int numChildren() {
        int children = 0;

        if (left != null) {
            children = 1 + left.numChildren();
        }

        if (right != null) {
            children = children + 1 + right.numChildren();
        }

        return children;
    }

    public int getHeight() {
        int height = -1;

        if (left != null) {
            height = Math.max(left.getHeight(), height);
        }

        if (right != null) {
            height = Math.max(right.getHeight(), height);
        }

        return height + 1;
    }

    public T getElement() {
        return element;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> node) {
        right = node;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> node) {
        left = node;
    }
}
