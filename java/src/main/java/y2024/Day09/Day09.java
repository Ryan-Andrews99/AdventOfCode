package y2024.Day09;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day09 {

    public static long part2(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var mapStringList = Arrays.stream(returnMapString(input).split(" ")).toList();
        var compactList = newCompactor(new ArrayList<>(mapStringList));

        var sum = 0L;
        var index = 0;

        while (index < compactList.size()) {

            if (compactList.get(index).equals(".")) {
                index++;
                continue;
            }

            sum += ((long) index * Integer.parseInt(compactList.get(index)));
            index++;
        }
        return sum;
    }

    public static long part1(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var mapStringList = Arrays.stream(returnMapString(input).split(" ")).toList();
        var compactList = compactList(new ArrayList<>(mapStringList));
        var justNums =
                compactList.stream()
                        .filter(item -> !item.equals("."))
                        .map(Integer::parseInt)
                        .toList();

        var sum = 0L;
        var index = 0;

        while (index < justNums.size()) {
            sum += ((long) index * justNums.get(index));
            index++;
        }
        return sum;
    }

    public static String returnMapString(List<Integer> input) {
        var mapString = new StringBuilder();
        var index = 0;
        var id = 0;
        while (index < input.size()) {
            var charToAppend = (index % 2 == 0) ? id : ".";
            var numberOfTimesToAppend = input.get(index);
            while (numberOfTimesToAppend > 0) {
                mapString.append(charToAppend + " ");
                numberOfTimesToAppend--;
            }
            if (index % 2 == 0) {
                id++;
            }
            index++;
        }
        return mapString.toString();
    }

    public static List<Integer> parseInput(String filepath) throws FileNotFoundException {
        var fileAsString = readFileToString(filepath).trim();
        return Arrays.stream(fileAsString.split("")).map(Integer::parseInt).toList();
    }

    public static List<String> compactList(ArrayList<String> listToSort) {
        var index = listToSort.size() - 1;

        while (index >= 0) {
            if (List.copyOf(listToSort).subList(0, index).stream().noneMatch(i -> i.equals("."))) {
                return List.copyOf(listToSort);
            }

            if (listToSort.get(index).equals(".")) {
                index--;
                continue;
            }

            var firstEmptyIndex = listToSort.indexOf(".");
            var temp = listToSort.get(index);
            listToSort.set(firstEmptyIndex, temp);
            listToSort.set(index, ".");
            index--;
        }

        return listToSort;
    }

    public static List<String> newCompactor(ArrayList<String> listToSort) {
        var index = listToSort.size() - 1;

        while (index >= 0) {
            var current = listToSort.get(index);
            var sizeOfFile = index - lookBehindMatcher(listToSort, current, index);
            if (current.equals(".")) {
                index -= sizeOfFile;
                continue;
            }

            var firstFittingIndex = findIndexOfSpaceMatchingSize(listToSort, sizeOfFile, index);

            if (firstFittingIndex == -1) {
                index -= sizeOfFile;
                continue;
            }
            var numsSwapped = 0;
            while (numsSwapped != sizeOfFile) {
                var temp = listToSort.get(index - numsSwapped);
                listToSort.set(firstFittingIndex + numsSwapped, temp);
                listToSort.set(index - numsSwapped, ".");
                numsSwapped++;
            }
            index -= sizeOfFile;
        }

        return listToSort;
    }

    public static int lookBehindMatcher(List<String> list, String charToMatch, int index) {
        if (index < 0 || !list.get(index).equals(charToMatch)) {
            return index;
        } else return lookBehindMatcher(list, charToMatch, index - 1);
    }

    public static int lookForwardMatcher(List<String> list, String charToMatch, int index) {
        if (index > list.size() - 1 || !list.get(index).equals(charToMatch)) {
            return index;
        } else return lookForwardMatcher(list, charToMatch, index + 1);
    }

    public static int findIndexOfSpaceMatchingSize(
            List<String> input, int fileSize, int currentIndex) {
        var index = 0;

        while (index < input.size()) {
            var current = input.get(index);
            var sizeOfCurrent = lookForwardMatcher(input, current, index) - index;

            if (!current.equals(".")) {
                index += sizeOfCurrent;
                continue;
            }

            if ((sizeOfCurrent >= fileSize) && index < currentIndex) {
                return index;
            }
            index += sizeOfCurrent;
        }
        return -1;
    }
}
