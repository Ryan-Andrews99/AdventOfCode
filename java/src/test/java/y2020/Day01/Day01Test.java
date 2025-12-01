package y2020.Day01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static y2020.Day01.Day01.part01;
import static y2020.Day01.Day01.part02;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

public class Day01Test {

    @Test
    void itPassesTheTestCasePart1() throws FileNotFoundException {
        assertEquals(part01("/java/src/main/java/y2020/Day01/testInput.txt"), 514579);
    }

    @Test
    void part1() throws FileNotFoundException {
        assertEquals(part01("/java/src/main/java/y2020/Day01/input.txt"), 918339);
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(part02("/java/src/main/java/y2020/Day01/testInput.txt"), 241861950);
    }

    @Test
    void part2() throws FileNotFoundException {
        assertEquals(part02("/java/src/main/java/y2020/Day01/input.txt"), 23869440);
    }
}
