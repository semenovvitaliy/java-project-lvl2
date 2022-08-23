package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class Stylish {
    public static String getInStylish(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        TreeSet<String> allKeysSet = new TreeSet<>(map1.keySet());
        allKeysSet.addAll(map2.keySet());

        Map<String, Object> outMap = new LinkedHashMap<>();

        for (String keyEntry : allKeysSet) {
            if (!map1.containsKey(keyEntry)) {
                outMap.put("+ " + keyEntry, map2.get(keyEntry) != null ? map2.get(keyEntry).toString() : "null");
            } else if (!map2.containsKey(keyEntry)) {
                outMap.put("- " + keyEntry, map1.get(keyEntry) != null ? map1.get(keyEntry).toString() : "null");
            } else if (EqualsEntryMap.isEquals(map1.get(keyEntry), map2.get(keyEntry))) {
                outMap.put("  " + keyEntry, map1.get(keyEntry) != null ? map1.get(keyEntry).toString() : "null");
            } else {
                outMap.put("- " + keyEntry, map1.get(keyEntry) != null ? map1.get(keyEntry).toString() : "null");
                outMap.put("+ " + keyEntry, map2.get(keyEntry) != null ? map2.get(keyEntry).toString() : "null");
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(outMap)
                .replaceAll("\"", "")
                .replaceAll(" :", ":")
                .replaceAll(",\n", "\n");
    }
}
