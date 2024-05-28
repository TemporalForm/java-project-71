package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Stylish {
    public static String buildStylishResult(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                            SortedMap<String, String> statusMap) {
        StringBuilder resultDiff = new StringBuilder("{\n");
        statusMap.forEach((key, status) -> {
            String oldValue = String.valueOf(firstMap.get(key));
            String newValue = String.valueOf(secondMap.get(key));
            switch (status) {
                case "changed" ->
                        resultDiff.append("  - ").append(key).append(": ").append(oldValue).append("\n")
                                .append("  + ").append(key).append(": ").append(newValue).append("\n");
                case "added" ->
                        resultDiff.append("  + ").append(key).append(": ").append(newValue).append("\n");
                case "removed" ->
                        resultDiff.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                case "same" ->
                        resultDiff.append("    ").append(key).append(": ").append(newValue).append("\n");
                default -> throw new RuntimeException("Received unexpected status: " + status);
            }
        });
        resultDiff.append("}");
        return resultDiff.toString();
    }
}
