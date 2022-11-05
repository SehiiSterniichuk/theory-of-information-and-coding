package lab2.controller;

import lab2.view.OptionSelectorLab2;
import lab2.view.Result;
import lab2.view.ResultTable;
import utils.FileGetter;
import utils.FileManager;

import java.io.File;
import java.util.*;

public class Runner {
    private static final String PATH_TO_FOLDER = "src/resources/";
    private static final String CONSOLE_FILE_NAME = "Console.txt";
    private static final String PATH_TO_TEXT_FOLDER = PATH_TO_FOLDER + "casual/eng/";
    private static final String PATH_TO_MEDIA_FOLDER = PATH_TO_FOLDER + "media/";


    public static void main(String[] args) {
        System.out.println("Lab #2 has started work");
        System.out.println("Shannon–Fano coding");
        var selector = new OptionSelectorLab2();
        File file;
        switch (selector.getOption()) {
            case FILE, CONSOLE -> {
                if (!selector.isConsole()) {
                    var converter = new FileGetter(selector.getMessage());
                    file = converter.getFile();
                } else {
                    file = (new FileManager()).
                            createAndWrite(PATH_TO_FOLDER + CONSOLE_FILE_NAME, selector.getMessage());
                }
                var program = new Program(file);
                if(selector.isConsole()) program.printCodes();
                var result = program.getResult();
                System.out.println(result);
            }
            case LAB -> ranLab();
        }
    }

    static void ranLab() {
        final var listOfTextFiles = FileGetter.getFiles(PATH_TO_TEXT_FOLDER);
        final var listOfMediaFiles = FileGetter.getFiles(PATH_TO_MEDIA_FOLDER);
        analizListOfFiles(listOfTextFiles);
        System.out.println("\nMedia...");
        analizListOfFiles(listOfMediaFiles);
    }

    static void analizListOfFiles(final File[] arrayOfFiles) {
        final List<File> listOfFiles = new ArrayList<>(arrayOfFiles.length);
        Collections.addAll(listOfFiles, arrayOfFiles);
        analizListOfFiles(listOfFiles);
    }

    static void analizListOfFiles(final List<File> listOfFiles) {
        listOfFiles.sort(Comparator.comparingDouble(File::length));
        final var results = makeListOfResults(listOfFiles);
        final var table = new ResultTable(results);
        table.print();
    }

    static List<Result> makeListOfResults(List<File> fileNames) {
        List<Result> list = new LinkedList<>();
        for (var i : fileNames) {
            var program = new Program(i);
            list.add(program.getResult());
        }
        return list;
    }
}
