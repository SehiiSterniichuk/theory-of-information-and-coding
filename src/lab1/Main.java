package lab1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    static private final DecimalFormat numView = new DecimalFormat("#.####");

    public static void printResult(StringAnalysisManager result, String message) {
        System.out.println(message);
        System.out.println("total number of symbols:\t" + result.lengthOfString);
        System.out.println("entropy:\t" + numView.format(result.getTotalEntropy()));
        System.out.println("average entropy per symbol :\t" + numView.format(result.averageEntropy));
        System.out.println("amount of information:\t" + numView.format(result.amountOfInformation));
        System.out.println("max entropy:\t" + numView.format(result.maxEntropy));
        System.out.println("absolute excess:\t" + numView.format(result.absoluteExcess));
        System.out.println("relative excess:\t" + numView.format(result.relativeExcess));
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("if you want to work with file enter 1, otherwise you will be work with string");
        System.out.print("Enter: ");
        String choice = sc.nextLine();
        String message, string;
        if (!choice.equals("1")) {
            System.out.println("Please, enter your string");
            System.out.print("Enter: ");
            string = sc.nextLine();
            message = "String: " + string;
        } else {
            System.out.println("Please, enter name of your file");
            System.out.print("Enter: ");
            String fileName = sc.nextLine();
            FileConverter converter = new FileConverter(fileName);
            string = converter.getFileAsString();
            message = "File: " + fileName;
        }
        printResult(new StringAnalysisManager(string), message);
    }
}
