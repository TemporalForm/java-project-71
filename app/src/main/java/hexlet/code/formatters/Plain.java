package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.Differ.FIELD_STATUS;

public class Plain {
    public static String buildPlainResult(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                          SortedMap<String, String> statusMap) {
        StringBuilder resultDiff = new StringBuilder();
        statusMap.forEach((key, status) -> {
            String currentValue = String.valueOf(firstMap.get(key));
            String newValue = String.valueOf(secondMap.get(key));
            if (status.equals(FIELD_STATUS[0])) {
                resultDiff.append("Property '").append(key).append("' was updated. From ");
                if (firstMap.get(key) instanceof String) {
                    resultDiff.append("'").append(currentValue).append("' to ");
                } else if (currentValue.startsWith("[") || currentValue.startsWith("{")) {
                    resultDiff.append("[complex value] to ");
                } else {
                    resultDiff.append(currentValue).append(" to ");
                }
                if (secondMap.get(key) instanceof String) {
                    resultDiff.append("'").append(newValue).append("'").append("\n");
                } else if (newValue.startsWith("[") || newValue.startsWith("{")) {
                    resultDiff.append("[complex value]").append("\n");
                } else {
                    resultDiff.append(newValue).append("\n");
                }
            }
            if (status.equals(FIELD_STATUS[1])) {
                resultDiff.append("Property '").append(key).append("' was added with value: ");
                if (secondMap.get(key) instanceof String) {
                    resultDiff.append("'").append(newValue).append("'").append("\n");
                } else if (newValue.startsWith("[") || newValue.startsWith("{")) {
                    resultDiff.append("[complex value]").append("\n");
                } else {
                    resultDiff.append(newValue).append("\n");
                }
            }
            if (status.equals(FIELD_STATUS[2])) {
                resultDiff.append("Property '").append(key).append("' was removed").append("\n");
            }
        });
        return resultDiff.toString().trim();
    }
}
