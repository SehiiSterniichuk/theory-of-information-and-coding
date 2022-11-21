package lab4.algorithm;

public class Validator {

    private int errorPosition;

    public boolean isValid(byte[] data) {
        errorPosition = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '1') {
                if (errorPosition == -1) {
                    errorPosition = i;
                } else {
                    errorPosition = errorPosition ^ i;
                }
            }
        }
        return errorPosition == 0;
    }

    public int getErrorPosition() {
        return errorPosition;
    }
}
