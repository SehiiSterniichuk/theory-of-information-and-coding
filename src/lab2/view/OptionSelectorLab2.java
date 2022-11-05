package lab2.view;

import java.util.Scanner;

public class OptionSelectorLab2 {

    public enum Option {
        FILE, CONSOLE, LAB
    }

    private final Option option;

    private String message;

    public OptionSelectorLab2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                If you want to work with file enter 1
                if you want to work with string tap 2
                otherwise lab tasks will be executed""");
        System.out.print("Enter: ");
        String choice = sc.nextLine();
        if (!choice.equals("1") && !choice.equals("2")) {
            option = Option.LAB;
        } else {
            if (choice.equals("2")) {
                option = Option.CONSOLE;
                System.out.println("Please, enter your string");
            } else {
                option = Option.FILE;
                System.out.println("Please, enter name of your file");
            }
            System.out.print("Enter: ");
            message = sc.nextLine();
        }
    }

    public String getMessage() {
        return message;
    }

    public Option getOption() {
        return option;
    }

    public boolean isConsole() {
        return getOption() == Option.CONSOLE;
    }

    public boolean isFile() {
        return getOption() == Option.FILE;
    }
}
