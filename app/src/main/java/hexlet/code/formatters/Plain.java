package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Plain {

    public static String buildPlainResult(SortedMap<String, Map<String, Object>> differenceTree) {
        StringBuilder resultDiff = new StringBuilder();
        differenceTree.forEach((key, statusMap) -> {
            String status = String.valueOf(statusMap.get("status"));
            Object oldValue = statusMap.get("oldValue");
            Object newValue = statusMap.get("newValue");
            switch (status) {
                case "changed" -> updateValueByKey(resultDiff, key, oldValue, newValue);
                case "added" -> addValueByKey(resultDiff, key, newValue);
                case "removed" -> resultDiff.append("Property '").append(key).append("' was removed\n");
                case "same" -> {
                    break;
                }
                default -> throw new RuntimeException("Received unexpected status: " + status);
            }
        });
        return resultDiff.toString().trim();
    }

    private static void updateValueByKey(StringBuilder resultDiff, String key, Object oldValue, Object newValue) {
        resultDiff.append("Property '").append(key).append("' was updated. From ");
        appendValueByType(resultDiff, oldValue);
        resultDiff.append(" to ");
        appendValueByType(resultDiff, newValue);
        resultDiff.append("\n");
    }

    private static void addValueByKey(StringBuilder resultDiff, String key, Object newValue) {
        resultDiff.append("Property '").append(key).append("' was added with value: ");
        appendValueByType(resultDiff, newValue);
        resultDiff.append("\n");
    }

    private static void appendValueByType(StringBuilder resultDiff, Object value) {
        String valueAsString = String.valueOf(value);
        if (value instanceof String) {
            resultDiff.append("'").append(value).append("'");
        } else if (valueAsString.startsWith("[") || valueAsString.startsWith("{")) {
            resultDiff.append("[complex value]");
        } else {
            resultDiff.append(value);
        }
    }
}
