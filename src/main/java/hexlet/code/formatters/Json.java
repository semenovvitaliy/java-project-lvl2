package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ComparedEntry;

import java.util.LinkedHashMap;
import java.util.Map;

public class Json {
    public static String format(Map<String, ComparedEntry> diffBuilder) throws Exception {
        Map<String, Object> outMap = new LinkedHashMap<>();

        for (Map.Entry<String, ComparedEntry> each : diffBuilder.entrySet()) {
            switch (each.getValue().getAction()) {
                case ADDED -> outMap.put("+ " + each.getKey(), each.getValue().getValue());
                case REMOVED -> outMap.put("- " + each.getKey(), each.getValue().getLastValue());
                case NOTCHANGED -> outMap.put("  " + each.getKey(), each.getValue().getValue());
                case CHANGED -> {
                    outMap.put("- " + each.getKey(), each.getValue().getLastValue());
                    outMap.put("+ " + each.getKey(), each.getValue().getValue());
                }
                default -> throw new IllegalStateException("Unexpected action: " + each.getValue().getAction());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        //mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(outMap) + "\n";
    }
}
