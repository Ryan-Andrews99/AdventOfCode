package y2024.Day08;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day08 {

    public static long part2(String filePath) throws FileNotFoundException {
        var input = parseInput(filePath);
        var uniqueAntena = returnAntenna(input);
        return uniqueAntena.stream()
                .map(ant -> getCoordsForAntenna(input, ant))
                .map(coords -> getAntiNodesForCoords(coords, input.size()))
                .flatMap(List::stream)
                .filter(
                        an ->
                                an.getFirst() < input.size()
                                        && an.getFirst() >= 0
                                        && an.getLast() < input.size()
                                        && an.getLast() >= 0)
                .distinct()
                .count();
    }

    public static long part1(String filePath) throws FileNotFoundException {
        var input = parseInput(filePath);
        var uniqueAntena = returnAntenna(input);
        return uniqueAntena.stream()
                .map(ant -> getCoordsForAntenna(input, ant))
                .map(Day08::getAntiNodesForCoords)
                .flatMap(List::stream)
                .filter(
                        an ->
                                an.getFirst() < input.size()
                                        && an.getFirst() >= 0
                                        && an.getLast() < input.size()
                                        && an.getLast() >= 0)
                .distinct()
                .count();
    }

    public static List<List<String>> parseInput(String filepath) throws FileNotFoundException {
        var fileAsString = readFileToString(filepath);
        return Arrays.stream(fileAsString.split("\n"))
                .map(line -> Arrays.stream(line.split("")).toList())
                .toList();
    }

    public static List<String> returnAntenna(List<List<String>> input) {
        return input.stream().flatMap(List::stream).filter(c -> !".".equals(c)).distinct().toList();
    }

    public static List<List<Integer>> getCoordsForAntenna(
            List<List<String>> input, String antenna) {
        var coords = new ArrayList<List<Integer>>();
        var y = 0;
        var x = 0;

        while (y < input.size()) {
            var line = input.get(y);
            while (x < line.size()) {
                if (line.get(x).equals(antenna)) {
                    coords.add(List.of(y, x));
                }
                x++;
            }
            x = 0;
            y++;
        }
        return coords;
    }

    public static List<List<Integer>> getAntiNodesForCoords(List<List<Integer>> antennaCoords) {
        var antiNodes = new ArrayList<List<Integer>>();
        var index = 0;

        while (index < antennaCoords.size()) {
            var current = antennaCoords.get(index);
            var copyOfList = new ArrayList<>(antennaCoords);
            copyOfList.remove(index);
            copyOfList.stream()
                    .map(c -> returnAntinodes(current, c))
                    .flatMap(List::stream)
                    .forEach(antiNodes::add);
            index++;
        }
        return antiNodes;
    }

    public static List<List<Integer>> getAntiNodesForCoords(
            List<List<Integer>> antennaCoords, int maxSize) {
        var antiNodes = new ArrayList<List<Integer>>();
        var index = 0;

        while (index < antennaCoords.size()) {
            var current = antennaCoords.get(index);
            var copyOfList = new ArrayList<>(antennaCoords);
            copyOfList.remove(index);
            copyOfList.stream()
                    .map(c -> returnAntinodes(current, c, maxSize))
                    .flatMap(List::stream)
                    .forEach(antiNodes::add);
            index++;
        }
        return antiNodes;
    }

    public static List<List<Integer>> returnAntinodes(List<Integer> coord1, List<Integer> coord2) {
        var yMagnitude = coord1.getFirst() - coord2.getFirst();
        var xMagnitude = coord1.getLast() - coord2.getLast();
        return Stream.of(
                        List.of(
                                coord2.getFirst() + 2 * yMagnitude,
                                coord2.getLast() + 2 * xMagnitude),
                        List.of(
                                coord1.getFirst() - 2 * yMagnitude,
                                coord1.getLast() - 2 * xMagnitude))
                .toList();
    }

    public static List<List<Integer>> returnAntinodes(
            List<Integer> coord1, List<Integer> coord2, int maxGridSize) {
        var antiNodes = new ArrayList<List<Integer>>();
        var yMagnitude = coord1.getFirst() - coord2.getFirst();
        var xMagnitude = coord1.getLast() - coord2.getLast();
        var repeats =
                yMagnitude > xMagnitude
                        ? Math.round(maxGridSize / yMagnitude)
                        : Math.round(maxGridSize / xMagnitude);
        while (repeats >= 1) {
            antiNodes.add(
                    List.of(
                            coord2.getFirst() + repeats * yMagnitude,
                            coord2.getLast() + repeats * xMagnitude));
            antiNodes.add(
                    List.of(
                            coord1.getFirst() - repeats * yMagnitude,
                            coord1.getLast() - repeats * xMagnitude));
            repeats--;
        }
        return antiNodes;
    }
}
