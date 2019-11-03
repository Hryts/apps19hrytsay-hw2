package ua.edu.ucu.collections.immutable;

class FullCopy {
    private ImmutableLinkedList fullCopy;
    private Node lastCopied;

    FullCopy(ImmutableLinkedList fullCopy, Node copied) {
        this.fullCopy = fullCopy;
        this.lastCopied = copied;
    }

    ImmutableLinkedList getFullCopy() {return fullCopy;}

    Node getLastCopied() {return lastCopied;}
}
