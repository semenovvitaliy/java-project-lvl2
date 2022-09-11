package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parseString(String s, String extension) throws Exception {
        switch (extension) {
            case "json" -> {
                return parseJsonString(s);
            }
            case "yml", "yaml" -> {
                return parseYmlString(s);
            }
            default -> throw new Exception("Wrong file format");
        }
    }

    private static Map<String, Object> parseJsonString(String s) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(s, new TypeReference<>() { });
    }

    private static Map<String, Object> parseYmlString(String s) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(s, new TypeReference<>() { });
    }
}
