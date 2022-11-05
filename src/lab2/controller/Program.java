package lab2.controller;

import lab2.algorithm.Coder;
import lab2.algorithm.Decoder;
import lab2.model.Symbol;
import lab2.view.FileSize;
import lab2.view.Result;
import utils.FileGetter;
import utils.FileManager;

import java.io.File;
import java.util.List;

public class Program {

    private final String result;
    private static final String CODED_OUTPUT_FILE_NAME = "Compressed";
    private static final String CODED_OUTPUT_FILE_AS_TEXT_NAME = "CompressedAsText.txt";
    private static final String DECODED_OUTPUT_FILE_NAME = "Decompressed.txt";
    private static final String ZIP_FILE_NAME = "Zip.zip";
    private static final String PATH_TO_FOLDER = "src/resources/";
    private static final String TEXT_FORMAT = ".txt";

    public Program(File inputFile) {
        int length;
        Coder coder;
        if(TEXT_FORMAT.equals(inputFile.getName())){
            String inputText = FileGetter.getFileAsString(inputFile);
            coder = new Coder(inputText);
            length = inputText.length();
        }else {
            var bytes = FileGetter.getFileAsByteArray(inputFile);
            coder = new Coder(bytes);
            length = bytes.length;
        }
        String codedText = coder.getCodedText();
        FileManager fileManager = new FileManager();
        File compressedFile = new File(PATH_TO_FOLDER + CODED_OUTPUT_FILE_NAME);
        File zipFile = fileManager.archiveFileToZip(inputFile, PATH_TO_FOLDER + ZIP_FILE_NAME);
        fileManager.byteWrite(compressedFile, stringToBytes(codedText));
        fileManager.createAndWrite(PATH_TO_FOLDER + CODED_OUTPUT_FILE_AS_TEXT_NAME, codedText);
        var decoder = new Decoder();
        String decodedText = decoder.decode(codedText, coder.getRoot());
        fileManager.createAndWrite(PATH_TO_FOLDER + DECODED_OUTPUT_FILE_NAME, decodedText);
        result = makeResult(inputFile, compressedFile, zipFile, length, coder);
    }

    private String makeResult(File inputFile, File compressedFile, File zip, int lengthOfInput,
                              Coder coder) {
        var list = coder.getAnalysisManager().getSymbols();
        double averageLengthOfCodeCombinations = averageLengthOfCodeCombinations(list);
        return Result.of(inputFile.getName(),
                new FileSize(inputFile.length()),
                new FileSize(compressedFile.length()),
                new FileSize(zip.length()),
                lengthOfInput,
                coder.getAnalysisManager().getTotalEntropy(),
                coder.getAnalysisManager().maxEntropy,
                averageLengthOfCodeCombinations,
                coefficientOfCompression(coder, averageLengthOfCodeCombinations));
    }

    private double coefficientOfCompression(Coder coder, double averageLengthOfCodeCombinations) {
        return coder.getAnalysisManager().maxEntropy / averageLengthOfCodeCombinations;
    }

    private double averageLengthOfCodeCombinations(List<Symbol> list) {
        return list.stream().mapToDouble((x) -> x.code().length() * x.probability()).sum();
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
