package hexlet.code;


import hexlet.code.formatters.Json;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import java.util.SortedMap;
import java.util.Map;

public class Formatter {

    public static String buildFormattedResult(SortedMap<String, Map<String, Object>> differenceTree, String format)
            throws Exception {
        switch (format) {
            case "plain" -> {
                return Plain.buildPlainResult(differenceTree);
            }
            case "json" -> {
                return Json.buildJsonResult(differenceTree);
            }
            case "stylish" -> {
                return Stylish.buildStylishResult(differenceTree);
            }
            default -> throw new RuntimeException("Received unexpected format: " + format);
        }
    }

}
