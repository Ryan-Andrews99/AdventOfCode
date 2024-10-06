package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileReader {

    public static String readFileToString(String filePath) throws FileNotFoundException {
        StringBuilder fileOutput = new StringBuilder();
        String fullPath = Paths.get("").toAbsolutePath() + filePath;
        try {
            var file = new File(fullPath);
            var scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                fileOutput.append(scanner.nextLine()).append("\n");
            }

            scanner.close();
            return fileOutput.toString();
        } catch (FileNotFoundException e) {
            System.out.printf("Error: File not found at path: %s", filePath);
            throw e;
        }
    }
}
