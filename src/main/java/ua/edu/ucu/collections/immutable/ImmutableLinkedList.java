package ua.edu.ucu.collections.immutable;

import java.util.Iterator;

public class ImmutableLinkedList implements ImmutableList{
    private Node root = new Node();

    public ImmutableLinkedList() {}

    public ImmutableLinkedList(Node root) {this.root = root;}

    private ImmutableLinkedList copy() {
        Node current = root;
        Node currentCopy = root.copy();
        Node rootCopy = currentCopy;
        while (current != null) {
            currentCopy.setNext(current.getNext().copy());
            currentCopy = current.getNext();
            current = current.getNext();
        }
        return new ImmutableLinkedList(rootCopy);
    }

    private HalfCopy halfCopy(int index) {
        Node current = root;
        Node currentCopy = root.copy();
        for (int i = index; i > 0; i--) {
            currentCopy.setNext(current.getNext().copy());
            currentCopy = currentCopy.getNext();
            if (currentCopy == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return new HalfCopy(new ImmutableLinkedList(currentCopy), currentCopy.getNext());
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        ImmutableLinkedList res = this.copy();
        Node newRoot = new Node(e);
        newRoot.setNext(res.getRoot());
        res.setRoot(newRoot);
        return res;
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        Node current = root;
        Node currentCopy = root.copy();
        Node rootCopy = currentCopy;
        for (int i = index - 1; i > 0; i++) {
            currentCopy.setNext(current.getNext().copy());
            currentCopy = currentCopy.getNext();
            if (currentCopy == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        ImmutableLinkedList tail = new ImmutableLinkedList(
                currentCopy.getNext()).copy();
        Node toAdd = new Node(e);
        toAdd.setNext(tail.getRoot());
        currentCopy.setNext(toAdd);
        return new ImmutableLinkedList(rootCopy);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableLinkedList res = this.copy();
        for (int i = 0; i < c.length; i++) {
            res.add(c[i]);
        }
        return res;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        return null;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public ImmutableList remove(int index) {
        return null;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        return null;
    }

    @Override
    public int indexOf(Object e) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ImmutableList clear() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }
}
