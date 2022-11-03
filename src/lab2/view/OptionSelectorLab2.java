package lab2.view;

import java.util.Scanner;

public class OptionSelectorLab2 {

    public enum Option{
        FILE, CONSOLE
    }

    private Option option;

    private String message;

    public OptionSelectorLab2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("if you want to work with file enter 1, otherwise you will be work with string");
        System.out.print("Enter: ");
        String choice = sc.nextLine();
        if (!choice.equals("1")) {
            option = Option.CONSOLE;
            System.out.println("Please, enter your string");
        } else {
            option = Option.FILE;
            System.out.println("Please, enter name of your file");
        }
        System.out.print("Enter: ");
        message = sc.nextLine();
    }

    public String getMessage() {
        return message;
    }

    public Option getOption() {
        return option;
    }

    public boolean isConsole(){
        return getOption() == Option.CONSOLE;
    }
}
