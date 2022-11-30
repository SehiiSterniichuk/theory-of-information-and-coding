package lab4.controller.algorithm;

import static utils.ByteUtil.*;

public class HammingCoder  {

    public byte[] createCode(byte[] message) {
        int messageLength = message.length;
        int amountOfParityBits = calculateParity(messageLength);
        int hammingMessageLength = messageLength + amountOfParityBits;
        byte[] hammingMessageDataArray = generateEmptyHammingCode(hammingMessageLength, message);
        calculateParityCells(amountOfParityBits, hammingMessageLength, hammingMessageDataArray);
        return hammingMessageDataArray;
    }

    private static byte[] generateEmptyHammingCode(int hammingMessageLength, byte[] message) {
        int temp, temp2, j;
        temp = j = 0;

        byte[] hammingMessageDataArray = new byte[hammingMessageLength + 1];
        for (int i = 1; i <= hammingMessageLength; i++) {
            temp2 = (int) Math.pow(2, temp);
            if (i % temp2 != 0) {
                hammingMessageDataArray[i] = message[j];
                j++;
            } else {
                temp++;
            }
        }
        return hammingMessageDataArray;
    }

    private static void calculateParityCells(int hammingPower, int hammingMessageLength, byte[] hammingMessageDataArray) {
        for (int i = 0; i < hammingPower; i++) {
            int smallStep = (int) Math.pow(2, i);
            int bigStep = smallStep * 2;
            int start = smallStep, checkPos = start;
            while (true) {
                for (int k = start; k <= start + smallStep - 1; k++) {
                    checkPos = k;
                    if (k > hammingMessageLength) {
                        break;
                    }
                    xor(hammingMessageDataArray, smallStep, checkPos);
                }
                if (checkPos > hammingMessageLength) {
                    break;
                } else {
                    start = start + bigStep;
                }
            }
        }
    }

    public static int calculateParity(int m) {
        int r = 0;
        while (!(m + r + 1 <= Math.pow(2, r))) {
            r++;
        }
        return r;
    }
}
