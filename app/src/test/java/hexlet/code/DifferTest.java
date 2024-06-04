package hexlet.code;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private static String pathToFirstJsonFixture;
    private static String pathToSecondJsonFixture;
    private static String pathToFirstYamlFixture;
    private static String pathToSecondYamlFixture;

    @BeforeAll
    public static void setupTestResultFiles() {
        expectedStylish = readFileAsString("src/test/resources/fixtures/TestStylishFilesResult.txt");
        expectedPlain = readFileAsString("src/test/resources/fixtures/TestPlainFilesResult.txt");
        expectedJson = readFileAsString("src/test/resources/fixtures/TestJsonFilesResult.txt");
        pathToFirstJsonFixture = "src/test/resources/fixtures/TestFile1.json";
        pathToSecondJsonFixture = "src/test/resources/fixtures/TestFile2.json";
        pathToFirstYamlFixture = "src/test/resources/fixtures/TestFile1.yml";
        pathToSecondYamlFixture = "src/test/resources/fixtures/TestFile2.yml";

    }

    private static String readFileAsString(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDefaultFunctionalityJson() throws Exception {
        String actual = Differ.generate(pathToFirstJsonFixture,
                pathToSecondJsonFixture);
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testDefaultFunctionalityYaml() throws Exception {
        String actual = Differ.generate(pathToFirstYamlFixture,
                pathToSecondYamlFixture);
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testStylishFunctionalityJson() throws Exception {
        String actual = Differ.generate(pathToFirstJsonFixture,
                pathToSecondJsonFixture, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testStylishFunctionalityYaml() throws Exception {
        String actual = Differ.generate(pathToFirstYamlFixture,
                pathToSecondYamlFixture, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testPlainFunctionalityJson() throws Exception {
        String actual = Differ.generate(pathToFirstJsonFixture,
                pathToSecondJsonFixture, "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testPlainFunctionalityYaml() throws Exception {
        String actual = Differ.generate(pathToFirstYamlFixture,
                pathToSecondYamlFixture, "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testJsonFunctionalityJson() throws Exception {
        String actual = Differ.generate(pathToFirstJsonFixture,
                pathToSecondJsonFixture, "json");
        assertEquals(expectedJson, actual);
    }

    @Test
    public void testJsonFunctionalityYaml() throws Exception {
        String actual = Differ.generate(pathToFirstYamlFixture,
                pathToSecondYamlFixture, "json");
        assertEquals(expectedJson, actual);
    }
}
