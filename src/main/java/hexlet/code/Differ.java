package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Formatter.formatter;
import static java.nio.file.Files.readString;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatType) throws Exception {
        Path fullPath1 = Paths.get(filePath1).toAbsolutePath().normalize();
        String s1 = readString(fullPath1);

        Map<String, Object> map1;
        if (filePath1.endsWith(".json")) {
            map1 = Parser.parseJsonString(s1);
        } else if (filePath1.endsWith(".yml")) {
            map1 = Parser.parseYmlString(s1);
        } else {
            throw new Exception("Wrong file1 format");
        }

        Path fullPath2 = Paths.get(filePath2).toAbsolutePath().normalize();
        String s2 = readString(fullPath2);

        Map<String, Object> map2;
        if (filePath2.endsWith(".json")) {
            map2 = Parser.parseJsonString(s2);
        } else if (filePath2.endsWith(".yml")) {
            map2 = Parser.parseYmlString(s2);
        } else {
            throw new Exception("Wrong file2 format");
        }

        DiffBuilder builtDiff = new DiffBuilder(map1, map2);

        return formatter(builtDiff, formatType);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
