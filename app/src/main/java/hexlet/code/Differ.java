package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.DiffConstructor.buildDifferenceTree;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        String defaultFormat = "stylish";
        String diffResultAsString = generate(filepath1, filepath2, defaultFormat);
        return diffResultAsString;
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path pathToFirstFile = Paths.get(filepath1).toAbsolutePath().normalize();
        Path pathToSecondFile = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, Object> firstMap = Parser.parseFileToMap(pathToFirstFile);
        Map<String, Object> secondMap = Parser.parseFileToMap(pathToSecondFile);
        SortedMap<String, Map<String, Object>> diffAsTreeOfMaps = buildDifferenceTree(firstMap, secondMap);
        String diffResultAsString = Formatter.buildFormattedResult(diffAsTreeOfMaps, format);
        return diffResultAsString;
    }
}
