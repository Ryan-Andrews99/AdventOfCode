package y2024.Day02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static y2024.Day02.Day02.*;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day02Test {

    @Test
    void part2RealData() throws FileNotFoundException {
        assertEquals(427, part2("/java/src/main/java/y2024/Day02/input.txt"));
    }

    @Test
    void par2TestData() throws FileNotFoundException {
        assertEquals(4, part2("/java/src/main/java/y2024/Day02/testInput.txt"));
    }

    @Test
    void part1RealData() throws FileNotFoundException {
        assertEquals(282, part1("/java/src/main/java/y2024/Day02/input.txt"));
    }

    @Test
    void part1TestData() throws FileNotFoundException {
        assertEquals(2, part1("/java/src/main/java/y2024/Day02/testInput.txt"));
    }

    @Test
    void itParsesInputCorrectly() throws FileNotFoundException {
        assertEquals(
                List.of(
                        List.of(7, 6, 4, 2, 1),
                        List.of(1, 2, 7, 8, 9),
                        List.of(9, 7, 6, 2, 1),
                        List.of(1, 3, 2, 4, 5),
                        List.of(8, 6, 4, 4, 1),
                        List.of(1, 3, 6, 7, 9)),
                parseInput("/java/src/main/java/y2024/Day02/testInput.txt"));
    }
}
