package hexlet.code;


import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import java.util.SortedMap;
import java.util.Map;

public class Formatter {

    public static String stylish(Map<String, Object> firstMap, Map<String, Object> secondMap,
                                 SortedMap<String, String> statusMap, String format) {
        String formattedDiff = null;
        switch (format) {
            case "plain":
                formattedDiff = Plain.buildPlainResult(firstMap, secondMap, statusMap);
                break;
            case "stylish":
            default:
                formattedDiff = Stylish.buildStylishResult(firstMap, secondMap, statusMap);
                break;
        }
        return formattedDiff;
    }

}
