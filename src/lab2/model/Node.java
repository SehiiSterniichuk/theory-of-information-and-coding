package lab2.model;

public final class Node {
    private Node left;
    private Node right;
    private byte value;

    public Node(Node left, Node right, byte value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node() {
        this(null, null, (byte) 0);
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public byte getValue() {
        return value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setValue(byte value) {
        this.value = value;
    }
}
