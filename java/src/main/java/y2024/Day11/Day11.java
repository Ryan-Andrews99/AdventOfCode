package y2024.Day11;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {

    public static Map<String, Map<Integer, Long>> cache = new HashMap<>();

    public static long returnStonesAfterBlinks(String filepath, int blinks)
            throws FileNotFoundException {
        var input = parseInput(filepath);
        return input.stream()
                .map(
                        item ->
                                blinkNumberOfTimes(
                                        new LinkedList<>(List.of(item)), blinks, Optional.of(0L)))
                .reduce(Long::sum)
                .get();
    }

    public static List<String> parseInput(String filePath) throws FileNotFoundException {
        var inputAsString = readFileToString(filePath).trim();
        return Arrays.stream(inputAsString.split(" ")).toList();
    }

    public static Long blinkNumberOfTimes(
            LinkedList<String> input, int blinksRemaining, Optional<Long> countMaybe) {
        var count = countMaybe.orElse(0L);
        if (blinksRemaining == 0) {
            count += input.size();
            return count;
        }

        var index = 0;
        while (index < input.size()) {
            var current = input.get(index);
            var currentInt = Long.parseLong(current);

            if (currentInt == 0) {
                input.set(index, "1");
                index++;
                continue;
            }

            if (current.length() % 2 == 0) {
                var left = Long.parseLong(current.substring(0, current.length() / 2));
                var right = Long.parseLong(current.substring(current.length() / 2));
                input.set(index, Long.toString(left));
                input.add(index + 1, Long.toString(right));
                index += 2;
                continue;
            }

            input.set(index, Long.toString(currentInt * 2024));
            index++;
        }
        Long tempCount = count;
        return input.stream()
                .map(
                        (i) -> {
                            if (cache.containsKey(i) && cache.get(i).containsKey(blinksRemaining)) {
                                return cache.get(i).get(blinksRemaining);
                            } else {
                                var result =
                                        blinkNumberOfTimes(
                                                new LinkedList<>(List.of(i)),
                                                blinksRemaining - 1,
                                                Optional.of(tempCount));
                                if (cache.containsKey(i)) {
                                    cache.get(i).put(blinksRemaining, result);
                                } else {
                                    cache.put(i, new HashMap<>(Map.of(blinksRemaining, result)));
                                }
                                return result;
                            }
                        })
                .reduce(Long::sum)
                .get();
    }
}
