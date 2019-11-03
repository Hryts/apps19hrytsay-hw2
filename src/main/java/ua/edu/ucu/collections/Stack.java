package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList stack;

    public Stack(ImmutableLinkedList stack) {
        this.stack = stack;
    }

    public Object peek() {
        return stack.get(0);
    }

    public Object dequeue() {
        return stack.remove(0);
    }

    public void enqueue(Object e) {
        stack.add(0, e);
    }
}
