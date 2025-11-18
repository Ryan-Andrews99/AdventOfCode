package y2024.Day17;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day17 {

    public static Map<String, Integer> parseRegisters(String file) {
        var registers =
                Arrays.stream(file.split("\n\n"))
                        .filter(line -> line.contains("Register"))
                        .map(line -> line.trim().replaceAll("(\\w+ [A-C]:)+", ""));
        var map = new HashMap<String, Integer>();

        //        registers.forEach(register -> );

        return map;
    }
}
