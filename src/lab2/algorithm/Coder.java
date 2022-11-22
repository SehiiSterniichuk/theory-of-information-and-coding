package lab2.algorithm;

import lab1.StringAnalysisManager;
import lab2.model.Node;
import lab2.model.Symbol;
import utils.AlgorithmCoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coder {

    private final Map<Byte, byte[]> letterToCode = new HashMap<>();
    private final byte[] codedText;
    private final Node root;
    private final StringAnalysisManager analysisManager;

    public Coder(byte[] text, AlgorithmCoder algorithmCoder) {
        analysisManager = new StringAnalysisManager(text);
        List<Symbol> symbols = analysisManager.getSymbols();
        root = algorithmCoder.createCode(symbols);
        symbols.forEach((x) -> letterToCode.put(x.letter(), x.code().getBytes()));
        try(var builder = new ByteArrayOutputStream()){
            for (var i : text) {
                builder.write(letterToCode.get(i));
            }
            codedText = builder.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Node getRoot() {
        return root;
    }

    public String getCodedTextAsString() {
        return new String(codedText, StandardCharsets.UTF_8);
    }

    public byte[] getCodedText(){
        return Arrays.copyOf(codedText, codedText.length);
    }

    public StringAnalysisManager getAnalysisManager() {
        return analysisManager;
    }
}
