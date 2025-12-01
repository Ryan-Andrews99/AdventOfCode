package y2024.Day07;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static y2024.Day07.Day07.*;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day07Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(3598800864292L, part2("/java/src/main/java/y2024/Day07/input.txt"));
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(11387L, part2("/java/src/main/java/y2024/Day07/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(3598800864292L, part1("/java/src/main/java/y2024/Day07/input.txt"));
    }

    @Test
    void part1Test() throws FileNotFoundException {
        assertEquals(3749L, part1("/java/src/main/java/y2024/Day07/testInput.txt"));
    }

    @Test
    void itParsesInputCorrectly() throws FileNotFoundException {
        assertEquals(
                List.of(
                        List.of(190, 10, 19),
                        List.of(3267, 81, 40, 27),
                        List.of(83, 17, 5),
                        List.of(156, 15, 6),
                        List.of(7290, 6, 8, 6, 15),
                        List.of(161011, 16, 10, 13),
                        List.of(192, 17, 8, 14),
                        List.of(21037, 9, 7, 18, 13),
                        List.of(292, 11, 6, 16, 20)),
                parseInput("/java/src/main/java/y2024/Day07/testInput.txt"));
    }
}
