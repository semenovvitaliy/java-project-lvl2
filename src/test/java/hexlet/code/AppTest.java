package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void generateJsonToJsonTest() throws Exception {
        String resultPathByString = "src/test/resources/result12_json_json.txt";
        Path resultPath = Paths.get(resultPathByString).toAbsolutePath().normalize();
        String extendedString = readString(resultPath);

        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";

        String result = Differ.generate(path1, path2, "json");

        assertEquals(extendedString, result);
    }

    @Test
    void generateYmlToPlainTest() throws Exception {
        String resultPathByString = "src/test/resources/result12_yml_plain.txt";
        Path resultPath = Paths.get(resultPathByString).toAbsolutePath().normalize();
        String extendedString = readString(resultPath);

        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";

        String result = Differ.generate(path1, path2, "plain");

        assertEquals(extendedString, result);
    }

    @Test
    void generateJsonToStylishTest() throws Exception {
        String resultPathByString = "src/test/resources/result12_json_stylish.txt";
        Path resultPath = Paths.get(resultPathByString).toAbsolutePath().normalize();
        String extendedString = readString(resultPath);
        if (extendedString.endsWith("\n")) {
            extendedString = extendedString.substring(0, extendedString.length() - 1);
        }

        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";

        String result = Differ.generate(path1, path2, "stylish");

        assertEquals(extendedString, result);
    }

    @Test
    void generateJsonToRecursionStylishDefaultTest() throws Exception {
        String resultPathByString = "src/test/resources/result34_json_stylish.txt";
        Path resultPath = Paths.get(resultPathByString).toAbsolutePath().normalize();
        String extendedString = readString(resultPath);
        if (extendedString.endsWith("\n")) {
            extendedString = extendedString.substring(0, extendedString.length() - 1);
        }

        String path1 = "src/test/resources/file3.json";
        String path2 = "src/test/resources/file4.json";

        String result = Differ.generate(path1, path2);

        assertEquals(extendedString, result);
    }
}
