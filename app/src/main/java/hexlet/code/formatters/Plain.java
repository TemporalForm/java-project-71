package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Plain {

    public static String buildPlainResult(SortedMap<String, Map<String, Object>> differenceTree) {
        StringBuilder resultDiff = new StringBuilder();
        differenceTree.forEach((key, diffMap) -> {
            String diffType = diffMap.get("diffType") == null ? "null" : diffMap.get("diffType").toString();
            Object firstValue = diffMap.get("value1");
            Object secondValue = diffMap.get("value2");
            switch (diffType) {
                case "changed" -> updateValueByKey(resultDiff, key, firstValue, secondValue);
                case "added" -> addValueByKey(resultDiff, key, secondValue);
                case "removed" -> resultDiff.append("Property '").append(key).append("' was removed\n");
                case "same" -> {
                    break;
                }
                default -> throw new RuntimeException("Received unexpected status: " + diffType);
            }
        });
        return resultDiff.toString().trim();
    }

    private static void updateValueByKey(StringBuilder resultDiff, String key, Object firstValue, Object secondValue) {
        resultDiff.append("Property '").append(key).append("' was updated. From ");
        stringify(resultDiff, firstValue);
        resultDiff.append(" to ");
        stringify(resultDiff, secondValue);
        resultDiff.append("\n");
    }

    private static void addValueByKey(StringBuilder resultDiff, String key, Object secondValue) {
        resultDiff.append("Property '").append(key).append("' was added with value: ");
        stringify(resultDiff, secondValue);
        resultDiff.append("\n");
    }

    private static void stringify(StringBuilder resultDiff, Object value) {
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
