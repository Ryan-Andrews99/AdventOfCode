package y2024.Day01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static y2024.Day01.Day01.*;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day01Test {

    @Test
    void part2RealData() throws FileNotFoundException {
        assertEquals(26593248, part2("/java/src/main/java/y2024/Day01/input.txt"));
    }

    @Test
    void part2TestData() throws FileNotFoundException {
        assertEquals(31, part2("/java/src/main/java/y2024/Day01/testInput.txt"));
    }

    @Test
    void part1RealData() throws FileNotFoundException {
        assertEquals(3508942, part1("/java/src/main/java/y2024/Day01/input.txt"));
    }

    @Test
    void part1TestData() throws FileNotFoundException {
        assertEquals(11, part1("/java/src/main/java/y2024/Day01/testInput.txt"));
    }

    @Test
    void itParsesInputCorrectly() throws FileNotFoundException {
        assertEquals(
                List.of(List.of(3, 4, 2, 1, 3, 3), List.of(4, 3, 5, 3, 9, 3)),
                parseInput("/java/src/main/java/y2024/Day01/testInput.txt"));
    }
}
