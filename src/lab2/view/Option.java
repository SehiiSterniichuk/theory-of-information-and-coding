package lab2.view;

public enum Option {
    FILE, CONSOLE, LAB;

    public boolean isConsole(){
        return this == CONSOLE;
    }
}
