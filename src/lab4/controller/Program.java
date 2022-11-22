package lab4.controller;

import lab2.algorithm.Coder;
import lab2.algorithm.Decoder;
import lab2.controller.ResultSaver;
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
import java.util.LinkedList;
import java.util.List;

public class Program {

    private final byte[] codedText;
    private final Node root;
    private final File inputFile;
    final private List<Integer> insertedErrors = new LinkedList<>();

    public Program(final File inputFile, final AlgorithmCoder algorithmCoder) {
        this.inputFile = inputFile;
        var data = FileGetter.getFileAsByteArray(inputFile);
        var coder = new Coder(data, algorithmCoder);
        this.root = coder.getRoot();
        var hummingCoder = new HammingCoder();
        codedText = hummingCoder.createCode(coder.getCodedText());
    }

    public void makeErrorInIndex(int i){
        if(i >= codedText.length || i <= 0){
            final int old = i;
            i = 1;
            System.out.println("You can't modify index " + old + ". Instead index will be equal: " + i);
        }
        insertedErrors.add(i);
        ByteUtil.changeSign(codedText, i);
    }

    public ResultLab4 decode(int attemptsToFix){
        var hammingDecoder = new HammingDecoder(new HammingCodeFixer(attemptsToFix));
        byte[] hammingDecoding = hammingDecoder.decoding(codedText);
        String decoded = (new Decoder()).decode(hammingDecoding, root);
        new ResultSaver(inputFile, codedText, decoded);
        return makeResult(hammingDecoder);
    }

    private ResultLab4 makeResult(HammingDecoder decoder){
        return new ResultLab4(inputFile.getName(),
                new FileSize(inputFile.length()),
                decoder.fixer().isValid(),
                decoder.fixer().getErrors(),
                getInsertedErrors());
    }

    public List<Integer> getInsertedErrors() {
        return new LinkedList<>(insertedErrors);
    }
}
