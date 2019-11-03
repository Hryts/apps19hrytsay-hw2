package ua.edu.ucu.collections.immutable;

class Node {
    private Node next = null;
    private Object value;

    Node(Object value) {this.value = value;}

    Node() {}

    Node copy() {
        Node res = new Node(value);
        res.setNext(next);
        return res;
    }

    void setNext(Node next) {this.next = next;}

    void setValue(Object value) {this.value = value;}

    Node getNext() {return next;}

    Object getValue() {return value;}
}
