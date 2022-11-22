package lab2.algorithm;

import lab2.model.Node;

public class Decoder {

    public String decode(final byte[] array, final Node root) {
        if (array == null || root == null) {
            return "";
        }
        var builder = new StringBuilder();
        Node currentNode = root;
        for (var i : array) {
            if (i == '0') {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
            if(currentNode == null){throw new IllegalArgumentException("Bad tree of codes");}
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                char value = (char) currentNode.getValue();
                builder.append(value);
                currentNode = root;
            }
        }
        return builder.toString();
    }
}
