package ua.edu.ucu.collections.immutable;

class HalfCopy {
    private ImmutableLinkedList halfCopy;
    private Node lastCopied;
    private Node unCopied;


    HalfCopy(ImmutableLinkedList halfCopy, Node unCopied, Node lastCopied) {
        this.halfCopy = halfCopy;
        this.lastCopied = lastCopied;
        this.unCopied = unCopied;
    }

    ImmutableLinkedList getHalfCopy() {return halfCopy;}

    Node getLastCopied() {return lastCopied;}

    Node getUnCopied() {return unCopied;}
}
