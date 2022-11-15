package lab2.view;

import java.text.DecimalFormat;

public record Result(String name,
                     FileSize originalSize,
                     FileSize compressedSize,
                     FileSize zipSize,
                     int fileLengthOfOriginalFile,
                     double entropy,
                     double maxEntropy,
                     double averageLengthOfCodeCombinations,
                     double coefficientOfCompression,
                     double shannonSaveSpaceRatio,
                     double zipSaveSpaceRatio,
                     String methodName) {

    static private final DecimalFormat numView = new DecimalFormat("#.###");

    public String toString() {
        return "Result{" +
                "\nName = " + name +
                "\nOriginal size = " + originalSize.toString() +
                "\nCompressed size = " + compressedSize.toString() +
                "\nZip size = " + zipSize.toString() +
                "\nEntropy of original = " + entropyString() +
                "\nMax entropy of original = " + numView.format(maxEntropy) +
                "\nLength of original file = " + fileLengthOfOriginalFile +
                "\nAverage length of code combinations = " + numView.format(averageLengthOfCodeCombinations) +
                "\nCoefficient of compression = " + coefficientOfCompressionString() +
                "\nZip save space ratio = " + zipSaveSpaceRatioString() +
                "\n"+ methodName +" save space ratio = " + shannonSaveSpaceRatioString() +
                "\n}";
    }

    public String entropyString() {
        return numView.format(entropy);
    }

    public String coefficientOfCompressionString() {
        return numView.format(coefficientOfCompression);
    }

    public String zipSaveSpaceRatioString() {
        return numView.format(zipSaveSpaceRatio * 100) + "%";
    }

    public String shannonSaveSpaceRatioString() {
        return numView.format(shannonSaveSpaceRatio * 100) + "%";
    }
}
