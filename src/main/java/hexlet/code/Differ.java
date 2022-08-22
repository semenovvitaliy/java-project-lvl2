package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;

import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatType) throws Exception {
        Map<String, Object> map1 = Parser.getParserFile(filePath1);
        Map<String, Object> map2 = Parser.getParserFile(filePath2);

        switch (formatType) {
            case "json" -> {
                return Json.getInJson(map1, map2);
            }
            case "plain" -> {
                return Plain.getInPlain(map1, map2);
            }
            default -> {
                System.out.println("Wrong format. Used default format: stylish");
                return Plain.getInPlain(map1, map2);
            }
        }
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
