package y2020.Day02;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.*;

public class Day02 {

    private Day02() {}

    public static int part2(String filePath) throws FileNotFoundException {
        var mapOfInput = parseInput(filePath);
        int correctPasswords = 0;

        for (var entry : mapOfInput) {

            var rule = entry.get(0).split(" ");
            var range = Arrays.stream(rule[0].trim().split("-")).map(Integer::parseInt).toList();
            var letter = rule[1].trim();
            var password = entry.get(1).trim();
            var firstPosition = password.charAt(range.get(0) - 1);
            var lastPosition = password.charAt(range.get(1) - 1);
            var doBothMatch = firstPosition == letter.charAt(0) && lastPosition == letter.charAt(0);

            if ((firstPosition == letter.charAt(0) || lastPosition == letter.charAt(0))
                    && !doBothMatch) {
                correctPasswords++;
            }
        }
        return correctPasswords;
    }

    public static int part1(String filePath) throws FileNotFoundException {
        var mapOfInput = parseInput(filePath);
        int correctPasswords = 0;

        for (var entry : mapOfInput) {

            var rule = entry.get(0).split(" ");
            var range = Arrays.stream(rule[0].trim().split("-")).map(Integer::parseInt).toList();
            var letter = rule[1].trim();
            var password = entry.get(1).trim();
            var filteredPassword =
                    Arrays.stream(password.split(""))
                            .filter(c -> Objects.equals(c, letter))
                            .toList();

            if (filteredPassword.size() >= range.getFirst()
                    && filteredPassword.size() <= range.getLast()) {
                correctPasswords++;
            }
        }
        return correctPasswords;
    }

    public static ArrayList<List<String>> parseInput(String filePath) throws FileNotFoundException {
        var fileAsString = readFileToString(filePath);
        var values = new ArrayList<List<String>>();

        Arrays.stream(fileAsString.split("\n"))
                .filter(line -> !(line.isBlank() || line.isEmpty()))
                .forEach(
                        line -> {
                            var splitLine = Arrays.stream(line.split((":"))).toList();
                            values.add(List.of(splitLine.get(0).trim(), splitLine.get(1).trim()));
                        });

        return values;
    }
}
