package lab2.algorithm;

import lab1.StringAnalysisManager;
import lab2.model.Node;
import lab2.model.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coder {

    private final Map<Byte, String> letterToCode = new HashMap<>();
    private final String codedText;
    private final Node root;
    private final StringAnalysisManager analysisManager;

    public Coder(String text) {
       this(text.getBytes());
    }

    public Coder(byte[] text) {
        analysisManager = new StringAnalysisManager(text);
        List<Symbol> symbols = analysisManager.getSymbols();
        var shannonFano = new ShannonFanoCoder();
        root = shannonFano.createCode(symbols);
        symbols.forEach((x) -> letterToCode.put(x.letter(), x.code()));
        var builder = new StringBuilder();
        for(var i : text){
            builder.append(letterToCode.get(i));
        }
        codedText = builder.toString();
    }

    public Node getRoot() {
        return root;
    }

    public String getCodedText() {
        return codedText;
    }

    public StringAnalysisManager getAnalysisManager() {
        return analysisManager;
    }
}
