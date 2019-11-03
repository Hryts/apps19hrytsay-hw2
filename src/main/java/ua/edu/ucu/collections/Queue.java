package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList queue;

    public Queue(ImmutableLinkedList queue) {
        this.queue = queue;
    }

    public Object peek() {
        return queue.get(0);
    }

    public Object dequeue() {
        return queue.remove(0);
    }

    public void enqueue (Object e) {
        queue.add(queue.size()-1, e);
    }
}
