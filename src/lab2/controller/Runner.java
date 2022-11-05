package lab2.controller;

import lab2.view.OptionSelectorLab2;
import utils.FileGetter;
import utils.FileManager;

import java.io.File;

public class Runner {
    private static final String PATH_TO_FOLDER = "src/resources/";
    private static final String CONSOLE_FILE_NAME = "Console.txt";

    public static void main(String[] args) {
        System.out.println("Lab #2 has started work");
        System.out.println("Shannon–Fano coding");
        var selector = new OptionSelectorLab2();
        File file;
        if (selector.isConsole()) {
            file = (new FileManager()).createAndWrite(PATH_TO_FOLDER + CONSOLE_FILE_NAME, selector.getMessage());
        } else {
            var converter = new FileGetter(selector.getMessage());
            file = converter.getFile();
        }
        var program = new Program(file);
        var result = program.getResult();
        System.out.println(result);
    }
}
