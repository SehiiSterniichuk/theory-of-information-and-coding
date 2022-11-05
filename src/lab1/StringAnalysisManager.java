package lab1;

import lab2.model.Symbol;

import java.util.*;

import static utils.Formulas.*;

public class StringAnalysisManager {
    public Integer lengthOfString;
    private double totalEntropy;
    public final double averageEntropy;
    public final double amountOfInformation;
    public final double maxEntropy;
    public final double absoluteExcess;
    public final double relativeExcess;
    private final List<Symbol> symbols;

    public StringAnalysisManager(String string) {
        this(string.getBytes());
    }

    public StringAnalysisManager(byte[] string) {
        lengthOfString = string.length;
        Map<Byte, Integer> symbolCounter = Counter.getNumberOfAppearancesOfEveryCharacter(string);
        symbols = new LinkedList<>();
        symbolCounter.forEach((key, numberOfAppearances) -> {
            double probability = numberOfAppearances.doubleValue() / lengthOfString.doubleValue();
            totalEntropy += calculateEntropyOfSymbol(probability);
            symbols.add(new Symbol(key,probability));
        });
        int size = symbolCounter.size();
        System.out.println("size = " + size);
        averageEntropy = averageEntropy(size, totalEntropy);
        amountOfInformation = amountOfInformation(lengthOfString, totalEntropy);
        maxEntropy = maxEntropy(size);
        absoluteExcess = absoluteExcess(maxEntropy, totalEntropy);
        relativeExcess = relativeExcess(absoluteExcess, maxEntropy);
    }

    public List<Symbol> getSymbols() {
        return new ArrayList<>(symbols);
    }

    public double getTotalEntropy() {
        return totalEntropy;
    }
}