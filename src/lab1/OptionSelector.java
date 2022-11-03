package lab1;

import utils.FileConverter;

import java.util.Scanner;

public class OptionSelector {

    public enum Option{
        FILE, CONSOLE
    }

    private Option option;

    private String message, string;

    public OptionSelector() {
        Scanner sc = new Scanner(System.in);
        System.out.println("if you want to work with file enter 1, otherwise you will be work with string");
        System.out.print("Enter: ");
        String choice = sc.nextLine();

        if (!choice.equals("1")) {
            option = Option.FILE;
            System.out.println("Please, enter your string");
            System.out.print("Enter: ");
            string = sc.nextLine();
            message = "String: " + string;
        } else {
            option = Option.CONSOLE;
            System.out.println("Please, enter name of your file");
            System.out.print("Enter: ");
            String fileName = sc.nextLine();
            FileConverter converter = new FileConverter(fileName);
            string = converter.getFileAsString();
            message = "File: " + fileName;
        }
    }

    public String getMessage() {
        return message;
    }

    public Option getOption() {
        return option;
    }

    public String getString() {
        return string;
    }

    public boolean isConsole(){
        return getOption() == OptionSelector.Option.CONSOLE;
    }
}
