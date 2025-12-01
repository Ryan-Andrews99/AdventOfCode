package y2024.Day05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static y2024.Day05.Day05.*;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class Day05Test {

    @Test
    void part2Real() throws FileNotFoundException {
        PublicKey thing = new RSAPublicKey() {
            @Override
            public BigInteger getPublicExponent() {
                return null;
            }

            @Override
            public String getAlgorithm() {
                return "";
            }

            @Override
            public String getFormat() {
                return "";
            }

            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }

            @Override
            public BigInteger getModulus() {
                return null;
            }
        };

        if(thing instanceof SecretKey){

        }
    }

    @Test
    void part2Test() throws FileNotFoundException {
        assertEquals(123, (int) part2("/java/src/main/java/y2024/Day05/testInput.txt"));
    }

    @Test
    void part1Real() throws FileNotFoundException {
        assertEquals(5091, (int) part1("/java/src/main/java/y2024/Day05/input.txt"));
    }

    @Test
    void part1Test() throws FileNotFoundException {
        assertEquals(143, (int) part1("/java/src/main/java/y2024/Day05/testInput.txt"));
    }

    @Test
    void itParsesTheInput() throws FileNotFoundException {
        assertEquals(
                List.of(
                        List.of(
                                "47|53", "97|13", "97|61", "97|47", "75|29", "61|13", "75|53",
                                "29|13", "97|29", "53|29", "61|53", "97|53", "61|29", "47|13",
                                "75|47", "97|75", "47|61", "75|61", "47|29", "75|13", "53|13"),
                        List.of(
                                List.of(75, 47, 61, 53, 29),
                                List.of(97, 61, 53, 29, 13),
                                List.of(75, 29, 13),
                                List.of(75, 97, 47, 61, 53),
                                List.of(61, 13, 29),
                                List.of(97, 13, 75, 29, 47))),
                parseInput("/java/src/main/java/y2024/Day05/testInput.txt"));
    }
}
