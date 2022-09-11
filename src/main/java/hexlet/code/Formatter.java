package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String formatter(Map<String, ComparedEntry> diffBuilder, String format) throws Exception {
        switch (format) {
            case "json" -> {
                return Json.format(diffBuilder);
            }
            case "plain" -> {
                return Plain.format(diffBuilder);
            }
            case "stylish" -> {
                return Stylish.format(diffBuilder);
            }
            default -> throw new Exception("Wrong format");
        }
    }
}
