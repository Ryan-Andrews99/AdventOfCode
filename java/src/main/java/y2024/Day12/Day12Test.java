package y2024.Day12;

import static org.junit.Assert.assertEquals;
import static y2024.Day12.Day12.part1;
import static y2024.Day12.Day12.part2;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

public class Day12Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(554603, part2("/java/src/main/java/y2024/Day12/input.txt"));
    }

    @Test
    void part2TestData3() throws FileNotFoundException {
        assertEquals(1206, part2("/java/src/main/java/y2024/Day12/testInput3.txt"));
    }

    @Test
    void part2TestData() throws FileNotFoundException {
        assertEquals(80, part2("/java/src/main/java/y2024/Day12/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(1449902, part1("/java/src/main/java/y2024/Day12/input.txt"));
    }

    @Test
    void part1TestData3() throws FileNotFoundException {
        assertEquals(1930, part1("/java/src/main/java/y2024/Day12/testInput3.txt"));
    }

    @Test
    void part1TestData2() throws FileNotFoundException {
        assertEquals(772, part1("/java/src/main/java/y2024/Day12/testInput2.txt"));
    }

    @Test
    void part1TestData() throws FileNotFoundException {
        assertEquals(140, part1("/java/src/main/java/y2024/Day12/testInput.txt"));
    }
}
