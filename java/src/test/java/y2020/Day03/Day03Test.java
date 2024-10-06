package y2020.Day03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static y2020.Day03.Day03.part1;
import static y2020.Day03.Day03.part2;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

public class Day03Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(
                3952291680L, part2("/java/src/main/java/y2020/Day03/input.txt"));
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(336, part2("/java/src/main/java/y2020/Day03/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(232, part1("/java/src/main/java/y2020/Day03//input.txt"));
    }

    @Test
    void part1TestData() throws FileNotFoundException {
        assertEquals(7, part1("/java/src/main/java/y2020/Day03/testInput.txt"));
    }
}
