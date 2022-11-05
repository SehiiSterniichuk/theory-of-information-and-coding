package lab2.algorithm;

import lab2.model.Node;
import lab2.model.Symbol;

import java.util.*;

public class ShannonFanoCoder {

    public Node createCode(List<Symbol> symbols) {
        final Node root =  new Node();

        //noinspection ResultOfMethodCallIgnored
        symbols.stream().sorted(Comparator.comparingDouble(Symbol::probability));
        recursiveCoding(symbols, 0, symbols.size(), root);
        return root;
    }

    private void recursiveCoding(List<Symbol> symbols, int start, int end, Node node){
        if(end - start <= 1){
            node.setValue(symbols.get(start).letter());
            return;
        }
        var dividerIndex = Divider.getBestDiff(symbols, start, end);
        for (int i = start; i < dividerIndex; i++) {
            symbols.get(i).add("0");
        }
        for (int i = dividerIndex; i < end; i++) {
            symbols.get(i).add("1");
        }
        node.setLeft(new Node());
        node.setRight(new Node());
        recursiveCoding(symbols, start, dividerIndex, node.getLeft());
        recursiveCoding(symbols, dividerIndex, end, node.getRight());
    }
}
