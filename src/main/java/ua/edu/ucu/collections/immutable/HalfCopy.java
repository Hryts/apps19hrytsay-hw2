package ua.edu.ucu.collections.immutable;

public class HalfCopy {
    ImmutableLinkedList halfCopy;
    Node uncopied;

    public HalfCopy(ImmutableLinkedList halfCopy, Node copyIndex) {
        this.halfCopy = halfCopy;
        this.uncopied = copyIndex;
    }
}
