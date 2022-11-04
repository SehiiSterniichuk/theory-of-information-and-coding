package lab1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Counter {

    public static Map<Byte, Integer> getNumberOfAppearancesOfEveryCharacter(String str) {
        return getNumberOfAppearancesOfEveryCharacter(str.getBytes());
    }

    public static Map<Byte, Integer> getNumberOfAppearancesOfEveryCharacter(byte[] array) {
        Map<Byte, Integer> symbolCounter = new HashMap<>();
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
