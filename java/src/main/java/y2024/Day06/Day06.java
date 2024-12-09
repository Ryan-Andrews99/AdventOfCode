package y2024.Day06;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {

    public static long part2(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var startPosition = findStartPosition(input);
        var currentCoords = List.copyOf(startPosition);
        var direction = "^";
        var indexed = new ArrayList<List<Integer>>();
        indexed.add(currentCoords);

        while (true) {
            var nextCoord = returnNextCoord(currentCoords, direction);

            if (!(nextCoord.getFirst() < input.size() && nextCoord.getLast() < input.size())) {
                indexed.add(currentCoords);
                break;
            }

            if (input.get(nextCoord.getFirst()).get(nextCoord.getLast()).equals("#")) {
                direction = returnNinteyDegrees(direction);
                continue;
            }

            if (!indexed.contains(currentCoords)) {
                indexed.add(currentCoords);
            }

            currentCoords = nextCoord;
        }

        return indexed.parallelStream()
                .filter(n -> isALoopingChange(input, n, startPosition))
                .count();
    }

    public static long part1(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var currentCoords = findStartPosition(input);
        var direction = "^";
        var indexed = new ArrayList<List<Integer>>();
        indexed.add(currentCoords);

        while (true) {
            var nextCoord = returnNextCoord(currentCoords, direction);

            if (!(nextCoord.getFirst() < input.size())) {
                indexed.add(nextCoord);
                break;
            }

            if (input.get(nextCoord.getFirst()).get(nextCoord.getLast()).equals("#")) {
                direction = returnNinteyDegrees(direction);
                continue;
            }

            if (!indexed.contains(currentCoords)) {
                indexed.add(currentCoords);
            }

            currentCoords = nextCoord;
        }
        return indexed.size();
    }

    public static List<List<String>> parseInput(String filepath) throws FileNotFoundException {
        var fileAsString = readFileToString(filepath);
        return Arrays.stream(fileAsString.split(("\n")))
                .map(line -> Arrays.stream(line.split("")).toList())
                .toList();
    }

    public static String returnNinteyDegrees(String currentDirection) {
        return switch (currentDirection) {
            case "^" -> ">";
            case ">" -> "v";
            case "v" -> "<";
            case "<" -> "^";
            default -> throw new IllegalStateException("Unexpected value: " + currentDirection);
        };
    }

    // yx
    public static List<Integer> returnNextCoord(
            List<Integer> currentCoord, String currentDirection) {
        return switch (currentDirection) {
            case "^" -> List.of(currentCoord.getFirst() - 1, currentCoord.getLast());
            case ">" -> List.of(currentCoord.getFirst(), currentCoord.getLast() + 1);
            case "v" -> List.of(currentCoord.getFirst() + 1, currentCoord.getLast());
            case "<" -> List.of(currentCoord.getFirst(), currentCoord.getLast() - 1);
            default -> throw new IllegalStateException("Unexpected value: " + currentDirection);
        };
    }

    // yx
    public static List<Integer> findStartPosition(List<List<String>> input) {
        var y = 0;
        var x = 0;

        while (y < input.size()) {
            var line = input.get(y);
            while (x < line.size()) {
                if (line.get(x).equals("^")) {
                    return List.of(y, x);
                }
                x++;
            }
            x = 0;
            y++;
        }
        throw new RuntimeException("Could not find starting postion with char ^");
    }

    public static boolean isALoopingChange(
            List<List<String>> input, List<Integer> coordToChange, List<Integer> currentCoords) {
        var modifiableInput = new ArrayList<>(input.stream().map(ArrayList::new).toList());
        modifiableInput.get(coordToChange.getFirst()).set(coordToChange.getLast(), "O");
        var direction = "^";
        var indexed = new ArrayList<List>();
        indexed.add(List.of(currentCoords, direction));

        while (true) {
            var nextCoord = returnNextCoord(currentCoords, direction);

            if (!nextCoord.stream().allMatch(coord -> coord >= 0 && coord < input.size())) {
                return false;
            }

            if (indexed.contains(List.of(nextCoord, direction))) {
                return true;
            }

            var next = modifiableInput.get(nextCoord.getFirst()).get(nextCoord.getLast());

            if (next.equals("#") || next.equals("O")) {
                indexed.add(List.of(currentCoords, direction));
                direction = returnNinteyDegrees(direction);
                continue;
            }

            if (!indexed.contains(List.of(currentCoords, direction))) {
                indexed.add(List.of(currentCoords, direction));
            }
            currentCoords = nextCoord;
        }
    }
}
