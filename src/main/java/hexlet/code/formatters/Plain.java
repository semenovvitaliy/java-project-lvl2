package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeSet;

public class Plain {
    public static String getInPlain(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> allKeysSet = new TreeSet<>(map1.keySet());
        allKeysSet.addAll(map2.keySet());

        StringBuilder resultString = new StringBuilder();

        for (String keyEntry : allKeysSet) {
            if (!map1.containsKey(keyEntry)) {
                resultString.append("Property '")
                        .append(keyEntry)
                        .append("' was added with value: ")
                        .append(map2.get(keyEntry) != null ? map2.get(keyEntry) : "null")
                        .append("\n");
            } else if (!map2.containsKey(keyEntry)) {
                resultString.append("Property '")
                        .append(keyEntry)
                        .append("' was removed")
                        .append("\n");
            } else if (equalsEntryMap(map1.get(keyEntry), map2.get(keyEntry))) {
                    resultString.append("Property '")
                            .append(keyEntry)
                            .append("' was not changed. Value: ")
                            .append(map2.get(keyEntry) != null ? map2.get(keyEntry) : "null")
                            .append("\n");
                } else resultString.append("Property '")
                        .append(keyEntry)
                        .append("' was updated. From ")
                        .append(map1.get(keyEntry) != null ? map1.get(keyEntry) : "null")
                        .append(" to ")
                        .append(map2.get(keyEntry) != null ? map2.get(keyEntry) : "null")
                        .append("\n");
            }
        return resultString.toString();
    }
    private static boolean equalsEntryMap(Object entry1, Object entry2) {
        if ((entry1 != null) && (entry2 != null)) {
            return entry1.equals(entry2);
        } else return (entry1 == null) && (entry2 == null);

    }
}
