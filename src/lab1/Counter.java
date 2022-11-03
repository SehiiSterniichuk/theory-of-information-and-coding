package lab1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Counter {

    public static Map<String, Integer> getNumberOfAppearancesOfEveryCharacter(String str) {
        Map<String, Integer> symbolCounter = new HashMap<>();
        var array = str.split("");
        for (var symbol : array) {
            if (!symbolCounter.containsKey(symbol)) {
                symbolCounter.put(symbol, 1);
            } else {
                symbolCounter.put(symbol, (symbolCounter.get(symbol) + 1));
            }
        }
        return symbolCounter;
    }
}
