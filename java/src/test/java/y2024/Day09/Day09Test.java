package y2024.Day09;

import static org.junit.Assert.assertEquals;
import static y2024.Day09.Day09.*;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day09Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(6381624803796L, part2("/java/src/main/java/y2024/Day09/input.txt"));
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(2858, part2("/java/src/main/java/y2024/Day09/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(6359213660505L, part1("/java/src/main/java/y2024/Day09/input.txt"));
    }

    @Test
    void part1Test() throws FileNotFoundException {
        assertEquals(1928, part1("/java/src/main/java/y2024/Day09/testInput.txt"));
    }

    @Test
    void mapStringReturnsExpected() {
        assertEquals(
                "00...111...2...333.44.5555.6666.777.888899",
                returnMapString(List.of(2, 3, 3, 3, 1, 3, 3, 1, 2, 1, 4, 1, 4, 1, 3, 1, 4, 0, 2)));
    }

    @Test
    void itParsesInputAsExpected() throws FileNotFoundException {
        assertEquals(
                List.of(2, 3, 3, 3, 1, 3, 3, 1, 2, 1, 4, 1, 4, 1, 3, 1, 4, 0, 2),
                parseInput("/java/src/main/java/y2024/Day09/testInput.txt"));
    }
}
