package lab3.algorithm;

import lab2.model.Node;
import lab2.model.Symbol;
import lab3.model.HuffmanNode;
import utils.AlgorithmCoder;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class HuffmanCoder implements AlgorithmCoder {
    @Override
    public Node createCode(List<Symbol> symbols) {
        List<HuffmanNode> nodes = new ArrayList<>(symbols.size());
        Map<Byte, Symbol> symbolMap = new HashMap<>();
        for (var symbol : symbols) {
            nodes.add(new HuffmanNode(symbol.letter(), symbol.probability()));
            symbolMap.put(symbol.letter(), symbol);
        }
        var root = code(nodes);
        getCodeCombinations(root, symbolMap, new byte[]{});
        return root;
    }

    private static void getCodeCombinations(Node root, Map<Byte, Symbol> symbolMap, byte[] code) {
        if (!root.hasChildren()) {
            symbolMap.get(root.getValue()).add(new String(code, StandardCharsets.UTF_8));
            return;
        }
        var newCode = Arrays.copyOf(code, code.length + 1);
        newCode[code.length] = '0';
        getCodeCombinations(root.getLeft(), symbolMap, newCode);
        newCode[code.length] = '1';
        getCodeCombinations(root.getRight(), symbolMap, newCode);
    }

    private static HuffmanNode code(List<HuffmanNode> nodes) {
        while (nodes.size() > 1) {
            sort(nodes);
            var left = nodes.get(0);
            var right = nodes.get(1);
            var newNode = new HuffmanNode(left, right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(newNode);
        }
        return nodes.get(0);
    }

    private static void sort(List<HuffmanNode> nodes) {
        nodes.sort(Comparator.comparingDouble(HuffmanNode::getWeight));
    }

    @Override
    public String getName() {
        return "Huffman";
    }
}
