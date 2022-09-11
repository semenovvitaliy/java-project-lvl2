package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Formatter.formatter;
import static java.nio.file.Files.readString;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatType) throws Exception {

        Map<String, Object> map1 = readFileToMap(filePath1);
        Map<String, Object> map2 = readFileToMap(filePath2);

        Map<String, ComparedEntry> builtDiff = DiffBuilder.getDiff(map1, map2);

        return formatter(builtDiff, formatType);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static Map<String, Object> readFileToMap(String filePath) throws Exception {
        Path fullPath = Paths.get(filePath).toAbsolutePath().normalize();
        String str = readString(fullPath);

        String extensionOfFile = filePath.substring(filePath.lastIndexOf(".") + 1);
        return Parser.parseString(str, extensionOfFile);
    }
}
