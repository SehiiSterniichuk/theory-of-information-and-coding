package lab4.controller.algorithm;

import java.util.LinkedList;
import java.util.List;

import static utils.ByteUtil.changeSign;

public class HammingCodeFixer {

    private boolean isValid;
    private final Validator validator;
    private final List<Integer> errors;
    private final int attemptsToFix;

    public HammingCodeFixer(Validator validator, int attemptsToFix) {
        this.validator = validator;
        this.attemptsToFix = attemptsToFix;
        this.errors = new LinkedList<>();
    }

    public HammingCodeFixer(int attemptsToFix) {
        this(new Validator(), attemptsToFix);
    }

    public void fixError(byte[] message) {
        isValid = validator.isValid(message);
        int errorIndex;
        for (int i = 0; i < attemptsToFix && !isValid; i++) {
            errorIndex = validator.getErrorPosition();
            if (errorIndex >= message.length || errorIndex <= 1) {
                errors.add(-1);
                return;
            }
            changeSign(message, errorIndex);
            errors.add(errorIndex);
            isValid = validator.isValid(message);
            if (isValid) {
                return;
            }
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public List<Integer> getErrors() {
        return new LinkedList<>(this.errors);
    }
}
