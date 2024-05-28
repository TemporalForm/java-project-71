package hexlet.code;


import hexlet.code.formatters.Json;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import java.util.SortedMap;
import java.util.Map;

public class Formatter {

    public static String stylish(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                 SortedMap<String, String> statusMap, String format) throws Exception {
        switch (format) {
            case "plain" -> {
                return Plain.buildPlainResult(firstMap, secondMap, statusMap);
            }
            case "json" -> {
                return Json.buildJsonResult(firstMap, secondMap, statusMap);
            }
            case "stylish" -> {
                return Stylish.buildStylishResult(firstMap, secondMap, statusMap);
            }
            default -> throw new RuntimeException("Received unexpected format: " + format);
        }
    }

}
