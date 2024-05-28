package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class Json {
    public static String buildJsonResult(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                         SortedMap<String, String> statusMap) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Map<String, Object>> diffAsListOfMaps = new LinkedList<>();
        statusMap.forEach((key, status) -> {
            String oldValue = String.valueOf(firstMap.get(key));
            String newValue = String.valueOf(secondMap.get(key));
            Map<String, Object> dataSegment = new LinkedHashMap<>();
            switch (status) {
                case "changed" -> {
                    dataSegment.put("status", "updated");
                    dataSegment.put("key", key);
                    dataSegment.put("oldValue", oldValue);
                    dataSegment.put("newValue", newValue);
                }
                case "added" -> {
                    dataSegment.put("status", "added");
                    dataSegment.put("key", key);
                    dataSegment.put("newValue", newValue);
                }
                case "removed" -> {
                    dataSegment.put("status", "removed");
                    dataSegment.put("key", key);
                    dataSegment.put("oldValue", oldValue);
                }
                case "same" -> {
                    dataSegment.put("status", "same");
                    dataSegment.put("key", key);
                    dataSegment.put("oldValue", oldValue);
                }
                default -> throw new RuntimeException("Received unexpected status: " + status);
            }
            diffAsListOfMaps.add(dataSegment);
        });
        objectMapper.writeValue(new File("Difference.json"), diffAsListOfMaps);
        String resultDiff = objectMapper.writeValueAsString(diffAsListOfMaps);
        return resultDiff;
    }
}
