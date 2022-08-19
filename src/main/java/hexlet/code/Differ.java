package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        String s1 = readFile(filePath1);
        Map<String, String> map1 = new HashMap<>(unserialize(s1));
        String s2 = readFile(filePath2);
        Map<String, String> map2 = new HashMap<>(unserialize(s2));

        TreeSet<String> allKeysSet = new TreeSet<>(map1.keySet());
        allKeysSet.addAll(map2.keySet());

        Map<String, String> outMap = new LinkedHashMap<>();

        for (String keyEntry : allKeysSet) {
            if (!map1.containsKey(keyEntry)) {
                outMap.put("+ " + keyEntry, map2.get(keyEntry));
            } else if (!map2.containsKey(keyEntry)) {
                outMap.put("- " + keyEntry, map1.get(keyEntry));
            } else if (map1.get(keyEntry).equals(map2.get(keyEntry))) {
                outMap.put("  " + keyEntry, map1.get(keyEntry));
            } else {
                outMap.put("- " + keyEntry, map1.get(keyEntry));
                outMap.put("+ " + keyEntry, map2.get(keyEntry));
            }
        }
        return serialize(outMap);
    }

    public static String serialize(Map<String, String> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(map)
                .replaceAll("\"", "")
                .replaceAll(" :", ":")
                .replaceAll(",", "");
    }

    public static Map<String, String> unserialize(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<>() { });
    }

    public static String readFile(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath);
    }
}
