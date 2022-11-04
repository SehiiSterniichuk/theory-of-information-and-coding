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
    private static final String CODED_OUTPUT_FILE_NAME = "Compressed";
    private static final String CODED_OUTPUT_FILE_AS_TEXT_NAME = "CompressedAsText.txt";
    private static final String DECODED_OUTPUT_FILE_NAME = "Decompressed.txt";
    private static final String PATH_TO_FOLDER = "src/resources/";
    private static final String TEXT_FORMAT = ".txt";

    public Program(File inputFile) {
        int length;
        Coder coder;
        if(TEXT_FORMAT.equals(inputFile.getName())){
            String inputText = FileConverter.getFileAsString(inputFile);
            coder = new Coder(inputText);
            length = inputText.length();
        }else {
            var bytes = FileConverter.getFileAsByteArray(inputFile);
            coder = new Coder(bytes);
            length = bytes.length;
        }
        String codedText = coder.getCodedText();
        FileManager fileManager = new FileManager();
        File compressedFile = new File(PATH_TO_FOLDER + CODED_OUTPUT_FILE_NAME);
        fileManager.byteWrite(compressedFile, stringToBytes(codedText));
        fileManager.createAndWrite(PATH_TO_FOLDER + CODED_OUTPUT_FILE_AS_TEXT_NAME, codedText);
        var decoder = new Decoder();
        String decodedText = decoder.decode(codedText, coder.getRoot());
        fileManager.createAndWrite(PATH_TO_FOLDER + DECODED_OUTPUT_FILE_NAME, decodedText);
        result = makeResult(inputFile, compressedFile, length, coder);

        for (var symbol: coder.getAnalysisManager().getSymbols()) {
            System.out.printf("%c\t%s\t%f\t\n", (char)symbol.letter(), symbol.code(), symbol.probability());
//            System.out.printf("%c\t%f\n", (char)symbol.letter(), symbol.probability());
        }
    }

    private String makeResult(File inputFile, File compressedFile, int lengthOfInput,
                              Coder coder) {
        var list = coder.getAnalysisManager().getSymbols();
        double averageLengthOfCodeCombinations = averageLengthOfCodeCombinations(list);
        return Result.of(inputFile.getName(),
                new FileSize(inputFile.length()), new FileSize(compressedFile.length()),
                lengthOfInput,
                coder.getAnalysisManager().getTotalEntropy(),
                coder.getAnalysisManager().maxEntropy,
                averageLengthOfCodeCombinations,
                coefficientOfCompression(coder, averageLengthOfCodeCombinations));
    }

    private double coefficientOfCompression(File original, File compressed) {
        return coefficientOfCompression(original.length(), compressed.length());
    }

    private double coefficientOfCompression(double original, double compressed) {
        return original / compressed;
    }

    private double coefficientOfCompression(Coder coder, double averageLengthOfCodeCombinations) {
        return coder.getAnalysisManager().maxEntropy / averageLengthOfCodeCombinations;
    }

    private double averageLengthOfCodeCombinations(List<Symbol> list) {
        var sum = list.stream().mapToDouble((x) -> x.code().length() * x.probability()).sum();
        return sum;
    }

    public String getResult() {
        return result;
    }

    private byte[] stringToBytes(String s) {
        int sizeOfArray = s.length() / 8;
        if (s.length() % 8 != 0) {
            sizeOfArray++;
        }
        byte[] data = new byte[sizeOfArray];
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
