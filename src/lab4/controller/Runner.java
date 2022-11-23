package lab4.controller;

import lab3.algorithm.HuffmanCoder;
import lab4.view.ErrorInput;
import lab4.view.OptionSelectorLab4;
import lab4.view.ResultLab4;
import lab4.view.ResultTableLab4;
import utils.file.FileGetter;
import utils.file.FileManager;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static utils.file.FileList.*;

public class Runner {
    private static final String HELLO_MESSAGE_LAB_4 = "Lab #4 has started to work";

    public static void main(String[] args) {
        System.out.println(HELLO_MESSAGE_LAB_4);
        var selector = new OptionSelectorLab4();
        var mode = selector.selectMode();
        File file;
        switch (mode) {
            case FILE, CONSOLE -> {
                if (!mode.isConsole()) {
                    var converter = new FileGetter(selector.getMessage());
                    file = converter.getFile();
                } else {
                    file = (new FileManager()).
                            write(PATH_TO_RESULT.add(CONSOLE_FILE_NAME), selector.getMessage());
                }
                var program = new Program(file, new HuffmanCoder());
                System.out.println("Hamming coding: " + program.getHammingCodingAsString());
                ErrorInput errorInput = selector.selectWayOfErrorInput();
                switch (errorInput) {
                    case BY_INDEX -> {
                        var list = selector.inputList(selector.getNumber());
                        for (var i : list) {
                            program.makeErrorInIndex(i);
                        }
                        System.out.println("Edited coding: " + program.getHammingCodingAsString());
                    }
                    case BY_NUMBER -> {
                        var number = selector.getNumber();
                        program.makeRandomErrors(number);
                    }
                }
                var result = program.decode(2);
                System.out.println(result);
            }
            case LAB -> ranLab();
        }
    }

    private static void ranLab() {
        List<ResultLab4> results = new LinkedList<>();
        var files = Arrays.stream(FileGetter.getFiles(PATH_TO_TEXT_FOLDER.getPath()))
                        .sorted(Comparator.comparingLong(File::length)).toList();
        final int attempts = 2;
        final int maxNumberOfErrors = 3;
        var coder = new HuffmanCoder();
        for (var file : files) {
            for (int i = 1; i <= maxNumberOfErrors; i++) {
                var program = new Program(file, coder);
                program.makeRandomErrors(i);
                ResultLab4 result = program.decode(attempts);
                results.add(result);
            }
        }
        var table = new ResultTableLab4(results);
        table.print();
    }
}
