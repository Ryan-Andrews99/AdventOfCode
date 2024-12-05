package y2024.Day03;

import static y2024.Day03.Day03.part1;
import static y2024.Day03.Day03.part2;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day03Test {

    @Test
    void part2RealData() throws FileNotFoundException {
        Assertions.assertEquals(104083373, part2("/java/src/main/java/y2024/Day03/input.txt"));
    }

    @Test
    void part2TestData() throws FileNotFoundException {
        Assertions.assertEquals(48, part2("/java/src/main/java/y2024/Day03/testInput2.txt"));
    }

    @Test
    void part1RealData() throws FileNotFoundException {
        Assertions.assertEquals(192767529, part1("/java/src/main/java/y2024/Day03/input.txt"));
    }

    @Test
    void part1TestData() throws FileNotFoundException {
        Assertions.assertEquals(161, part1("/java/src/main/java/y2024/Day03/testInput.txt"));
    }
}
