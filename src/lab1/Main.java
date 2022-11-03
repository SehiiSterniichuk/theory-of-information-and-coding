package lab1;

import java.text.DecimalFormat;

public class Main {
    static private final DecimalFormat numView = new DecimalFormat("#.####");

    public static void printResult(StringAnalysisManager result, String message) {
        System.out.println(message);
        System.out.println("total number of symbols:\t" + result.lengthOfString);
        System.out.println("entropy:\t" + numView.format(result.getTotalEntropy()));
        System.out.println("average entropy per symbol :\t" + numView.format(result.averageEntropy));
        System.out.println("amount of information:\t" + numView.format(result.amountOfInformation));
        System.out.println("max entropy:\t" + numView.format(result.maxEntropy));
        System.out.println("absolute excess:\t" + numView.format(result.absoluteExcess));
        System.out.println("relative excess:\t" + numView.format(result.relativeExcess));
    }


    public static void main(String[] args) {
        var selector = new OptionSelector();
        printResult(new StringAnalysisManager(selector.getString()), selector.getMessage());
        Integer val1 = 1;
        Integer val2 = Integer.valueOf(1);
        System.out.println(val1 == val2);
    }
}
