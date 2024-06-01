package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Stylish {
    public static String buildStylishResult(SortedMap<String, Map<String, Object>> differenceTree) {
        StringBuilder resultDiff = new StringBuilder("{\n");
        differenceTree.forEach((key, statusMap) -> {
            String status = String.valueOf(statusMap.get("status"));
            String oldValue = String.valueOf(statusMap.get("oldValue"));
            String newValue = String.valueOf(statusMap.get("newValue"));
            switch (status) {
                case "changed" -> resultDiff.append("  - ").append(key).append(": ").append(oldValue).append("\n")
                        .append("  + ").append(key).append(": ").append(newValue).append("\n");
                case "added" -> resultDiff.append("  + ").append(key).append(": ").append(newValue).append("\n");
                case "removed" -> resultDiff.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                case "same" -> resultDiff.append("    ").append(key).append(": ").append(oldValue).append("\n");
                default -> throw new RuntimeException("Received unexpected status: " + status);
            }
        });
        resultDiff.append("}");
        return resultDiff.toString();
    }
}
