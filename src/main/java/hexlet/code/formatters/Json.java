package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ComparedEntry;
import hexlet.code.DiffBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

public class Json {
    public static String getInJson(DiffBuilder diffBuilder) throws Exception {
        Map<String, Object> outMap = new LinkedHashMap<>();

        for (ComparedEntry each : diffBuilder.getList()) {
            switch (each.getAction()) {
                case ADDED -> outMap.put("+ " + each.getKey(), each.getValue());
                case REMOVED -> outMap.put("- " + each.getKey(), each.getLastValue());
                case NOTCHANGED -> outMap.put("  " + each.getKey(), each.getValue());
                case CHANGED -> {
                    outMap.put("- " + each.getKey(), each.getLastValue());
                    outMap.put("+ " + each.getKey(), each.getValue());
                }
                default -> throw new IllegalStateException("Unexpected action: " + each.getAction());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        //mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(outMap) + "\n";
    }
}
