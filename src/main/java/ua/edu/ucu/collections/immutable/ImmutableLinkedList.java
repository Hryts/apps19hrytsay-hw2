package ua.edu.ucu.collections.immutable;

import java.util.Iterator;

public class ImmutableLinkedList implements ImmutableList{
    private Node root = new Node();
    private int size;

    public ImmutableLinkedList() {size = 0;}

    public ImmutableLinkedList(Node root) {this.root = root;}

    private ImmutableLinkedList copy() {
        Node currentCopy = root.copy();
        Node rootCopy = currentCopy;
        while (currentCopy != null) {
            currentCopy.setNext(currentCopy.getNext().copy());
            currentCopy = currentCopy.getNext();
        }
        return new ImmutableLinkedList(rootCopy);
    }

    private HalfCopy halfCopy(int index) {
        Node currentCopy = root.copy();
        Node rootCopy = currentCopy;
        for (int i = index; i > 0; i--) {
            currentCopy.setNext(currentCopy.getNext().copy());
            currentCopy = currentCopy.getNext();
            if (currentCopy == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        Node uncopied = currentCopy.getNext();
        currentCopy.setNext(null);
        return new HalfCopy(new ImmutableLinkedList(rootCopy), uncopied,
                currentCopy);
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
        HalfCopy hf = halfCopy(index);
        ImmutableLinkedList head = hf.getHalfCopy();
        ImmutableLinkedList tail = new ImmutableLinkedList(
                hf.getUnCopied()).copy();
        hf.getLastCopied().setNext(tail.getRoot());
        return head;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableLinkedList res = this.copy();
        for (int i = 0; i < c.length; i++) {
            Node newRoot = new Node(c[i]);
            newRoot.setNext(res.getRoot());
            res.setRoot(newRoot);
        }
        return res;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        HalfCopy hf = halfCopy(index);
        ImmutableLinkedList head = hf.getHalfCopy();
        ImmutableLinkedList tail = new ImmutableLinkedList(
                hf.getUnCopied()).copy();
        tail.addAll(c);
        hf.getLastCopied().setNext(tail.getRoot());
        return head;
    }

    @Override
    public Object get(int index) {
        Node current = root;
        for (int i = index; i > 0; i--) {
            current = current.getNext();
            if (current == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return current.getValue();
    }

    @Override
    public ImmutableList remove(int index) {
        HalfCopy hf = halfCopy(index);
        ImmutableLinkedList head = hf.getHalfCopy();
        ImmutableLinkedList tail = new ImmutableLinkedList(
                hf.getUnCopied().getNext()).copy();
        hf.getLastCopied().setNext(tail.getRoot());
        return head;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        HalfCopy hf = halfCopy(index);
        ImmutableLinkedList head = hf.getHalfCopy();
        Node firstUncopied = hf.getUnCopied();
        firstUncopied.setValue(e);
        ImmutableLinkedList tail = new ImmutableLinkedList(
                firstUncopied).copy();
        hf.getLastCopied().setNext(tail.getRoot());
        return head;
    }

    @Override
    public int indexOf(Object e) {
        Node current = root;
        int counter = 0;
        while (current != null) {
            if (current.getValue() == e) {
                return counter;
            }
            current = current.getNext();
            counter++;
        }
        return counter;
    }

    @Override
    public int size() {
        int counter = 0;
        Node current = root;
        while (current != null) {
            current = current.getNext();
            counter++;
        }
        return counter;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        int len = size();
        Object[] res = new Object [len];
        Node current = root;
        for (int i = 0; i < len; i++) {
            res[i] = current;
            current = current.getNext();
        }
        return res;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }
}
