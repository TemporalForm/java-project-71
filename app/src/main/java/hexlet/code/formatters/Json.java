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
            Map<String, Object> dataSegment = new LinkedHashMap<>();
            dataSegment.put("status", status);
            dataSegment.put("key", key);
            switch (status) {
                case "changed" -> {
                    dataSegment.put("oldValue", firstMap.get(key));
                    dataSegment.put("newValue", secondMap.get(key));
                }
                case "added" -> {
                    dataSegment.put("newValue", secondMap.get(key));
                }
                case "removed", "same" -> {
                    dataSegment.put("oldValue", firstMap.get(key));
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
