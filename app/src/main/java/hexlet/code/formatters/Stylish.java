package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.Differ.FIELD_STATUS;

public class Stylish {
    public static String buildStylishResult(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                 SortedMap<String, String> statusMap) {
        StringBuilder resultDiff = new StringBuilder("{\n");
        statusMap.forEach((key, value) -> {
            if (value.equals(FIELD_STATUS[3])) {
                resultDiff.append("    ").append(key).append(": ").append(firstMap.get(key)).append("\n");
            } else if (value.equals(FIELD_STATUS[0])) {
                resultDiff.append("  - ").append(key).append(": ").append(firstMap.get(key)).append("\n");
                resultDiff.append("  + ").append(key).append(": ").append(secondMap.get(key)).append("\n");
            } else if (value.equals(FIELD_STATUS[1])) {
                resultDiff.append("  + ").append(key).append(": ").append(secondMap.get(key)).append("\n");
            } else {
                resultDiff.append("  - ").append(key).append(": ").append(firstMap.get(key)).append("\n");
            }
        });
        resultDiff.append("}");
        return resultDiff.toString();
    }
}
