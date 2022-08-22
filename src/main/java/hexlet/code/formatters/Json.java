package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class Json {
    public static String getInJson(Map<String, Object> map1, Map<String, Object> map2) throws JsonProcessingException {
        TreeSet<String> allKeysSet = new TreeSet<>(map1.keySet());
        allKeysSet.addAll(map2.keySet());

        Map<String, Object> outMap = new LinkedHashMap<>();

        for (String keyEntry : allKeysSet) {
            if (!map1.containsKey(keyEntry)) {
                outMap.put("+ " + keyEntry, map2.get(keyEntry) != null ? map2.get(keyEntry) : "null");
            } else if (!map2.containsKey(keyEntry)) {
                outMap.put("- " + keyEntry, map1.get(keyEntry) != null ? map1.get(keyEntry) : "null");
            } else if (EqualsEntryMap.isEquals(map1.get(keyEntry),map2.get(keyEntry))) {
                outMap.put("  " + keyEntry, map1.get(keyEntry) != null ? map1.get(keyEntry) : "null");
            } else {
                outMap.put("- " + keyEntry, map1.get(keyEntry) != null ? map1.get(keyEntry) : "null");
                outMap.put("+ " + keyEntry, map2.get(keyEntry) != null ? map2.get(keyEntry) : "null");
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(outMap)
                .replaceAll("\"", "")
                .replaceAll(" :", ":")
                .replaceAll(",", "");
    }
}
