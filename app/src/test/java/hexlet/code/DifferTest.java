package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final String expected;

    static {
        try {
            expected = Files.readString(Paths.get("src/test/resources/TestNestedFilesResult.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testNestedFunctionalityJson() throws Exception {
        String actual = Differ.generate("src/test/resources/TestNestedFile1.json",
                "src/test/resources/TestNestedFile2.json", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testNestedFunctionalityYaml() throws Exception {
        String actual = Differ.generate("src/test/resources/TestNestedFile1.yml",
                "src/test/resources/TestNestedFile2.yml", "stylish");
        assertEquals(expected, actual);
    }

}
