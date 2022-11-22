package lab4.controller;

import lab2.algorithm.Coder;
import utils.AlgorithmCoder;
import utils.file.FileGetter;

import java.io.File;

public class Program {

    private final String codedText;

    public Program(final File inputFile, final AlgorithmCoder algorithmCoder, final int insertedErrors) {
        var data = FileGetter.getFileAsByteArray(inputFile);
        Coder coder = new Coder(data, algorithmCoder);
        codedText = coder.getCodedTextAsString();
    }
}
