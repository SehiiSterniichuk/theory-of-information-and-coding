package lab2.algorithm;

import lab2.model.Symbol;

import java.util.List;

public class Divider {
    public int getBestDiff(List<Symbol> symbols, int start, int end) {
        var allSum = sum(start, end, symbols);
        double bestDiff = 2;
        int bestIndex = start;
        int i = start;
        for (; i < end && i < symbols.size(); i++) {
            var sumUp = sum(i, end, symbols);
            var sumDown = allSum - sumUp;
            var oldDiff = bestDiff;
            var oldIndex = bestIndex;
            bestDiff = Math.abs(sumUp - sumDown);
            bestIndex = i;
            if(oldDiff < bestDiff){
                bestDiff = oldDiff;
                bestIndex = oldIndex;
                break;
            }
        }
        return bestIndex;
    }

    private double sum(int start, int end, List<Symbol> symbols) {
        double sum = 0;
        for (int i = start; i < end && i < symbols.size(); i++) {
            sum += symbols.get(i).probability();
        }
        return sum;
    }
}
