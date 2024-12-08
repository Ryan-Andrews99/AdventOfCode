package y2024.Day05;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.*;

public class Day05 {
    private static List<String> ruleSet;

    public static long part2(String filePath) throws FileNotFoundException {
        var input = parseInput(filePath);
        List<List<Integer>> updates = input.getLast();
        ruleSet = input.getFirst();
        var updatesWhichNeedReordering =
                updates.stream()
                        .filter(
                                update -> {
                                    var originalUpdate = new ArrayList<>(update);
                                    var sortedUpdate = new ArrayList<>(update);
                                    sortedUpdate.sort(updateComparator);
                                    return !originalUpdate.equals(sortedUpdate);
                                })
                        .map(update -> update.stream().sorted(updateComparator).toList())
                        .toList();
        return updatesWhichNeedReordering.stream()
                .map(list -> list.get((int) Math.floor(list.size() / 2)))
                .reduce(0, (acc, num) -> acc += num);
    }

    public static long part1(String filePath) throws FileNotFoundException {
        var input = parseInput(filePath);
        List<List<Integer>> updates = input.getLast();
        ruleSet = input.getFirst();
        var validUpdates =
                updates.stream()
                        .filter(
                                update -> {
                                    var originalUpdate = new ArrayList<>(update);
                                    var sortedUpdate = new ArrayList<>(update);
                                    sortedUpdate.sort(updateComparator);
                                    return originalUpdate.equals(sortedUpdate);
                                })
                        .toList();
        return validUpdates.stream()
                .map(list -> list.get((int) Math.floor(list.size() / 2)))
                .reduce(0, (acc, num) -> acc += num);
    }

    public static List<List> parseInput(String filePath) throws FileNotFoundException {
        var fileAsString = readFileToString(filePath);
        var splitFile = fileAsString.split("\n\n");
        var rules = splitFile[0];
        var updates = splitFile[1];
        var parsedRules = Arrays.stream(rules.split("\n")).map(String::trim).toList();
        var parsedUpdates =
                Arrays.stream(updates.split("\n"))
                        .map(
                                lines ->
                                        Arrays.stream(lines.split(","))
                                                .map(Integer::parseInt)
                                                .toList())
                        .toList();
        return List.of(parsedRules, parsedUpdates);
    }

    static Comparator<Integer> updateComparator =
            new Comparator<>() {
                @Override
                public int compare(Integer current, Integer next) {
                    var currentBeforeNext = ruleSet.contains(String.format("%s|%s", current, next));
                    var nextBeforeCurrent = ruleSet.contains(String.format("%s|%s", next, current));

                    if (currentBeforeNext) {
                        return -1;
                    }
                    if (nextBeforeCurrent) {
                        return 1;
                    }
                    return 0;
                }
            };
}
