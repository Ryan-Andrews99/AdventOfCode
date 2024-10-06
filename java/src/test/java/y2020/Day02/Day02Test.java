package y2020.Day02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static y2020.Day02.Day02.*;

import java.io.FileNotFoundException;
import java.util.*;
import org.junit.jupiter.api.Test;

public class Day02Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(354, part2("/java/src/main/java/y2020/Day02/input.txt"));
    }

    @Test
    void part2TestData() throws FileNotFoundException {
        assertEquals(1, part2("/java/src/main/java/y2020/Day02/TestInput.txt"));
    }

    @Test
    void part1Test() throws FileNotFoundException {
        assertEquals(614, part1("/java/src/main/java/y2020/Day02/input.txt"));
    }

    @Test
    void part1TestData() throws FileNotFoundException {
        assertEquals(2, part1("/java/src/main/java/y2020/Day02/testInput.txt"));
    }

    @Test
    void parsesFileIntoExpectedOutput() throws FileNotFoundException {
        var parsedFile =
                parseInput("/java/src/main/java/y2020/Day02/testInput.txt");
        var expectedMap =
                new ArrayList<>(
                        Arrays.asList(
                                List.of("1-3 a", "abcde"),
                                List.of("1-3 b", "cdefg"),
                                List.of("2-9 c", "ccccccccc")));
        assertEquals(expectedMap, parsedFile);
    }
}
