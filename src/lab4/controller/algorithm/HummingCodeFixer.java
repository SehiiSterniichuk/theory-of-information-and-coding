package lab4.controller.algorithm;

import static utils.ByteUtil.changeSign;

public class HummingCodeFixer {

    private boolean isValid;
    private boolean isFixed;
    private int firstErrorIndex = -1;
    private int secondErrorIndex = -1;
    private final Validator validator;

    public HummingCodeFixer(Validator validator) {
        this.validator = validator;
    }

    public void fixError(byte[] message){
        isValid = validator.isValid(message);
        if(!isValid){
            firstErrorIndex = validator.getErrorPosition();
            changeSign(message, firstErrorIndex);
        }
        isValid = validator.isValid(message);
        if(isValid){
            isFixed = true;
            return;
        }
        secondErrorIndex = validator.getErrorPosition();
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public int getFirstErrorIndex() {
        return firstErrorIndex;
    }

    public int getSecondErrorIndex() {
        return secondErrorIndex;
    }
}
