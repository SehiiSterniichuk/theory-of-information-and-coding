package lab4.view;

import lab2.view.FileSize;

import java.nio.charset.StandardCharsets;
import java.util.List;

public record ResultLab4(String source, FileSize sizeOfFile,
                         byte[] code,
                         byte[] hammingCode,
                         byte[] hammingDeCode,
                         boolean decodedIsCorrectByValidator,
                         boolean reallyIsCorrectDecoding,
                         List<Integer> foundErrors,
                         List<Integer> realErrors) {

    @Override
    public String toString() {
        return "Result{" +
                "\nSource: " + source +
                "\nOriginal size: " + sizeOfFile +
                "\nMessage: " + arrayToString(code) +
                "\nCoded Message: " + arrayToString(hammingCode) +
                "\nDecoded message: " + arrayToString(hammingDeCode) +
                "\nCorrect : " + reallyIsCorrectDecoding +
                "\nCorrect by Hamming: " + decodedIsCorrectByValidator +
                "\nFound errors: " + foundErrors +
                "\nReal errors: " + realErrors +
                "\n}";
    }

    private static String arrayToString(byte[] array) {
        return new String(array, StandardCharsets.UTF_8);
    }
}
