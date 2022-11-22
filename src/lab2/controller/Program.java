package lab2.controller;

import lab2.algorithm.Coder;
import lab2.algorithm.Decoder;
import lab2.model.Symbol;
import lab2.view.FileSize;
import lab2.view.Result;
import utils.AlgorithmCoder;
import utils.file.FileGetter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

public class Program {

    private final Result result;
    private final List<Symbol> symbols;
    private final byte[] codedText;

    public Program(File inputFile, AlgorithmCoder algorithmCoder) {
        var data = FileGetter.getFileAsByteArray(inputFile);
        Coder coder = new Coder(data, algorithmCoder);
        int length = data.length;
        codedText = coder.getCodedText();

        var decoder = new Decoder();
        String decodedText = decoder.decode(codedText, coder.getRoot());

        var resultSaver = new ResultSaver(inputFile, codedText, decodedText);
        result = makeResult(inputFile, resultSaver, length, coder, algorithmCoder.getName());
        this.symbols = coder.getAnalysisManager().getSymbols();
        symbols.sort(Comparator.comparingDouble(Symbol::probability).reversed());
    }


    public void printCodes() {
        for (var s : symbols) {
            System.out.printf("%c\t%f\t%s\n", (char) s.letter(), s.probability(), s.code());
        }
    }

    public String getCodedText() {
        return new String(codedText, StandardCharsets.UTF_8);
    }

    private Result makeResult(File inputFile, ResultSaver saver, int lengthOfInput,
                              Coder coder, String methodName) {
        return makeResult(inputFile, saver.compressedFile, saver.zipFile, lengthOfInput, coder, methodName);
    }

    private Result makeResult(File inputFile, File compressedFile, File zip, int lengthOfInput,
                              Coder coder, String methodName) {
        var list = coder.getAnalysisManager().getSymbols();
        double averageLengthOfCodeCombinations = averageLengthOfCodeCombinations(list);
        return new Result(inputFile.getName(),
                new FileSize(inputFile.length()),
                new FileSize(compressedFile.length()),
                new FileSize(zip.length()),
                lengthOfInput,
                coder.getAnalysisManager().getTotalEntropy(),
                coder.getAnalysisManager().maxEntropy,
                averageLengthOfCodeCombinations,
                coefficientOfCompression(coder, averageLengthOfCodeCombinations),
                saveSpaceRatio(inputFile, compressedFile),
                saveSpaceRatio(inputFile, zip),
                methodName);
    }

    private double saveSpaceRatio(File source, File archive) {
        return 1 - archive.length() / ((double) source.length());
    }

    private double coefficientOfCompression(Coder coder, double averageLengthOfCodeCombinations) {
        return coder.getAnalysisManager().maxEntropy / averageLengthOfCodeCombinations;
    }

    private double averageLengthOfCodeCombinations(List<Symbol> list) {
        return list.stream().mapToDouble((x) -> x.code().length() * x.probability()).sum();
    }

    public Result getResult() {
        return result;
    }

}
