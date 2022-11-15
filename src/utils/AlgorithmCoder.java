package utils;

import lab2.model.Node;
import lab2.model.Symbol;

import java.util.List;

public interface AlgorithmCoder {
    Node createCode(List<Symbol> symbols);

    String getName();
}
