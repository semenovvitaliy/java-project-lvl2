package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.nio.file.Files.readString;

public class Parser {
    public static Map<String, Object> getParserFile(String filePath) throws IOException {
        Path fullPath = Paths.get(filePath).toAbsolutePath().normalize();
        String s = readString(fullPath);
        if (filePath.endsWith(".json")) {
            return getJsonParse(s);
        } else if (filePath.endsWith(".yml")) {
            return getYmlParse(s);
        }
        return null;
    }

    private static Map<String, Object> getJsonParse(String s) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(s, new TypeReference<>() { });
    }

    private static Map<String, Object> getYmlParse(String s) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(s, new TypeReference<>() { });
    }
}
