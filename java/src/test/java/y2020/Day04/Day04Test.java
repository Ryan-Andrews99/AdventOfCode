package y2020.Day04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static y2020.Day04.Day04.*;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day04Test {

    @Test
    void part2Real() throws FileNotFoundException {
        assertEquals(167, part2("/java/src/main/java/y2020/Day04/input.txt"));
    }

    @Test
    void part2Test2() throws FileNotFoundException {
        assertEquals(4, part2("/java/src/main/java/y2020/Day04/testInput3.txt"));
    }

    @Test
    void part2Test1() throws FileNotFoundException {
        assertEquals(0, part2("/java/src/main/java/y2020/Day04/testInput2.txt"));
    }

    @Test
    void part1RealInput() throws FileNotFoundException {
        assertEquals(208, part1("/java/src/main/java/y2020/Day04/input.txt"));
    }

    @Test
    void part1TestData() throws FileNotFoundException {
        assertEquals(2, part1("/java/src/main/java/y2020/Day04/testInput.txt"));
    }

    @Test
    void itParsesTheTestInputCorrectly() throws FileNotFoundException {
        assertEquals(
                List.of(
                        "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147"
                                + " hgt:183cm",
                        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929",
                        "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm",
                        "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"),
                parseInput("/java/src/main/java/y2020/Day04/testInput.txt"));
    }
}
