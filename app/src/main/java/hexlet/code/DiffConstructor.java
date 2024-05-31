package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class DiffConstructor {
    public static SortedMap<String, String> calculateDiffAsMap(Map<String, Object> firstMap,
                                                               Map<String, Object> secondMap) {
        SortedMap<String, String> statusMap = new TreeMap<>();
        firstMap.forEach((key, value) -> {
            if (secondMap.containsKey(key) && !Objects.equals(value, secondMap.get(key))) {
                statusMap.put(key, "changed");
            } else if (!secondMap.containsKey(key)) {
                statusMap.put(key, "removed");
            } else {
                statusMap.put(key, "same");
            }
        });
        secondMap.forEach((key, value) -> {
            if (!firstMap.containsKey(key)) {
                statusMap.put(key, "added");
            }
        });
        return statusMap;
    }
}
