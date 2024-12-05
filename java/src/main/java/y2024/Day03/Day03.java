package y2024.Day03;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Day03 {

    public static Integer part2(String filePath) throws FileNotFoundException {
        var regexMatcher =
                Pattern.compile("(don't\\(\\))|(do\\(\\))|(mul\\(\\d+,\\d+\\))")
                        .matcher(String.join("", readFileToString(filePath).split("\n")));

        var enabled = true;
        var matches = new ArrayList<String>();

        while (regexMatcher.find()) {
            if (regexMatcher.group().contains(("don"))) {
                enabled = false;
            } else if (regexMatcher.group().contains("do(")) {
                enabled = true;
            } else if (enabled) {
                matches.add(regexMatcher.group());
            }
        }

        return matches.stream()
                .map(
                        match ->
                                Arrays.stream(
                                                match.replace("mul", "")
                                                        .replace("(", "")
                                                        .replace(")", "")
                                                        .split(","))
                                        .map(Integer::parseInt)
                                        .reduce(1, (acc, num) -> acc * num))
                .reduce(Integer::sum)
                .get();
    }

    public static Integer part1(String filePath) throws FileNotFoundException {
        var matcher =
                Pattern.compile("mul\\(\\d+,\\d+\\)")
                        .matcher(String.join("", readFileToString(filePath).split("\n")));
        var matches = new ArrayList<String>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches.stream()
                .map(
                        match ->
                                Arrays.stream(
                                                match.replace("mul", "")
                                                        .replace("(", "")
                                                        .replace(")", "")
                                                        .split(","))
                                        .map(Integer::parseInt)
                                        .reduce(1, (acc, num) -> acc * num))
                .reduce(Integer::sum)
                .get();
    }
}
