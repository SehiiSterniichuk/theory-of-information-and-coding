package lab3.model;

import lab2.model.Node;

public class HuffmanNode extends Node {
    private final double weight;

    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        super(left, right, (byte)0);
        this.weight = left.getWeight() + right.getWeight();
    }

    public HuffmanNode(byte value, double weight) {
        super(null, null, value);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
