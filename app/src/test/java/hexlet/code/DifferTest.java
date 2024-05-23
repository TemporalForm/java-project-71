package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static String expected;

    static {
        try {
            expected = Files.readString(Paths.get("src/test/resources/testresult1.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMainFunctionality() throws Exception {
        assertEquals(expected,
                Differ.generate("src/test/resources/testfilepath1.json", "src/test/resources/testfilepath2.json"));

    }
}
