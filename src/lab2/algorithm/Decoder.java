package lab2.algorithm;

import lab2.model.Node;

public class Decoder {
    @SuppressWarnings("ConstantConditions")
    public String decode(String codedStr, Node root) {
        var array = codedStr.getBytes();
        var builder = new StringBuilder();
        Node currentNode = root;
        for (var i : array) {
            if (i == '0') {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
            if (currentNode != null && currentNode.getLeft() == null && currentNode.getRight() == null) {
                char value = (char) currentNode.getValue();
                builder.append(value);
                currentNode = root;
            }
        }
        return builder.toString();
    }
}
