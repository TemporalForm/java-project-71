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
                    statusMap.put("diffType", "changed");
                    statusMap.put("value1", firstMap.get(key));
                    statusMap.put("value2", secondMap.get(key));
                } else {
                    statusMap.put("diffType", "same");
                    statusMap.put("value1", firstMap.get(key));
                }
            } else if (firstMap.containsKey(key)) {
                statusMap.put("diffType", "removed");
                statusMap.put("value1", firstMap.get(key));
            } else {
                statusMap.put("diffType", "added");
                statusMap.put("value2", secondMap.get(key));
            }
            differenceTree.put(key, statusMap);
        }

        return differenceTree;
    }
}
