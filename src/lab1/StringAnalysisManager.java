package lab1;

import java.util.*;

import static lab1.Formulas.*;

public class StringAnalysisManager {
    public Integer lengthOfString;
    private double totalEntropy;
    public final double averageEntropy;
    public final double amountOfInformation;
    public final double maxEntropy;
    public final double absoluteExcess;
    public final double relativeExcess;

    StringAnalysisManager(String string) {
        lengthOfString = Integer.valueOf(string.length());
        Map<Character, Integer> symbolCounter = Counter.getNumberOfAppearancesOfEveryCharacter(string);
        symbolCounter.forEach((key, numberOfAppearances) -> {
            double probability = numberOfAppearances.doubleValue() / lengthOfString.doubleValue();
            totalEntropy += calculateEntropyOfSymbol(probability);
        });
        averageEntropy = averageEntropy(symbolCounter.size(), totalEntropy);
        amountOfInformation = amountOfInformation(lengthOfString, totalEntropy);
        maxEntropy = maxEntropy(lengthOfString);
        absoluteExcess = absoluteExcess(maxEntropy, totalEntropy);
        relativeExcess = relativeExcess(absoluteExcess, maxEntropy);
    }

    public double getTotalEntropy() {
        return totalEntropy;
    }
}