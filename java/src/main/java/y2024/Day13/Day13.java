package y2024.Day13;

import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Day13 {

    public static List<List<Integer>> parseInput(String filepath) throws FileNotFoundException {
        var inputAsString = readFileToString(filepath);
        var games = Arrays.stream(inputAsString.split("\n\n")).map(game -> game.split("\n"));
        return List.of();
    }


    public static int calculateDeterminant(List<Integer> m1, List<Integer> m2){
        return m1.getFirst()* m2.getLast() - m1.getLast() * m2.getFirst();
    }
}
