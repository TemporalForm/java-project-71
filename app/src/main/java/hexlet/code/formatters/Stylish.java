package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Stylish {
    public static String buildStylishResult(SortedMap<String, Map<String, Object>> differenceTree) {
        StringBuilder resultDiff = new StringBuilder("{\n");
        differenceTree.forEach((key, diffMap) -> {
            String diffType = stringify(diffMap.get("diffType"));
            String firstValue = stringify(diffMap.get("value1"));
            String secondValue = stringify(diffMap.get("value2"));
            switch (diffType) {
                case "changed" -> resultDiff.append("  - ").append(key).append(": ").append(firstValue).append("\n")
                        .append("  + ").append(key).append(": ").append(secondValue).append("\n");
                case "added" -> resultDiff.append("  + ").append(key).append(": ").append(secondValue).append("\n");
                case "removed" -> resultDiff.append("  - ").append(key).append(": ").append(firstValue).append("\n");
                case "same" -> resultDiff.append("    ").append(key).append(": ").append(firstValue).append("\n");
                default -> throw new RuntimeException("Received unexpected status: " + diffType);
            }
        });
        resultDiff.append("}");
        return resultDiff.toString();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        return value.toString();
    }
}
