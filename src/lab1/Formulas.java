package lab1;

public class Formulas {
    public static double log2(double value) {
        return Math.log(value) / Math.log(2);
    }

    public static double calculateEntropyOfSymbol(double probability) {
        return (-1) * probability * log2(probability);
    }

    public static double averageEntropy(int amountOfEntropy, double entropy) {
        return entropy / amountOfEntropy;
    }

    public static double amountOfInformation(int lengthOfString, double entropy) {
        return entropy * lengthOfString;
    }

    public static double maxEntropy(int lengthOfString) {
        return log2(lengthOfString);
    }

    public static double absoluteExcess(double maxEntropy, double entropy) {
        return maxEntropy - entropy;
    }

    public static double relativeExcess(double absoluteExcess, double maxEntropy) {
        return absoluteExcess / maxEntropy;
    }
}
