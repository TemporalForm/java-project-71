package hexlet.code;


import java.util.SortedMap;
import java.util.Map;

import static hexlet.code.Differ.FIELD_STATUS;

public class Formatter {

    public static String stylish(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                 SortedMap<String, String> statusMap, String format) {
        StringBuilder resultDiff = new StringBuilder("{\n");
        if (format.equals("stylish")) {
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
        } else {
            return "You need to use 'stylish' format by default";
        }
    }

}
