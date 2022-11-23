package lab4.controller;

import lab3.algorithm.HuffmanCoder;
import lab4.view.ErrorInput;
import lab4.view.OptionSelectorLab4;
import utils.file.FileGetter;
import utils.file.FileManager;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

import static utils.file.FileList.CONSOLE_FILE_NAME;
import static utils.file.FileList.PATH_TO_RESULT;

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
                String hammingCodingAsString = program.getHammingCodingAsString();
                System.out.println("Hamming coding: " + hammingCodingAsString);
                ErrorInput errorInput = selector.selectWayOfErrorInput();
                switch (errorInput) {
                    case BY_INDEX -> {
                        var list = selector.inputList(selector.getNumber());
                        for(var i : list){
                            program.makeErrorInIndex(i);
                        }
                    }
                    case BY_NUMBER -> {
                        var number = selector.getNumber();
                        var rand = new Random();
                        for (int i = 0; i < number; i++) {
                            program.makeErrorInIndex(rand.ints(1, hammingCodingAsString.length()).findFirst().getAsInt());
                        }
                    }
                }
                var attempts = selector.inputNumberAttempts();
                var result = program.decode(attempts);
                System.out.println(result);
            }
            case LAB -> ranLab();
        }
    }

    private static void ranLab(){

    }

}
