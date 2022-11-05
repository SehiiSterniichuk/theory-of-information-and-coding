package lab2.algorithm;

import lab2.model.Symbol;

import java.util.List;

public class Divider {
    public static int getBestDiff(List<Symbol> symbols, int start, int end) {
        double sumUp = 0.0;
        double sumDown = 0.0;
        int y = start;
        int z = end - 1;
        while (y <= z) {
            if (sumUp <= sumDown) {
                sumUp += symbols.get(y).probability();
                y++;
            } else {
                sumDown += symbols.get(z).probability();
                z--;
            }
        }
        return z + 1;
    }
}
