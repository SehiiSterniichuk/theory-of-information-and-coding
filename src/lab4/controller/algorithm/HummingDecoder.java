package lab4.controller.algorithm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public record HummingDecoder(HummingCodeFixer fixer) {

    public byte[] decoding(byte[] encodedMessage) {
        fixer.fixError(encodedMessage);
        int temp = 0;
        int temp2;
        try (var hummingMessage = new ByteArrayOutputStream()) {
            for (int i = 1; i <= encodedMessage.length - 1; i++) {
                temp2 = (int) Math.pow(2, temp);
                if (i % temp2 != 0) {
                    hummingMessage.write(encodedMessage[i]);
                } else {
                    temp++;
                }
            }
            return hummingMessage.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }
}

