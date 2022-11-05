package lab2;

import lab2.algorithm.Divider;
import lab2.model.Symbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DividerTest {
    List<Symbol> listParameter = List.of(
            new Symbol((byte)'A', 0.3),
            new Symbol((byte)'B', 0.25),
            new Symbol((byte)'C', 0.2),
            new Symbol((byte)'D', 0.12),
            new Symbol((byte)'E', 0.08),
            new Symbol((byte)'F', 0.05)
    );

    @Test
    void partition1() {
        var divider = new Divider();
        int start = 0;
        int end = listParameter.size();
        var actual = divider.getBestDiff(listParameter, start, end);
        var expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void partition2() {
        var divider = new Divider();
        int start = 0;
        int end = 2;
        var actual = divider.getBestDiff(listParameter, start, end);
        var expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void partition3() {
        var divider = new Divider();
        int start = 2;
        int end = listParameter.size();
        var actual = divider.getBestDiff(listParameter, start, end);
        var expected = start + 1;
        assertEquals(expected, actual);
    }

    @Test
    void partition4() {
        var divider = new Divider();
        int start = 3;
        int end = listParameter.size();
        var actual = divider.getBestDiff(listParameter, start, end);
        var expected = start + 1;
        assertEquals(expected, actual);
    }


}