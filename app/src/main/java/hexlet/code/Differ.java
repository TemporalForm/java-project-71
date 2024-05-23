package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {

    public static String[] FIELD_STATUS = {"changed", "added", "removed", "same"};

    public static Map<String, Object> parseJsonToMap(String filepath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Path pathToJson = Paths.get(filepath).toAbsolutePath();
        Map<String, Object> jsonToMap = objectMapper.readValue(pathToJson.toFile(), new TypeReference<Map<String, Object>>() {
        });
        return jsonToMap;
    }

    public static SortedMap<String, String> diffToMap(Map<String, Object> firstMap, Map<String, Object> secondMap) throws Exception {
        SortedMap<String, String> statusMap = new TreeMap<>();
        firstMap.forEach((key, value) -> {
            if (secondMap.containsKey(key) && Objects.equals(value, secondMap.get(key))) {
                statusMap.put(key, FIELD_STATUS[3]);
            } else if (secondMap.containsKey(key) && !Objects.equals(value, secondMap.get(key))) {
                statusMap.put(key, FIELD_STATUS[0]);
            } else {
                statusMap.put(key, FIELD_STATUS[2]);
            }
        });

        secondMap.forEach((key, value) -> {
            if (!firstMap.containsKey(key)) {
                statusMap.put(key, FIELD_STATUS[1]);
            }
        });
        return statusMap;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> firstMap = parseJsonToMap(filepath1);
        Map<String, Object> secondMap = parseJsonToMap(filepath2);
        SortedMap<String, String> statusMap = diffToMap(firstMap, secondMap);
        StringBuilder resultDiff = new StringBuilder("{\n");
        statusMap.forEach((key, value) -> {
            if (value.equals(FIELD_STATUS[3])) {
                resultDiff.append("    ").append(key).append(": ").append(firstMap.get(key)).append("\n");
            } else if (value.equals(FIELD_STATUS[0])) {
                resultDiff.append("  - ").append(key).append(": ").append(firstMap.get(key)).append("\n");
                resultDiff.append("  + ").append(key).append(": ").append(secondMap.get(key)).append("\n");
            } else if (value.equals(FIELD_STATUS[1])) {
                resultDiff.append("  + ").append(key).append(": ").append(secondMap.get(key)).append("\n");
            } else {
                resultDiff.append("  - ").append(key).append(": ").append(firstMap.get(key)).append("\n");
            }
        });
        resultDiff.append("}");
        return resultDiff.toString();
    }
}
