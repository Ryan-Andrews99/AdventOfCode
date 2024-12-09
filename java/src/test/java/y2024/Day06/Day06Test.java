package y2024.Day06;

import static org.junit.Assert.assertEquals;
import static y2024.Day06.Day06.*;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day06Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(1909, part2("/java/src/main/java/y2024/Day06/input.txt"));
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(6, part2("/java/src/main/java/y2024/Day06/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(5162, part1("/java/src/main/java/y2024/Day06/input.txt"));
    }

    @Test
    void part1Test() throws FileNotFoundException {
        assertEquals(41, part1("/java/src/main/java/y2024/Day06/testInput.txt"));
    }

    @Test
    void itParsesInputCorrectly() throws FileNotFoundException {
        assertEquals(
                List.of(
                        List.of(".", ".", ".", ".", "#", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", "#"),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", "#", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", "#", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", "#", ".", ".", "^", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", ".", ".", "#", "."),
                        List.of("#", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                        List.of(".", ".", ".", ".", ".", ".", "#", ".", ".", ".")),
                parseInput("/java/src/main/java/y2024/Day06/testInput.txt"));
    }
}
