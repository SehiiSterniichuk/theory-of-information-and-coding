package lab2.view;

import java.text.DecimalFormat;

public class Result {

    static private final DecimalFormat numView = new DecimalFormat("#.###");

    public static String of(String name,
                            FileSize originalSize,
                            FileSize compressedSize,
                            FileSize zipSize,
                            int fileLengthOfOriginalFile,
                            double entropy,
                            double maxEntropy,
                            double averageLengthOfCodeCombinations,
                            double coefficientOfCompression) {
        return "Result{" +
                "\nName = " + name +
                "\nOriginal size = " + originalSize.toString() +
                "\nCompressed size = " + compressedSize.toString() +
                "\nZip size = " + zipSize.toString() +
                "\nEntropy of original = " + numView.format(entropy) +
                "\nMax entropy of original = " + numView.format(maxEntropy) +
                "\nLength of original file = " + fileLengthOfOriginalFile +
                "\nAverage length of code combinations = " + numView.format(averageLengthOfCodeCombinations) +
                "\nCoefficient of compression =" + numView.format(coefficientOfCompression) +
                "\n}";
    }
}
