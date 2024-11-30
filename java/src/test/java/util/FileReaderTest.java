package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static util.FileReader.readFileToString;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

public class FileReaderTest {

    @Test
    void shouldReadAFileFromAPath() throws FileNotFoundException {
        assertEquals("1\n2\n3\n4\n5\n6\n", readFileToString("/java/src/test/java/util/file.txt"));
    }

    @Test
    void throwsWhenFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> readFileToString("not-exsistent-file.txt"));
    }
}
