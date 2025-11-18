package y2024.Day12;

import static java.lang.Math.abs;
import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Day12 {

    public static long part2(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var uniquePlots = getUniquePlots(input);
        System.out.println(uniquePlots);
        var groups =
                uniquePlots.stream()
                        .map(plot -> findGroups(input, plot))
                        .flatMap(List::stream)
                        .toList();
        var areas = groups.stream().map(List::size).toList();
        var corners = groups.stream().map(p -> cornerCounter(p, input)).toList();
        System.out.println(areas);
        System.out.println(corners);

        var index = 0;
        var sum = 0L;

        while (index < areas.size()) {
            sum += (long) areas.get(index) * corners.get(index);
            index++;
        }

        return sum;
    }

    public static long part1(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var uniquePlots = getUniquePlots(input);
        var groups =
                uniquePlots.stream()
                        .map(plot -> findGroups(input, plot))
                        .flatMap(List::stream)
                        .toList();
        var areas = groups.stream().map(List::size).toList();
        var edges =
                groups.stream()
                        .map(plot -> egdeMapper(plot, input.size()))
                        .map(
                                map ->
                                        map.entrySet().stream()
                                                .map(m -> m.getKey() * m.getValue())
                                                .reduce(0, Integer::sum))
                        .toList();

        var index = 0;
        var sum = 0L;

        while (index < areas.size()) {
            sum += (long) areas.get(index) * edges.get(index);
            index++;
        }

        return sum;
    }

    public static List<List<List<Integer>>> findGroups(List<List<String>> input, String plotType) {
        var groups = new ArrayList<ArrayList<List<Integer>>>();
        var stack = new Stack<List<Integer>>();
        var coords = getCoordsForPlotType(input, plotType);
        stack.addAll(coords);

        while (!stack.empty()) {
            var current = stack.pop();
            var currentValidNeighbours =
                    returnNonDiagonalNeighbours(current, input.size()).stream()
                            .filter(
                                    coord ->
                                            input.get(coord.getFirst())
                                                    .get(coord.getLast())
                                                    .equals(plotType))
                            .toList();

            var matchingGroup =
                    groups.stream()
                            .filter(
                                    group ->
                                            group.stream()
                                                    .anyMatch(currentValidNeighbours::contains))
                            .toList();

            if (matchingGroup.isEmpty()) {
                var currentAndNeighbours = new ArrayList<>(currentValidNeighbours);
                currentAndNeighbours.add(current);
                groups.add(new ArrayList<>(currentAndNeighbours));
                stack.addAll(currentValidNeighbours);
            } else {
                var groupWithCurrent = matchingGroup.getFirst();
                groupWithCurrent.add(current);

                var unindexedNeighbours =
                        currentValidNeighbours.stream()
                                .filter(n -> !groupWithCurrent.contains(n))
                                .toList();
                groupWithCurrent.addAll(unindexedNeighbours);
                stack.addAll(unindexedNeighbours);
            }
        }
        return groups.stream().map(g -> g.stream().distinct().toList()).toList();
    }

    public static List<List<String>> parseInput(String filepath) throws FileNotFoundException {
        var inputAsString = readFileToString(filepath);
        return Arrays.stream(inputAsString.split(("\n")))
                .map(line -> Arrays.stream(line.split("")).toList())
                .toList();
    }

    // index [y,x]
    // assume rows and cols are equal
    public static List<List<Integer>> returnNonDiagonalNeighbours(
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
                .filter(coord -> abs(coord.getFirst() - y) == 1 || abs(coord.getLast() - x) == 1)
                .toList();
    }

    public static List<List<Integer>> returnDiagonalNeighbours(
            List<Integer> index, int maxListLength) {
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
                .distinct()
                .toList();
    }

    public static HashMap<Integer, Integer> egdeMapper(
            List<List<Integer>> plotGroup, int maxInputSize) {
        var edgeMap = new HashMap<Integer, Integer>();
        var index = 0;

        while (index < plotGroup.size()) {
            var current = plotGroup.get(index);
            var neighboursMinusSelf = plotGroup.stream().filter(n -> !n.equals(current)).toList();
            var neighboursOfCurrent = returnNonDiagonalNeighbours(current, maxInputSize);
            var plotNeighbours =
                    neighboursOfCurrent.stream().filter(neighboursMinusSelf::contains).toList();

            if (plotNeighbours.isEmpty()) {
                if (edgeMap.containsKey(4)) {
                    edgeMap.put(4, edgeMap.get(4) + 1);
                } else edgeMap.put(4, 1);
            } else if (plotNeighbours.size() == 1) {
                if (edgeMap.containsKey(3)) {
                    edgeMap.put(3, edgeMap.get(3) + 1);
                } else edgeMap.put(3, 1);
            } else if (plotNeighbours.size() == 2) {
                if (edgeMap.containsKey(2)) {
                    edgeMap.put(2, edgeMap.get(2) + 1);
                } else edgeMap.put(2, 1);
            } else if (plotNeighbours.size() == 3) {
                if (edgeMap.containsKey(1)) {
                    edgeMap.put(1, edgeMap.get(1) + 1);
                } else edgeMap.put(1, 1);
            }
            index++;
        }
        return edgeMap;
    }

    public static long cornerCounter(List<List<Integer>> plotGroup, List<List<String>> input) {
        var index = 0;
        var corners = 0L;

        while (index < plotGroup.size()) {
            var current = plotGroup.get(index);
            var plotType = input.get(current.getFirst()).get(current.getLast());
            var neighboursMinusSelf = plotGroup.stream().filter(n -> !n.equals(current)).toList();
            var neighboursOfCurrent = returnNonDiagonalNeighbours(current, input.size());
            var plotNeighbours =
                    neighboursOfCurrent.stream().filter(neighboursMinusSelf::contains).toList();

            if (plotNeighbours.isEmpty()) {
                corners += 4;
            } else if (plotNeighbours.size() == 1) {
                corners += 2;
            } else if (plotNeighbours.size() == 2) {
                if (plotNeighbours.stream()
                        .map(
                                n ->
                                        List.of(
                                                abs(n.getFirst() - current.getFirst()),
                                                abs(n.getLast() - current.getLast())))
                        .toList()
                        .containsAll(List.of(List.of(1, 0), List.of(0, 1)))) {
                    corners++;
                    var n1 = plotNeighbours.getFirst();
                    var n2 = plotNeighbours.getLast();
                    var diag =
                            List.of(
                                    abs(current.getFirst() - n1.getFirst() - n2.getFirst()),
                                    abs((current.getLast() - n1.getLast() - n2.getLast())));
                    if (!getPlotAtCoord(diag, input).equals(plotType)) {
                        corners++;
                    }
                }
                ;
            } else if ((plotNeighbours.size() == 4)
                    && plotNeighbours.stream()
                            .map(c -> returnDiagonalNeighbours(c, input.size()))
                            .flatMap(List::stream)
                            .distinct()
                            .map(n -> getPlotAtCoord(n, input))
                            .anyMatch(plotType::equals)) {
                corners++;
            }
            index++;
        }
        return corners;
    }

    public static List<String> getUniquePlots(List<List<String>> input) {
        return input.stream().flatMap(List::stream).distinct().toList();
    }

    public static List<List<Integer>> getCoordsForPlotType(
            List<List<String>> input, String plotType) {
        var coords = new ArrayList<List<Integer>>();
        var y = 0;
        var x = 0;

        while (y < input.size()) {
            var line = input.get(y);
            while (x < line.size()) {
                if (line.get(x).equals(plotType)) {
                    coords.add(List.of(y, x));
                }
                x++;
            }
            x = 0;
            y++;
        }
        return coords;
    }

    public static String getPlotAtCoord(List<Integer> current, List<List<String>> input) {
        try {
            return input.get(current.getFirst()).get(current.getLast());
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }
}
