package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    static {
        try {
            expectedStylish = Files.readString(Paths.get("src/test/resources/TestStylishFilesResult.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            expectedPlain = Files.readString(Paths.get("src/test/resources/TestPlainFilesResult.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            expectedJson = Files.readString(Paths.get("src/test/resources/TestJsonFilesResult.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testStylishFunctionalityJson() throws Exception {
        String actual = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json", "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testStylishFunctionalityYaml() throws Exception {
        String actual = Differ.generate("src/test/resources/TestFile1.yml",
                "src/test/resources/TestFile2.yml", "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testPlainFunctionalityJson() throws Exception {
        String actual = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json", "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testPlainFunctionalityYaml() throws Exception {
        String actual = Differ.generate("src/test/resources/TestFile1.yml",
                "src/test/resources/TestFile2.yml", "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testJsonFunctionalityJson() throws Exception {
        String actual = Differ.generate("src/test/resources/TestFile1.yml",
                "src/test/resources/TestFile2.yml", "json");
        assertEquals(expectedJson, actual);
    }

    @Test
    public void testJsonFunctionalityYaml() throws Exception {
        String actual = Differ.generate("src/test/resources/TestFile1.yml",
                "src/test/resources/TestFile2.yml", "json");
        assertEquals(expectedJson, actual);
    }
}
