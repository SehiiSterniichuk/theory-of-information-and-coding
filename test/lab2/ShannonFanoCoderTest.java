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
                new Symbol((byte)'A', 0.3),
                new Symbol((byte)'B', 0.25),
                new Symbol((byte)'C', 0.2),
                new Symbol((byte)'D', 0.12),
                new Symbol((byte)'E', 0.08),
                new Symbol((byte)'F', 0.05)
        );
        listParameter = new LinkedList<>(listParameter);
        List<Symbol> listExpected = List.of(
                new Symbol((byte)'A', 0.3, "00"),
                new Symbol((byte)'B', 0.25, "01"),
                new Symbol((byte)'C', 0.2, "10"),
                new Symbol((byte)'D', 0.12, "110"),
                new Symbol((byte)'E', 0.08, "1110"),
                new Symbol((byte)'F', 0.05, "1111")
        );
        ShannonFanoCoder coder = new ShannonFanoCoder();
        coder.createCode(listParameter);
        assertEquals(listExpected, listParameter);
    }
}