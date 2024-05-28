package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Stylish {
    public static String buildStylishResult(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                            SortedMap<String, String> statusMap) {
        StringBuilder resultDiff = new StringBuilder("{\n");
        statusMap.forEach((key, status) -> {
            switch (status) {
                case "changed" ->
                        resultDiff.append("  - ").append(key).append(": ").append(firstMap.get(key)).append("\n")
                                .append("  + ").append(key).append(": ").append(secondMap.get(key)).append("\n");
                case "added" ->
                        resultDiff.append("  + ").append(key).append(": ").append(secondMap.get(key)).append("\n");
                case "removed" ->
                        resultDiff.append("  - ").append(key).append(": ").append(firstMap.get(key)).append("\n");
                case "same" ->
                        resultDiff.append("    ").append(key).append(": ").append(firstMap.get(key)).append("\n");
                default -> throw new RuntimeException("Received unexpected status: " + status);
            }
        });
        resultDiff.append("}");
        return resultDiff.toString();
    }
}
