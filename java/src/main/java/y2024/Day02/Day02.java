package y2024.Day02;

import static java.lang.Math.abs;
import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {

    private Day02() {}

    public static long part2(String filepath) throws FileNotFoundException {
        return parseInput(filepath).stream()
                .filter(line -> isListValid(line) || isListValidSkippingOne(new ArrayList<>(line)))
                .toList()
                .size();
    }

    public static long part1(String filepath) throws FileNotFoundException {
        return parseInput(filepath).stream().filter(Day02::isListValid).toList().size();
    }

    public static List<List<Integer>> parseInput(String filepath) throws FileNotFoundException {
        return Arrays.stream(readFileToString(filepath).trim().split("\n"))
                .map(line -> Arrays.stream(line.trim().split(" ")).map(Integer::parseInt).toList())
                .toList();
    }

    public static boolean isListValid(List<Integer> list) {
        var index = 0;
        Boolean isDiffNegative = null;

        while (index < list.size() - 1) {
            var current = list.get(index);
            var next = list.get(index + 1);
            var diff = current - next;

            if (abs(diff) < 1 || abs(diff) > 3) {
                return false;
            }

            if (isDiffNegative == null) {
                isDiffNegative = diff < 0;
            } else if (diff < 0 != isDiffNegative) {
                return false;
            }
            index++;
        }
        return true;
    }

    public static boolean isListValidSkippingOne(ArrayList<Integer> line) {
        var indexToSkip = 0;
        while (indexToSkip < line.size()) {
            var lineLessOne = new ArrayList<>(line);
            lineLessOne.remove(indexToSkip);

            if (isListValid(lineLessOne)) {
                return true;
            }
            indexToSkip++;
        }
        ;
        return false;
    }
}
