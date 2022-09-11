package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class DiffBuilder {

    public static Map<String, ComparedEntry> getDiff(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> allKeysSet = new TreeSet<>(map1.keySet());
        allKeysSet.addAll(map2.keySet());

        Map<String, ComparedEntry> outMap = new LinkedHashMap<>();

        for (String keyEntry : allKeysSet) {
            if (!map1.containsKey(keyEntry)) {
                outMap.put(keyEntry, new ComparedEntry(null, map2.get(keyEntry), KeyAction.ADDED));
            } else if (!map2.containsKey(keyEntry)) {
                outMap.put(keyEntry, new ComparedEntry(map1.get(keyEntry), null, KeyAction.REMOVED));
            } else if (isEquals(map1.get(keyEntry), map2.get(keyEntry))) {
                outMap.put(keyEntry, new ComparedEntry(map1.get(keyEntry), map1.get(keyEntry), KeyAction.NOTCHANGED));
            } else {
                outMap.put(keyEntry, new ComparedEntry(map1.get(keyEntry), map2.get(keyEntry), KeyAction.CHANGED));
            }
        }
        return outMap;
    }

    private static boolean isEquals(Object entry1, Object entry2) {
        if ((entry1 != null) && (entry2 != null)) {
            return entry1.equals(entry2);
        } else {
            return (entry1 == null) && (entry2 == null);
        }
    }
}
