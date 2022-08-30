package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void generateJsonToJsonTest() {
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
    void generateYmlToPlainTest() {
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
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
        assertEquals(actual, result);
    }

    @Test
    void generateJsonToStylishTest() {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        String result;

        try {
            result = Differ.generate(path1, path2, "stylish");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String actual = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertEquals(actual, result);
    }

    @Test
    void generateJsonToRecursionStylishTest() {
        String path1 = "src/test/resources/file3.json";
        String path2 = "src/test/resources/file4.json";
        String result;

        try {
            result = Differ.generate(path1, path2, "stylish");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String actual = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        assertEquals(actual, result);
    }
}
