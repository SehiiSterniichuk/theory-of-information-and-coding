package lab2.controller;

import lab2.algorithm.Coder;
import lab2.algorithm.Decoder;
import lab2.model.Symbol;
import lab2.view.FileSize;
import lab2.view.Result;
import utils.FileConverter;
import utils.FileManager;

import java.io.File;
import java.util.List;
import java.util.OptionalDouble;

public class Program {

    private final String result;
    private static final String codedOutputFileName = "Compressed.txt";
    private static final String decodedOutputFileName = "Decompressed.txt";
    private static final String pathToFolder = "src/resources/";

    public Program(File inputFile) {
        String inputText = FileConverter.getFileAsString(inputFile);
        var coder = new Coder(inputText);
        String codedText = coder.getCodedText();
        FileManager fileManager = new FileManager();
        File compressedFile = new File(pathToFolder + codedOutputFileName);
        fileManager.byteWrite(compressedFile, stringToBytes(codedText));
        var decoder = new Decoder();
        String decodedText = decoder.decode(codedText, coder.getRoot());
        if (!inputText.equals(decodedText)) {
            throw new RuntimeException("Input and decoded text are not the same");
        }
        fileManager.createAndWrite(pathToFolder + decodedOutputFileName, decodedText);
        result = makeResult(inputFile, compressedFile, inputText.length(), codedText.length(), coder);
    }

    private String makeResult(File inputFile, File compressedFile, int lengthOfInput, int lengthOfCompressed,
                              Coder coder) {
        var list = coder.getAnalysisManager().getSymbols();
        return Result.of(inputFile.getName(),
                new FileSize(inputFile.length()), new FileSize(compressedFile.length()),
                lengthOfInput,
                coder.getAnalysisManager().averageEntropy,
                averageLengthOfCodeCombinations(list),
                coefficientOfCompression(inputFile, compressedFile));
    }

    private double coefficientOfCompression(File original, File compressed) {
        return coefficientOfCompression(original.length(), compressed.length());
    }

    private double coefficientOfCompression(double original, double compressed) {
        return original / compressed;
    }

    private double averageLengthOfCodeCombinations(List<Symbol> list) {
        OptionalDouble average = list.stream().mapToDouble((x) -> x.code().length()).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public String getResult() {
        return result;
    }


    private byte[] stringToBytes(String s) {
        if (s.length() % 8 != 0) {
            System.out.println("decode");
        }
        byte[] data = new byte[s.length() / 8 + s.length() % 8];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                data[i >> 3] |= 0x80 >> (i & 0x7);
            } else if (c != '0') {
                throw new IllegalArgumentException("Invalid char in binary string");
            }
        }
        return data;
    }
}
