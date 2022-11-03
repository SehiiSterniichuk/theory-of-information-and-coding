package lab2.controller;

import lab2.algorithm.Coder;
import lab2.view.OptionSelectorLab2;
import utils.FileConverter;
import utils.FileManager;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Lab #2 has started work");
        System.out.println("Shannon–Fano coding");
        var selector = new OptionSelectorLab2();
        File file = null;
        if (selector.isConsole()) {
            file = (new FileManager()).createAndWrite("Console.txt", selector.getMessage());
        } else {
            var converter = new FileConverter(selector.getMessage());
            file = converter.getFile();
        }
        var program = new Program(file);
        var result = program.getResult();
        System.out.println(result);
    }
}
