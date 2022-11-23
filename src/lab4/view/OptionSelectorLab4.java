package lab4.view;

import lab2.view.Option;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OptionSelectorLab4 {

    private String message;
    private int number;


    public Option selectMode() {
        final String selectMode = """
                Please, select mode of program
                Type 0 if you want to input your message from console
                Type 1 if you want to work with file
                Type any other symbol if you want to analyze files with different length and errors number
                """;
        System.out.print(selectMode + "\nEnter: ");
        Scanner sc = new Scanner(System.in);
        String mode = sc.nextLine();
        Option modeOption;
        if (mode.contains("0")) {
            modeOption = Option.CONSOLE;
            System.out.print("Enter your message: ");
        } else if (mode.contains("1")) {
            modeOption = Option.FILE;
            System.out.println("Enter your path to your file: ");
        } else {
            modeOption = Option.LAB;
            return modeOption;
        }
        message = sc.nextLine();
        System.out.println("message = " + message);
        return modeOption;
    }

    public ErrorInput selectWayOfErrorInput() {
        final String selectWay = """
                Enter 0 if you want to set error by index
                Enter 1 if you want to select number of random errors
                Enter any other symbol if you don't want to make errors""";
        System.out.print(selectWay + "\nEnter: ");
        Scanner sc = new Scanner(System.in);
        final String choice = sc.nextLine();
        ErrorInput errorInputOption;
        if (choice.contains("0")) {
            errorInputOption = ErrorInput.BY_INDEX;
            System.out.print("How many errors do you want to enter?\nEnter: ");
        } else if (choice.equals("1")) {
            errorInputOption = ErrorInput.BY_NUMBER;
            System.out.print("Number: ");
        } else {
            errorInputOption = ErrorInput.NONE;
            return errorInputOption;
        }
        this.number = parseInt(sc.nextLine(), 1);
        return errorInputOption;
    }

    public int inputNumberAttempts() {
        System.out.println("How many attempts of fixing should Hamming do?");
        System.out.print("Enter: ");
        Scanner sc = new Scanner(System.in);
        return parseInt(sc.nextLine(), 2);
    }

    private static int parseInt(final String line, final int defaultValue) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("You have entered bad number and it was replaced with " + defaultValue + " by default");
            return defaultValue;
        }
    }

    public List<Integer> inputList(int number) {
        List<Integer> list = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= number; i++) {
            System.out.printf("Enter[%s] = ", i);
            var value = parseInt(sc.nextLine(), 1);
            list.add(value);
        }
        return list;
    }

    public String getMessage() {
        return message;
    }

    public int getNumber() {
        return number;
    }
}
