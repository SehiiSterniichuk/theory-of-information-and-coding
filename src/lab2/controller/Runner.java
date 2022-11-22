package lab2.controller;

import lab2.algorithm.ShannonFanoCoder;
import lab2.view.OptionSelectorLab2;
import lab2.view.Result;
import lab2.view.ResultTable;
import utils.AlgorithmCoder;
import utils.file.FileGetter;
import utils.file.FileManager;

import java.io.File;
import java.util.*;

import static utils.file.Files.*;

public class Runner {

    public static void main(String[] args) {
        String helloMessage = "Lab #2 has started work\nShannon–Fano coding";
        var runner = new Runner();
        runner.work(helloMessage, new ShannonFanoCoder());
    }

    public void work(String helloMessage, AlgorithmCoder coder) {
        System.out.println(helloMessage);
        var selector = new OptionSelectorLab2();
        File file;
        switch (selector.getOption()) {
            case FILE, CONSOLE -> {
                if (!selector.isConsole()) {
                    var converter = new FileGetter(selector.getMessage());
                    file = converter.getFile();
                } else {
                    file = (new FileManager()).
                            write(PATH_TO_FOLDER.add(CONSOLE_FILE_NAME), selector.getMessage());
                }
                var program = new Program(file, coder);
                if (selector.isConsole()) System.out.println("Coded string:\t" + program.getCodedText());
                var result = program.getResult();
                System.out.println(result);
                if (selector.isConsole()) program.printCodes();
            }
            case LAB -> ranLab(coder);
        }
    }

    static void ranLab(AlgorithmCoder coder) {
        final var listOfTextFiles = FileGetter.getFiles(PATH_TO_TEXT_FOLDER.getPath());
        final var listOfMediaFiles = FileGetter.getFiles(PATH_TO_MEDIA_FOLDER.getPath());
        System.out.println("\nText length analiz:");
        analizListOfFiles(listOfTextFiles, coder);
        System.out.println("\nDifferent types...");
        analizListOfFiles(listOfMediaFiles, coder);
    }

    static void analizListOfFiles(final File[] arrayOfFiles, AlgorithmCoder coder) {
        final List<File> listOfFiles = new ArrayList<>(arrayOfFiles.length);
        Collections.addAll(listOfFiles, arrayOfFiles);
        analizListOfFiles(listOfFiles, coder);
    }

    static void analizListOfFiles(final List<File> listOfFiles, AlgorithmCoder coder) {
        listOfFiles.sort(Comparator.comparingDouble(File::length));
        final var results = makeListOfResults(listOfFiles, coder);
        final var table = new ResultTable(results);
        table.print();
    }

    static List<Result> makeListOfResults(List<File> fileNames, AlgorithmCoder coder) {
        List<Result> list = new LinkedList<>();
        for (var i : fileNames) {
            var program = new Program(i, coder);
            list.add(program.getResult());
        }
        return list;
    }
}
