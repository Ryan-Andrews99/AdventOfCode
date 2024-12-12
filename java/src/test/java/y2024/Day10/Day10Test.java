package y2024.Day10;

import static org.junit.Assert.assertEquals;
import static y2024.Day10.Day10.part1;
import static y2024.Day10.Day10.part2;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

public class Day10Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(1252, part2("/java/src/main/java/y2024/Day10/input.txt"));
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(81, part2("/java/src/main/java/y2024/Day10/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(548, part1("/java/src/main/java/y2024/Day10/input.txt"));
    }

    @Test
    void part1Test() throws FileNotFoundException {
        assertEquals(36, part1("/java/src/main/java/y2024/Day10/testInput.txt"));
    }
}
