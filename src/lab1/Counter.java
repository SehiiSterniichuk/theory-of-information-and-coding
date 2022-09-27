package lab1;

import java.util.HashMap;
import java.util.Map;

public class Counter {

    public static Map<Character, Integer> getNumberOfAppearancesOfEveryCharacter(String str) {
        Map<Character, Integer> symbolCounter = new HashMap<>();
        for (char symbol : str.toCharArray()) {
            if (!symbolCounter.containsKey(symbol)) {
                symbolCounter.put(symbol, 1);
            } else {
                symbolCounter.put(symbol, (symbolCounter.get(symbol) + 1));
            }
        }
        return symbolCounter;
    }
}
