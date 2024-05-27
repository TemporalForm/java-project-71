package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class Differ {

    public static final String[] FIELD_STATUS = {"changed", "added", "removed", "same"};


    public static SortedMap<String, String> calculateDiffAsMap(Map<String, Object> firstMap,
                                                               Map<String, Object> secondMap) {
        SortedMap<String, String> statusMap = new TreeMap<>();
        firstMap.forEach((key, value) -> {
            if (secondMap.containsKey(key) && Objects.equals(value, secondMap.get(key))) {
                statusMap.put(key, FIELD_STATUS[3]);
            } else if (secondMap.containsKey(key) && !Objects.equals(value, secondMap.get(key))) {
                statusMap.put(key, FIELD_STATUS[0]);
            } else {
                statusMap.put(key, FIELD_STATUS[2]);
            }
        });

        secondMap.forEach((key, value) -> {
            if (!firstMap.containsKey(key)) {
                statusMap.put(key, FIELD_STATUS[1]);
            }
        });
        return statusMap;
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> firstMap = Parser.parseFileToMap(filepath1);
        Map<String, Object> secondMap = Parser.parseFileToMap(filepath2);
        SortedMap<String, String> statusMap = calculateDiffAsMap(firstMap, secondMap);
        String diffResultAsString = Formatter.stylish(firstMap, secondMap, statusMap, format);
        return diffResultAsString;
    }
}
