package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {
    public static String formatter(DiffBuilder diffBuilder, String format) throws Exception {
        switch (format) {
            case "json" -> {
                return Json.getInJson(diffBuilder);
            }
            case "plain" -> {
                return Plain.getInPlain(diffBuilder);
            }
            default -> {
                return Stylish.getInStylish(diffBuilder);
            }
        }
    }
}
