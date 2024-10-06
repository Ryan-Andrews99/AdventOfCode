package y2020.Day04;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day04 {

    public static long part2(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var validFields = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        var parsedPassports = parsePassports(input);
        return parsedPassports.stream()
                .map(passport -> passport.stream().collect(Collectors.toMap(p -> p[0], p -> p[1])))
                .filter(passportMap -> passportMap.keySet().containsAll(validFields))
                .filter(
                        passportMap ->
                                passportMap.entrySet().stream()
                                        .allMatch(
                                                entry ->
                                                        validationParser(
                                                                entry.getKey(), entry.getValue())))
                .toList()
                .size();
    }

    public static List<List<String[]>> parsePassports(ArrayList<String> input) {
        return input.stream()
                .map(passport -> Arrays.stream(passport.split(" ")).map(p -> p.split(":")).toList())
                .toList();
    }

    public static long part1(String filepath) throws FileNotFoundException {
        var input = parseInput(filepath);
        var validFields = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        var parsedPassports = parsePassports(input);
        return parsedPassports.stream()
                .map(pass -> pass.stream().map(p -> p[0]).toList())
                .filter(p -> new HashSet<>(p).containsAll(validFields))
                .toList()
                .size();
    }

    public static ArrayList<String> parseInput(String filePath) throws FileNotFoundException {
        return new ArrayList<>(
                Arrays.stream(readFileToString(filePath).split("\n\n"))
                        .map(passport -> new ArrayList<>(List.of(passport.split("\n"))))
                        .map(passport -> passport.stream().collect(Collectors.joining(" ")))
                        .toList());
    }

    public static boolean validateNumericalValue(int val, int min, int max) {
        return val >= min && val <= max;
    }

    public static boolean validateHairCol(String col) {
        return col.contains("#")
                && col.replace("#", "").length() == 6
                && Arrays.stream(col.replace("#", "").split(""))
                        .allMatch(
                                c ->
                                        List.of(
                                                        "0", "1", "2", "3", "4", "5", "6", "7", "8",
                                                        "9", "a", "b", "c", "d", "e", "f")
                                                .contains(c));
    }

    public static boolean validationParser(String key, String val) {
        switch (key) {
            case "byr":
                return validateNumericalValue(Integer.parseInt(val), 1920, 2002);
            case "iyr":
                return validateNumericalValue(Integer.parseInt(val), 2010, 2020);
            case "eyr":
                return validateNumericalValue(Integer.parseInt(val), 2020, 2030);
            case "hgt":
                if (val.contains("in")) {
                    return validateNumericalValue(Integer.parseInt(val.replace("in", "")), 59, 76);
                } else if (val.contains("cm")) {
                    return validateNumericalValue(
                            Integer.parseInt(val.replace("cm", "")), 150, 193);
                } else return false;
            case "hcl":
                return validateHairCol(val);
            case "ecl":
                return List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(val);
            case "pid":
                var arr = val.split("");
                if (arr.length != 9) return false;
                return Arrays.stream(arr)
                        .allMatch(
                                c -> {
                                    try {
                                        Integer.parseInt(c);
                                        return true;
                                    } catch (NumberFormatException | NullPointerException e) {
                                        return false;
                                    }
                                });
            default:
                return key.equals("cid");
        }
    }
}
