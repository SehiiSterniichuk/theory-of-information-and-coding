package lab4.view;

import java.util.Scanner;

public class OptionSelectorLab4 {

    private String message;
    private Mode modeOption;
    private ErrorsInput errorsInputOption;
    private int number;

    enum Mode {
        CONSOLE, FILE, LENGTH_ANALIZ
    }
    enum ErrorsInput{
        BY_INDEX, BY_NUMBER
    }

    public Mode selectMode(){
        final String selectMode = """
                Please, select mode of program
                Type 0 if you want to input your message from console
                Type 1 if you want to work with file
                Type any other symbol if you want to analyze files with different length and errors number
                """;
        System.out.print(selectMode + "\nEnter: ");
        try(Scanner sc = new Scanner(System.in)){
            String mode = sc.nextLine();
            if(mode.contains("0")){
                modeOption = Mode.CONSOLE;
                System.out.println("Enter your message: ");
            } else if (mode.contains("1")) {
                modeOption = Mode.FILE;
                System.out.println("Enter your path to your file: ");
            }else{
                modeOption = Mode.LENGTH_ANALIZ;
            }
            if(modeOption != Mode.LENGTH_ANALIZ){
                message = sc.nextLine();
            }
        }
        return modeOption;
    }

    public ErrorsInput selectWayOfErrorInput(){
        final String selectWay = """
                Enter 0 if you want to set error by index
                Enter any other symbol if you want to select number of random errors
                """;
        System.out.print(selectWay + "\nEnter: ");
        try(Scanner sc = new Scanner(System.in)){
            final String choice = sc.nextLine();
            if(choice.contains("0")){
                System.out.print("Index: ");
            }else{
                System.out.print("Number: ");
            }
            this.number = Integer.parseInt(sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("You have entered bad number and it was replaced with 1 by default");
            this.number = 1;
        }
        return errorsInputOption;
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
