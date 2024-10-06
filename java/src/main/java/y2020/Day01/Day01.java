package y2020.Day01;

import static java.lang.Math.abs;
import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.*;

public class Day01 {

    public static int part01(String filePath) throws FileNotFoundException {
        int target = 2020;
        int answer = 0;
        var remainders = new ArrayList<Integer>();
        var intArray = parseInput(filePath);
        var i = 0;
        while (i < intArray.size()) {
            var current = intArray.get(i);
            var remainder = abs(target - current);
            if (remainders.contains(remainder)) {
                answer = remainder * current;
                break;
            } else {
                remainders.add(current);
                i++;
            }
        }

        return answer;
    }

    public static int part02(String filePath) throws FileNotFoundException {
        int target = 2020;
        var answer = new ArrayList<Integer>();
        var intArray = parseInput(filePath);
        var i = 0;
        while (i < intArray.size()) {
            var j = i + 1;
            while (j < intArray.size()) {
                var k = j + 1;
                while (k < intArray.size()) {
                    if (intArray.get(i) + intArray.get(j) + intArray.get(k) == target) {
                        answer.add(intArray.get(i));
                        answer.add(intArray.get(j));
                        answer.add(intArray.get(k));
                        break;
                    }
                    k++;
                }
                if (answer.size() == 3) break;
                j++;
            }
            if (answer.size() == 3) break;
            i++;
        }
        return answer.stream().reduce(1, (total, el) -> total * el);
    }

    private static List<Integer> parseInput(String filePath) throws FileNotFoundException {
        return Arrays.stream(readFileToString(filePath).split("\n"))
                .filter(line -> !(line.isEmpty() || line.isBlank()))
                .map(Integer::parseInt)
                .toList();
    }
}
