package ua.edu.ucu.collections.immutable;

public class HalfCopy {
    private ImmutableLinkedList halfCopy;
    private Node uncopied;
    private Node copied;

    public void setCopied(Node copied) {
        this.copied = copied;
    }

    public Node getUncopied() {
        return uncopied;
    }

    public Node getCopied() {
        return copied;
    }

    public void setLastCopied(Node lastCopied) {
        this.lastCopied = lastCopied;
    }

    Node getLastCopied() {
        return lastCopied;
    }

    private Node lastCopied;

    HalfCopy(ImmutableLinkedList halfCopy, Node uncopied, Node copied) {
        this.halfCopy = halfCopy;
        this.uncopied = uncopied;
        this.copied = copied;
    }

    public void setHalfCopy(ImmutableLinkedList halfCopy) {
        this.halfCopy = halfCopy;
    }

    public void setUnCopied(Node uncopied) {
        this.uncopied = uncopied;
    }

    ImmutableLinkedList getHalfCopy() {
        return halfCopy;
    }

    Node getUnCopied() {
        return uncopied;
    }
}
