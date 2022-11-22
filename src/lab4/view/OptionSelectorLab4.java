package lab4.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OptionSelectorLab4 {

    private String message;
    private Mode modeOption;
    private ErrorsInput errorsInputOption;
    private int number;

    enum Mode {
        CONSOLE, FILE, LENGTH_ANALIZ
    }

    enum ErrorsInput {
        BY_INDEX, BY_NUMBER
    }

    public Mode selectMode() {
        final String selectMode = """
                Please, select mode of program
                Type 0 if you want to input your message from console
                Type 1 if you want to work with file
                Type any other symbol if you want to analyze files with different length and errors number
                """;
        System.out.print(selectMode + "\nEnter: ");
        try (Scanner sc = new Scanner(System.in)) {
            String mode = sc.nextLine();
            if (mode.contains("0")) {
                modeOption = Mode.CONSOLE;
                System.out.println("Enter your message: ");
            } else if (mode.contains("1")) {
                modeOption = Mode.FILE;
                System.out.println("Enter your path to your file: ");
            } else {
                modeOption = Mode.LENGTH_ANALIZ;
            }
            if (modeOption != Mode.LENGTH_ANALIZ) {
                message = sc.nextLine();
            }
        }
        return modeOption;
    }

    public ErrorsInput selectWayOfErrorInput() {
        final String selectWay = """
                Enter 0 if you want to set error by index
                Enter any other symbol if you want to select number of random errors
                """;
        System.out.print(selectWay + "\nEnter: ");
        try (Scanner sc = new Scanner(System.in)) {
            final String choice = sc.nextLine();
            if (choice.contains("0")) {
                errorsInputOption = ErrorsInput.BY_INDEX;
                System.out.print("How many errors do you want to enter?\nEnter: ");
            } else {
                errorsInputOption = ErrorsInput.BY_NUMBER;
                System.out.print("Number: ");
            }
            this.number = parseInt(sc.nextLine(), 1);
        }
        return errorsInputOption;
    }

    public int inputNumberAttempts(){
        System.out.println("How many attempts of fixing should Hamming do?");
        System.out.print("Enter");
        try (Scanner sc = new Scanner(System.in)) {
            return parseInt(sc.nextLine(), 2);
        }
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
        try (Scanner sc = new Scanner(System.in)) {
            for (int i = 1; i <= number; i++) {
                System.out.print("Number" + i + ": ");
                var value = parseInt(sc.nextLine(), 1);
                list.add(value);
            }
        }
        return list;
    }

    public String getMessage() {
        return message;
    }

    public ErrorsInput getErrorsInputOption() {
        return errorsInputOption;
    }

    public int getNumber() {
        return number;
    }
}
