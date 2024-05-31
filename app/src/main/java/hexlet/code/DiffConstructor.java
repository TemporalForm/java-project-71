package hexlet.code;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class DiffConstructor {
    public static SortedMap<String, Map<String, Object>> buildDifferenceTree(Map<String, Object> firstMap,
                                                                       Map<String, Object> secondMap) {
        SortedMap<String, Map<String, Object>> differenceTree = new TreeMap<>();
        Set<String> allKeys = new HashSet<>(firstMap.keySet());
        allKeys.addAll(secondMap.keySet());
        for (String key : allKeys) {
            Map<String, Object> statusMap = new LinkedHashMap<>();
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (!Objects.equals(firstMap.get(key), secondMap.get(key))) {
                    statusMap.put("status", "changed");
                    statusMap.put("oldValue", firstMap.get(key));
                    statusMap.put("newValue", secondMap.get(key));
                } else {
                    statusMap.put("status", "same");
                    statusMap.put("oldValue", firstMap.get(key));
                }
            } else if (firstMap.containsKey(key)) {
                statusMap.put("status", "removed");
                statusMap.put("oldValue", firstMap.get(key));
            } else {
                statusMap.put("status", "added");
                statusMap.put("newValue", secondMap.get(key));
            }
            differenceTree.put(key, statusMap);
        }

        return differenceTree;
    }
}
