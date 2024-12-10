package y2024.Day07;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Day07 {

    public static long part2(String filepath) throws FileNotFoundException {
        return parseInput(filepath).stream()
                .filter(eq -> hasCombinationThatGetsTarget(eq, true))
                .map(List::getFirst)
                .reduce(0L, (acc, num) -> acc + num);
    }

    public static long part1(String filepath) throws FileNotFoundException {
        return parseInput(filepath).stream()
                .filter(eq -> hasCombinationThatGetsTarget(eq, false))
                .map(List::getFirst)
                .reduce(0L, (acc, num) -> acc + num);
    }

    public static List<List<Long>> parseInput(String filepath) throws FileNotFoundException {
        var fileAsString = readFileToString(filepath);
        return Arrays.stream(fileAsString.split("\n"))
                .map(
                        line ->
                                Arrays.stream(line.split(" "))
                                        .map(num -> num.trim().replace(":", ""))
                                        .map(Long::parseLong)
                                        .toList())
                .toList();
    }

    public static boolean hasCombinationThatGetsTarget(List<Long> current, boolean concat) {
        var target = current.getFirst();
        var remaining = current.subList(1, current.size());
        return getAllCombinations(remaining, remaining.size() - 1, concat).stream()
                .anyMatch(target::equals);
    }

    public static List<Long> getAllCombinations(List<Long> list, int index, boolean concat) {

        if (index == 1) {
            return concat
                    ? List.of(
                            list.getFirst() + list.getLast(),
                            list.getFirst() * list.getLast(),
                            concatTwo(list.getFirst(), list.getLast()))
                    : List.of(list.getFirst() + list.getLast(), list.getFirst() * list.getLast());
        }
        var current = list.get(index);
        var combinations = getAllCombinations(list.subList(0, index), index - 1, concat);
        return combinations.stream()
                .map(
                        c ->
                                concat
                                        ? List.of(c + current, c * current, concatTwo(c, current))
                                        : List.of(c + current, c * current))
                .flatMap(List::stream)
                .toList();
    }

    private static Long concatTwo(Long num1, Long num2) {
        return Long.parseLong(String.format("%s%s", num1, num2));
    }
}
