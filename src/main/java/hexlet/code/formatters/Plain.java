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
                        .append(getValue(map2, keyEntry))
                        .append("\n");
            } else if (!map2.containsKey(keyEntry)) {
                resultString.append("Property '")
                        .append(keyEntry)
                        .append("' was removed")
                        .append("\n");
            } else if (EqualsEntryMap.isEquals(map1.get(keyEntry), map2.get(keyEntry))) {
                resultString.append("Property '")
                        .append(keyEntry)
                        .append("' was not changed. Value: ")
                        .append(getValue(map2, keyEntry))
                        .append("\n");
            } else  {
                resultString.append("Property '")
                        .append(keyEntry)
                        .append("' was updated. From ")
                        .append(getValue(map1, keyEntry))
                        .append(" to ")
                        .append(getValue(map2, keyEntry))
                        .append("\n");
            }
        }
        return resultString.toString();
    }

    private static Object getValue(Map<String, Object> map, String key) {
        Object tempValue = map.get(key);
        if (tempValue == null) {
            return "null";
        }
        if ((tempValue instanceof String)
                || (tempValue instanceof Integer)
                || (tempValue instanceof Boolean)) {
            return tempValue;
        }
        return "[complex value]";
    }
}
