package y2024.Day04;

import static java.lang.Math.abs;
import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Day04 {

    public static long part2(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var coordsOfX = new ArrayList<List<Integer>>();
        var y = 0;
        var x = 0;
        while (y < input.size()) {
            var line = input.get(y);
            while (x < line.size()) {
                if (line.get(x).equals("A")) {
                    coordsOfX.add(List.of(y, x));
                }
                x++;
            }
            x = 0;
            y++;
        }
        return coordsOfX.stream().filter(mCoord -> isXMas(mCoord, input)).count();
    }

    public static long part1(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var coordsOfX = new ArrayList<List<Integer>>();
        var y = 0;
        var x = 0;
        while (y < input.size()) {
            var line = input.get(y);
            while (x < line.size()) {
                if (line.get(x).equals("X")) {
                    coordsOfX.add(List.of(y, x));
                }
                x++;
            }
            x = 0;
            y++;
        }
        return coordsOfX.stream()
                .map(coord -> numberOfXmases(coord, input))
                .reduce(0, (acc, num) -> acc += num);
    }

    public static int numberOfXmases(List<Integer> coord, List<List<String>> input) {
        int numberOfXmas = 0;
        var coordsToCheck = new Stack<ArrayList>();
        coordsToCheck.add(new ArrayList(Arrays.asList(coord, "X")));

        while (!coordsToCheck.empty()) {
            var nextCoord = coordsToCheck.pop();
            var builtString = (String) nextCoord.get(1);
            var direction = nextCoord.getLast();
            var coordsOfCurrent = (List<Integer>) nextCoord.getFirst();
            if ((builtString).equals("XMAS")) {
                numberOfXmas++;
            } else {
                var nextLetter = returnNextChar(builtString);
                var validNeighbours =
                        returnNeighbourCoords(coordsOfCurrent, input.size()).stream()
                                .filter(
                                        neighbour ->
                                                input.get(neighbour.getFirst())
                                                        .get(neighbour.getLast())
                                                        .equals(nextLetter))
                                .filter(
                                        neighbour ->
                                                builtString.equals("X")
                                                        || direction.equals(
                                                                returnDirection(
                                                                        coordsOfCurrent,
                                                                        neighbour)))
                                .toList();
                validNeighbours.stream()
                        .forEach(
                                n ->
                                        coordsToCheck.add(
                                                new ArrayList(
                                                        Arrays.asList(
                                                                n,
                                                                builtString + nextLetter,
                                                                returnDirection(
                                                                        coordsOfCurrent, n)))));
            }
        }
        return numberOfXmas;
    }

    public static List<List<String>> parseInput(String filepath) throws FileNotFoundException {
        var inputAsString = readFileToString(filepath);
        return Arrays.stream(inputAsString.split(("\n")))
                .map(line -> Arrays.stream(line.split("")).toList())
                .toList();
    }

    // index [y,x]
    // assume rows and cols are equal
    public static List<List<Integer>> returnNeighbourCoords(
            List<Integer> index, int maxListLength) {
        var x = index.getLast();
        var y = index.getFirst();
        var min = 0;

        var maxX = Math.min(x + 1, maxListLength - 1);
        var maxY = Math.min(y + 1, maxListLength - 1);
        var minX = Math.max(x - 1, min);
        var minY = Math.max(y - 1, min);
        return Stream.of(
                        List.of(maxY, x),
                        List.of(maxY, minX),
                        List.of(maxY, maxX),
                        List.of(y, minX),
                        List.of(y, maxX),
                        List.of(minY, minX),
                        List.of(minY, x),
                        List.of(minY, maxX))
                .distinct()
                .toList();
    }

    public static String returnNextChar(String currentString) {
        return switch (currentString) {
            case "X" -> "M";
            case "XM" -> "A";
            case "XMA" -> "S";
            default -> throw new IllegalArgumentException("Bad string");
        };
    }

    public static String returnDirection(List<Integer> currentPosition, List<Integer> nextCoord) {
        var yDirection = currentPosition.getFirst() - nextCoord.getFirst();
        var xDirection = currentPosition.getLast() - nextCoord.getLast();

        if (yDirection > 0) {
            if (xDirection > 0) {
                return "TR";
            } else if (xDirection == 0) {
                return "TM";
            } else if (xDirection < 0) {
                return "TL";
            }

        } else if (yDirection == 0) {
            if (xDirection > 0) {
                return "ML";
            } else if (xDirection == 0) {
                throw new RuntimeException("Same as original :(");
            } else if (xDirection < 0) {
                return "MR";
            }

        } else if (yDirection < 0) {
            if (xDirection > 0) {
                return "BL";
            } else if (xDirection == 0) {
                return "BM";
            } else if (xDirection < 0) {
                return "BR";
            }
        }
        throw new RuntimeException("Could not determine direction");
    }

    public static boolean isXMas(List<Integer> currentCoord, List<List<String>> input) {
        var diagonals = returnDiagonals(currentCoord, input.size());
        if (diagonals.size() != 4) {
            return false;
        }

        var values =
                diagonals.stream()
                        .map(diag -> input.get(diag.getFirst()).get(diag.getLast()))
                        .toList();
        if (Collections.frequency(values, "S") != 2 || Collections.frequency(values, "M") != 2) {
            return false;
        }

        var coordsOfMs =
                diagonals.stream()
                        .map(diag -> List.of(diag, input.get(diag.getFirst()).get(diag.getLast())))
                        .filter(v -> v.getLast().equals("M"))
                        .map(v -> (List<Integer>) v.getFirst())
                        .toList();
        if (abs(coordsOfMs.getFirst().getFirst() - coordsOfMs.getLast().getFirst()) == 2
                && abs(coordsOfMs.getFirst().getLast() - coordsOfMs.getLast().getLast()) == 2) {
            return false;
        }

        return true;
    }

    public static List<List<Integer>> returnDiagonals(List<Integer> index, int maxListLength) {
        var x = index.getLast();
        var y = index.getFirst();
        var min = 0;

        var maxX = Math.min(x + 1, maxListLength - 1);
        var maxY = Math.min(y + 1, maxListLength - 1);
        var minX = Math.max(x - 1, min);
        var minY = Math.max(y - 1, min);
        return Stream.of(
                        List.of(maxY, minX),
                        List.of(maxY, maxX),
                        List.of(minY, minX),
                        List.of(minY, maxX))
                .filter(
                        diag ->
                                !Objects.equals(diag.getFirst(), y)
                                        && !Objects.equals(diag.getLast(), x))
                .distinct()
                .toList();
    }
}
