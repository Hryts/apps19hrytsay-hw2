package ua.edu.ucu.collections.immutable;

import java.util.Iterator;

public class ImmutableLinkedList implements ImmutableList{
    private Node root = new Node();

    ImmutableLinkedList() {}

    private ImmutableLinkedList(Node root) {this.root = root;}

    private FullCopy copy() {
        Node currentCopy = root.copy();
        Node rootCopy = currentCopy;
        while (currentCopy.getNext() != null) {
            currentCopy.setNext(currentCopy.getNext().copy());
            currentCopy = currentCopy.getNext();
        }
        return new FullCopy(new ImmutableLinkedList(rootCopy), currentCopy);
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
        Node unCopied = currentCopy.getNext();
        currentCopy.setNext(null);
        return new HalfCopy(new ImmutableLinkedList(rootCopy), unCopied,
                currentCopy);
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        FullCopy fc = copy();
        ImmutableLinkedList res = fc.getFullCopy();
        Node last = fc.getLastCopied();
        last.setNext(new Node(e));
        return res;
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList res = copy().getFullCopy();
        Node newRoot = new Node(e);
        newRoot.setNext(res.getRoot());
        res.setRoot(newRoot);
        return res;
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        if (index >= size()) throw new IndexOutOfBoundsException();
        HalfCopy hf = halfCopy(index);
        ImmutableLinkedList head = hf.getHalfCopy();
        ImmutableLinkedList tail = new ImmutableLinkedList(
                hf.getUnCopied()).copy().getFullCopy();
        Node toAdd = new Node(e);
        toAdd.setNext(tail.getRoot());
        tail.setRoot(toAdd);
        hf.getLastCopied().setNext(tail.getRoot());
        return head;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        FullCopy fc = copy();
        ImmutableLinkedList res = fc.getFullCopy();
        Node last = fc.getLastCopied();
        for (Object o : c) {
            Node toAdd = new Node(o);
            last.setNext(toAdd);
            last = toAdd;
        }
        return res;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index >= size()) throw new IndexOutOfBoundsException();
        HalfCopy hf = halfCopy(index);
        ImmutableLinkedList head = hf.getHalfCopy();
        ImmutableLinkedList tail = new ImmutableLinkedList(
                hf.getUnCopied());
        head = (ImmutableLinkedList) head.addAll(c);
        Node crutch = head.getRoot();
        while (crutch.getNext() != null) {
            crutch = crutch.getNext();
        }
        crutch.setNext(tail.getRoot());
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
                hf.getUnCopied().getNext()).copy().getFullCopy();
        hf.getLastCopied().setNext(tail.getRoot());
        return head;
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList res = copy().getFullCopy();
        res.setRoot(res.getRoot().getNext());
        return res;
    }

    public ImmutableLinkedList removeLast() {
        FullCopy fc = copy();
        ImmutableLinkedList res = fc.getFullCopy();
        Node last = fc.getLastCopied();
        last.setValue(null);
        return res;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        HalfCopy hf = halfCopy(index);
        ImmutableLinkedList head = hf.getHalfCopy();
        Node firstUnCopied = hf.getUnCopied();
        firstUnCopied.setValue(e);
        ImmutableLinkedList tail = new ImmutableLinkedList(
                firstUnCopied).copy().getFullCopy();
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
        return counter - 1;
    }

    @Override
    public ImmutableList clear() {return new ImmutableLinkedList();}

    @Override
    public boolean isEmpty() {return size() == 0;}

    @Override
    public Object[] toArray() {
        int len = size();
        Object[] res = new Object [len];
        Node current = root;
        int counter = 0;
        for (int i = 0; i < len + 1; i++) {
            if (current.getValue() != null) {
                res[counter++] = current.getValue();
            }
            current = current.getNext();
        }
        return res;
    }

    private void setRoot(Node root) {
        this.root = root;
    }

    Node getRoot() {
        return root;
    }

    public Node getRootOut() {return root.copy();}
}
