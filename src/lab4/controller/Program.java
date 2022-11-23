package lab4.controller;

import lab2.algorithm.Coder;
import lab2.algorithm.Decoder;
import lab2.model.Node;
import lab2.view.FileSize;
import lab4.controller.algorithm.HammingCodeFixer;
import lab4.controller.algorithm.HammingCoder;
import lab4.controller.algorithm.HammingDecoder;
import lab4.view.ResultLab4;
import utils.AlgorithmCoder;
import utils.ByteUtil;
import utils.file.FileGetter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Program {

    private final Node root;
    private final File inputFile;
    private final List<Integer> insertedErrors = new LinkedList<>();
    private final byte[] inputData;
    private final byte[] algorithmCoding;
    private final byte[] hammingCoding;

    public Program(final File inputFile, final AlgorithmCoder algorithmCoder) {
        this.inputFile = inputFile;
        this.inputData = FileGetter.getFileAsByteArray(inputFile);
        var coder = new Coder(inputData, algorithmCoder);
        this.root = coder.getRoot();
        var hummingCoder = new HammingCoder();
        this.algorithmCoding = coder.getCodedText();
        this.hammingCoding = hummingCoder.createCode(algorithmCoding);
    }

    public void makeErrorInIndex(int i) {
        if (i >= hammingCoding.length || i <= 0) {
            final int old = i;
            i = 1;
            System.out.println("You can't modify index " + old + ". Instead index will be equal: " + i);
        }
        insertedErrors.add(i);
        ByteUtil.changeSign(hammingCoding, i);
    }

    private static int random(int max) {
        int min = 1;
        return (int) ((Math.random() * (max - min)) + min);
    }


    public void makeRandomError() {
        makeErrorInIndex(random(hammingCoding.length));
    }

    public void makeRandomErrors(int number) {
        for (int i = 0; i < number; i++) {
            makeRandomError();
        }
    }


    public ResultLab4 decode(int attemptsToFix) {
        var hammingDecoder = new HammingDecoder(new HammingCodeFixer(attemptsToFix));
        byte[] hammingDecoding = hammingDecoder.decoding(hammingCoding);
        byte[] decoded = (new Decoder()).decode(hammingDecoding, root);
        return makeResult(hammingDecoder, hammingDecoding, Arrays.equals(inputData, decoded));
    }

    private ResultLab4 makeResult(HammingDecoder decoder,
                                  byte[] hammingDecoding,
                                  boolean isValid) {
        return new ResultLab4(inputFile.getName(),
                new FileSize(inputFile.length()),
                algorithmCoding, hammingCoding, hammingDecoding,
                decoder.fixer().isValid(),
                isValid,
                decoder.fixer().getErrors(),
                getInsertedErrors());
    }

    public List<Integer> getInsertedErrors() {
        return new LinkedList<>(insertedErrors);
    }

    public String getHammingCodingAsString() {
        return new String(hammingCoding, StandardCharsets.UTF_8);
    }
}
