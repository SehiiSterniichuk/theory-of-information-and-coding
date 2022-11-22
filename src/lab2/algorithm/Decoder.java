package lab2.algorithm;

import lab2.model.Node;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Decoder {

    public byte[] decode(final byte[] array, final Node root) {
        if (array == null || root == null) {
            return new byte[]{};
        }
        Node currentNode = root;
        try(var builder = new ByteArrayOutputStream()){
            for (var i : array) {
                if (i == '0') {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode = currentNode.getRight();
                }
                if(currentNode == null){throw new IllegalArgumentException("Bad tree of codes");}
                if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                    builder.write(currentNode.getValue());
                    currentNode = root;
                }
            }
            return builder.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
