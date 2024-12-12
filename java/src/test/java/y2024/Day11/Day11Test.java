package y2024.Day11;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

public class Day11Test {

    @Test
    void part2() throws FileNotFoundException {
        assertEquals(
                232454623677743L,
                Day11.returnStonesAfterBlinks("/java/src/main/java/y2024/Day11/input.txt", 75));
    }

    @Test
    void part1() throws FileNotFoundException {
        assertEquals(
                194482,
                Day11.returnStonesAfterBlinks("/java/src/main/java/y2024/Day11/input.txt", 25));
    }

    @Test
    void testData() throws FileNotFoundException {
        assertEquals(
                55312,
                Day11.returnStonesAfterBlinks("/java/src/main/java/y2024/Day11/testInput.txt", 25));
    }
}
