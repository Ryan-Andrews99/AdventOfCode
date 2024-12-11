package y2024.Day08;

import static org.junit.Assert.assertEquals;
import static y2024.Day08.Day08.*;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day08Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(396, part2("/java/src/main/java/y2024/Day08/input.txt"));
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(34, part2("/java/src/main/java/y2024/Day08/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(396, part1("/java/src/main/java/y2024/Day08/input.txt"));
    }

    @Test
    void part1Test() throws FileNotFoundException {
        assertEquals(14, part1("/java/src/main/java/y2024/Day08/testInput.txt"));
    }

    @Test
    void itParsesInputAsExpected() throws FileNotFoundException {
        assertEquals(
                List.of(
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", "0", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", "0", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", "0", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", "0", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", "A", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", "A", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", "A", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".")),
                parseInput("/java/src/main/java/y2024/Day08/testInput.txt"));
    }
}
