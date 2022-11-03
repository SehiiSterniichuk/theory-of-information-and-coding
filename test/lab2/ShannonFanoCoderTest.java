package lab2;

import lab2.algorithm.ShannonFanoCoder;
import lab2.model.Symbol;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShannonFanoCoderTest {

    @Test
    void createCode() {
        List<Symbol> listParameter = List.of(
                new Symbol("A", 0.3),
                new Symbol("B", 0.25),
                new Symbol("C", 0.2),
                new Symbol("D", 0.12),
                new Symbol("E", 0.08),
                new Symbol("F", 0.05)
        );
        listParameter = new LinkedList<>(listParameter);
        List<Symbol> listExpected = List.of(
                new Symbol("A", 0.3, "00"),
                new Symbol("B", 0.25, "01"),
                new Symbol("C", 0.2, "10"),
                new Symbol("D", 0.12, "110"),
                new Symbol("E", 0.08, "1110"),
                new Symbol("F", 0.05, "1111")
        );
        ShannonFanoCoder coder = new ShannonFanoCoder();
        coder.createCode(listParameter);
        assertEquals(listExpected, listParameter);
    }



    @Test
    void code() {
    }

    @Test
    void decode() {
    }
}