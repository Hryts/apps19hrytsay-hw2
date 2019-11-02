package ua.edu.ucu.collections.immutable;

public class Node {
    private Node next = null;
    private Object value = new Object();

    public Node(Object value) {this.value = value;}

    public Node() {}

    public Node copy() {
        Node res = new Node(value);
        res.setNext(next);
        return res;
    }

    public void setNext(Node next) {this.next = next;}

    public void setValue(Object value) {this.value = value;}

    public Node getNext() {return next;}

    public Object getValue() {return value;}
}
