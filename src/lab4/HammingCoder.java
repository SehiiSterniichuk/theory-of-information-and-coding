package lab4;

import java.nio.charset.StandardCharsets;
import static lab4.ByteUtil.*;

public class HammingCoder  {
    public static void main(String[] args) {
        var code  = "101010";

        byte[] bytes = code.getBytes();
        System.out.println(bytes.length);
        var encoding = encoding(bytes);
        System.out.println(new String(encoding, StandardCharsets.UTF_8));
        var decoder = new HummingDecoder(new HummingCodeFixer(new Validator()));
        changeSign(encoding, 3);
        System.out.println(new String(encoding, StandardCharsets.UTF_8));
        var decoded = decoder.decoding(encoding);
        System.out.println(new String(decoded, StandardCharsets.UTF_8));
    }

    public static byte[] encoding(byte[] message) {
        int messageLength = message.length;
        int amountOfParityBits = calculateParity(messageLength);
        int hammingMessageLength = messageLength + amountOfParityBits;
        byte[] hammingMessageDataArray = generateEmptyHammingCode(hammingMessageLength, message);
        calculateParityCells(amountOfParityBits, hammingMessageLength, hammingMessageDataArray);
        return hammingMessageDataArray;
    }

    private static byte[] generateEmptyHammingCode(int hammingMessageLength, byte[] message) {
        int temp = 0;
        int temp2;
        int j = 0;

        byte[] hammingMessageDataArray = new byte[hammingMessageLength + 1]; //+1 because starts with 1
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
}
