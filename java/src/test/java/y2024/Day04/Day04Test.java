package y2024.Day04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static y2024.Day04.Day04.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Day04Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(1965, part2("/java/src/main/java/y2024/Day04/input.txt"));
    }

    @Test
    void part2Test2() throws FileNotFoundException {
        assertEquals(0, part2("/java/src/main/java/y2024/Day04/test2Input.txt"));
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(9, part2("/java/src/main/java/y2024/Day04/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(2603, part1("/java/src/main/java/y2024/Day04/input.txt"));
    }

    @Test
    void part1Test() throws FileNotFoundException {
        assertEquals(18, part1("/java/src/main/java/y2024/Day04/testInput.txt"));
    }

    @ParameterizedTest
    @MethodSource("neighbourTests")
    void itReturnsNeighbours(List<Integer> coord, int maxSize, List<List<Integer>> expected) {
        var actual = returnNeighbourCoords(coord, maxSize);
        assertTrue(
                expected.size() == actual.size()
                        && actual.containsAll(expected)
                        && expected.containsAll(actual));
    }

    @Test
    void itParsesTheInputTo2dArray() throws FileNotFoundException {
        assertEquals(
                parseInput("/java/src/main/java/y2024/Day04/testInput.txt"),
                List.of(
                        List.of("M", "M", "M", "S", "X", "X", "M", "A", "S", "M"),
                        List.of("M", "S", "A", "M", "X", "M", "S", "M", "S", "A"),
                        List.of("A", "M", "X", "S", "X", "M", "A", "A", "M", "M"),
                        List.of("M", "S", "A", "M", "A", "S", "M", "S", "M", "X"),
                        List.of("X", "M", "A", "S", "A", "M", "X", "A", "M", "M"),
                        List.of("X", "X", "A", "M", "M", "X", "X", "A", "M", "A"),
                        List.of("S", "M", "S", "M", "S", "A", "S", "X", "S", "S"),
                        List.of("S", "A", "X", "A", "M", "A", "S", "A", "A", "A"),
                        List.of("M", "A", "M", "M", "M", "X", "M", "M", "M", "M"),
                        List.of("M", "X", "M", "X", "A", "X", "M", "A", "S", "X")));
    }

    private static Stream<Arguments> neighbourTests() {
        return Stream.of(
                Arguments.of(
                        List.of(2, 2),
                        5,
                        List.of(
                                List.of(1, 1),
                                List.of(1, 2),
                                List.of(1, 3),
                                List.of(2, 1),
                                List.of(2, 3),
                                List.of(3, 1),
                                List.of(3, 2),
                                List.of(3, 3))));
    }
}
