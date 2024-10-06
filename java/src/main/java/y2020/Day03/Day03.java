package y2020.Day03;

import static java.lang.Math.abs;
import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day03 {

    private Day03() {}
    ;

    public static long part2(String filePath) throws FileNotFoundException {
        var input = parseInput(filePath);
        var slopes =
                List.of(List.of(1, 1), List.of(3, 1), List.of(5, 1), List.of(7, 1), List.of(1, 2));
        return slopes.stream()
                .map(slope -> countTreesForSlope(input, slope.getFirst(), slope.getLast()))
                .reduce(1L, (acc, tree) -> acc * tree);
    }

    public static long part1(String filePath) throws FileNotFoundException {
        return countTreesForSlope(parseInput(filePath), 3, 1);
    }

    public static long countTreesForSlope(List<List<String>> input, int right, int down) {
        long trees = 0L;

        // y,x
        var coords = new int[] {0, 0};

        while (coords[0] < input.size()) {
            var line = input.get(coords[0]);

            if (Objects.equals(line.get(coords[1]), "#")) {
                trees++;
            }

            coords[1] = handleMovement(line.size(), coords[1], right);
            coords[0] += down;
        }
        return trees;
    }

    public static List<List<String>> parseInput(String filepath) throws FileNotFoundException {
        var inputAsString = readFileToString(filepath);
        return Arrays.stream(inputAsString.split(("\n")))
                .map(line -> Arrays.stream(line.split("")).toList())
                .toList();
    }

    public static int handleMovement(int lineLength, int currentPosition, int right) {
        var lastIndex = lineLength - 1;
        if (currentPosition + right > lastIndex) {
            return abs(currentPosition + right - lastIndex - 1);
        } else return currentPosition + right;
    }
}
