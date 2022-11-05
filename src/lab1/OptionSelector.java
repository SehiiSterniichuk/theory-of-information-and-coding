package lab1;

import utils.FileGetter;

import java.util.Scanner;

public class OptionSelector {

    private final String message, string;

    public OptionSelector() {
        Scanner sc = new Scanner(System.in);
        System.out.println("if you want to work with file enter 1, otherwise you will be work with string");
        System.out.print("Enter: ");
        String choice = sc.nextLine();

        if (!choice.equals("1")) {
            System.out.println("Please, enter your string");
            System.out.print("Enter: ");
            string = sc.nextLine();
            message = "String: " + string;
        } else {
            System.out.println("Please, enter name of your file");
            System.out.print("Enter: ");
            String fileName = sc.nextLine();
            FileGetter converter = new FileGetter(fileName);
            string = converter.getFileAsString();
            message = "File: " + fileName;
        }
    }

    public String getMessage() {
        return message;
    }

    public String getString() {
        return string;
    }

}
