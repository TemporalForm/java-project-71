package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Plain {

    public static String buildPlainResult(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                          SortedMap<String, String> statusMap) {
        StringBuilder resultDiff = new StringBuilder();
        statusMap.forEach((key, status) -> {
            String oldValue = String.valueOf(firstMap.get(key));
            String newValue = String.valueOf(secondMap.get(key));
            switch (status) {
                case "changed" -> updateValueByKey(resultDiff, key, oldValue, newValue, firstMap, secondMap);
                case "added" -> addValueByKey(resultDiff, key, newValue, secondMap);
                case "removed" -> resultDiff.append("Property '").append(key).append("' was removed\n");
                case "same" -> {
                    break;
                }
                default -> throw new RuntimeException("Received unexpected status: " + status);
            }
        });
        return resultDiff.toString().trim();
    }

    private static void updateValueByKey(StringBuilder resultDiff, String key, String currentValue, String newValue,
                                         Map<String, Object> firstMap, Map<String, Object> secondMap) {
        resultDiff.append("Property '").append(key).append("' was updated. From ");
        appendValueByType(resultDiff, currentValue, firstMap.get(key));
        resultDiff.append(" to ");
        appendValueByType(resultDiff, newValue, secondMap.get(key));
        resultDiff.append("\n");
    }

    private static void addValueByKey(StringBuilder resultDiff, String key, String newValue,
                                      Map<String, Object> secondMap) {
        resultDiff.append("Property '").append(key).append("' was added with value: ");
        appendValueByType(resultDiff, newValue, secondMap.get(key));
        resultDiff.append("\n");
    }

    private static void appendValueByType(StringBuilder resultDiff, String value, Object originalValue) {
        if (originalValue instanceof String) {
            resultDiff.append("'").append(value).append("'");
        } else if (value.startsWith("[") || value.startsWith("{")) {
            resultDiff.append("[complex value]");
        } else {
            resultDiff.append(value);
        }
    }
}
