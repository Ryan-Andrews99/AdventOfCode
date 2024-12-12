package y2024.Day10;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Day10 {

    public static long part2(String filePath) throws FileNotFoundException {
        var input = parseInput(filePath);
        var startCoords = getCoordsOfTrailStart(input);
        return startCoords.stream().map(c -> returnRating(c, input, false)).reduce(Long::sum).get();
    }

    public static long part1(String filePath) throws FileNotFoundException {
        var input = parseInput(filePath);
        var startCoords = getCoordsOfTrailStart(input);
        return startCoords.stream().map(c -> returnRating(c, input, true)).reduce(Long::sum).get();
    }

    public static long returnRating(
            List<Integer> startCoord, List<List<Integer>> input, boolean part1) {
        var rating = 0L;
        var indexed = new ArrayList<List<Integer>>();
        var coordsToCheck = new Stack<List>();
        coordsToCheck.add(List.of(startCoord, 0));

        while (!coordsToCheck.empty()) {
            var currentEntry = coordsToCheck.pop();
            var curentIndex = (List<Integer>) currentEntry.getFirst();
            var currentNumber = (int) currentEntry.getLast();

            if (currentNumber == 9) {
                rating++;
                continue;
            }
            var target = currentNumber + 1;
            returnNeighboursNoDiagonals(curentIndex, input.size()).stream()
                    .filter(n -> input.get(n.getFirst()).get(n.getLast()).equals(target))
                    .forEach(
                            c -> {
                                if (part1) {
                                    if (!indexed.contains(c)) {
                                        indexed.add(c);
                                        coordsToCheck.add(List.of(c, target));
                                    }
                                } else {
                                    coordsToCheck.add(List.of(c, target));
                                }
                                ;
                            });
        }
        return rating;
    }

    public static List<List<Integer>> parseInput(String filePath) throws FileNotFoundException {
        var fileAsString = readFileToString(filePath);
        return Arrays.stream(fileAsString.split("\n"))
                .map(line -> Arrays.stream(line.split("")).map(Integer::parseInt).toList())
                .toList();
    }

    public static List<List<Integer>> getCoordsOfTrailStart(List<List<Integer>> input) {
        var coords = new ArrayList<List<Integer>>();
        var y = 0;
        var x = 0;

        while (y < input.size()) {
            var line = input.get(y);
            while (x < line.size()) {
                if (line.get(x).equals(0)) {
                    coords.add(List.of(y, x));
                }
                x++;
            }
            x = 0;
            y++;
        }
        return coords;
    }

    public static List<List<Integer>> returnNeighboursNoDiagonals(
            List<Integer> index, int maxListLength) {
        var x = index.getLast();
        var y = index.getFirst();
        var min = 0;

        var maxX = Math.min(x + 1, maxListLength - 1);
        var maxY = Math.min(y + 1, maxListLength - 1);
        var minX = Math.max(x - 1, min);
        var minY = Math.max(y - 1, min);
        return Stream.of(List.of(maxY, x), List.of(y, minX), List.of(y, maxX), List.of(minY, x))
                .distinct()
                .toList();
    }
}
