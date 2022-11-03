package utils;

public class Formulas {
    public static double log2(double value) {
        return Math.log(value) / Math.log(2);
    }

    public static double calculateEntropyOfSymbol(double probability) {
        return (-1) * probability * log2(probability);
    }

    public static double averageEntropy(int numberOfUniqueSymbols, double entropy) {
        return entropy / numberOfUniqueSymbols;
    }

    public static double amountOfInformation(int lengthOfString, double entropy) {
        return entropy * lengthOfString;
    }

    public static double maxEntropy(int numberOfUniqueSymbols) {
        return log2(numberOfUniqueSymbols);
    }

    public static double absoluteExcess(double maxEntropy, double entropy) {
        return maxEntropy - entropy;
    }

    public static double relativeExcess(double absoluteExcess, double maxEntropy) {
        return absoluteExcess / maxEntropy;
    }


}
