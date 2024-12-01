package y2024.Day01;

import static java.lang.Math.abs;
import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day01 {
    private Day01() {}

    public static long part2(String filePath) throws FileNotFoundException {
        var parsedInput = parseInput(filePath);
        var left = parsedInput.getFirst();
        var right = parsedInput.getLast();

        assert (left.size() == right.size()) : "Left and right lists are unequal size, argh!";

        var mapOfRightValues = new HashMap<Integer, Integer>();

        right.forEach(
                val -> {
                    if (mapOfRightValues.containsKey(val)) {
                        mapOfRightValues.put(val, mapOfRightValues.get(val) + 1);
                    } else mapOfRightValues.put(val, 1);
                });

        var sum = 0L;
        int index = 0;

        while (index < left.size()) {
            sum +=
                    mapOfRightValues.containsKey(left.get(index))
                            ? (long) left.get(index) * mapOfRightValues.get(left.get(index))
                            : 0;

            index++;
        }

        return sum;
    }

    public static long part1(String filePath) throws FileNotFoundException {
        var parsedInput = parseInput(filePath);
        var sortedLeft = parsedInput.getFirst().stream().sorted().toList();
        var sortedRight = parsedInput.getLast().stream().sorted().toList();

        assert (sortedLeft.size() == sortedRight.size())
                : "Left and right lists are unequal size, argh!";

        var sum = 0L;
        int index = 0;

        while (index < sortedLeft.size()) {
            sum += abs(sortedLeft.get(index) - sortedRight.get(index));
            index++;
        }

        return sum;
    }

    public static List<List<Integer>> parseInput(String filePath) throws FileNotFoundException {
        var inputAsString = readFileToString(filePath);
        var left = new ArrayList<String>();
        var right = new ArrayList<String>();
        Arrays.stream(inputAsString.split("\n"))
                .forEach(
                        line -> {
                            var splitLine = line.split(" {3}");
                            left.add(splitLine[0]);
                            right.add(splitLine[1]);
                        });
        return List.of(
                left.stream().map(Integer::parseInt).toList(),
                right.stream().map(Integer::parseInt).toList());
    }
}
