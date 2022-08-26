package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void generateJsonTest() {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        String result;

        try {
            result = Differ.generate(path1, path2, "json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String actual = "{\"- follow\":false,"
                + "\"  host\":\"hexlet.io\","
                + "\"- proxy\":\"123.234.53.22\","
                + "\"- timeout\":50,\""
                + "+ timeout\":20,\""
                + "+ verbose\":true}";
        assertEquals(actual, result);
    }
    @Test
    void generateYmlTest() {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";
        String result;

        try {
            result = Differ.generate(path1, path2, "plain");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String actual = """
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: value2
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'setting1' was updated. From Some value to Another value
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to none
                """;
        assertEquals(actual, result);
    }
}