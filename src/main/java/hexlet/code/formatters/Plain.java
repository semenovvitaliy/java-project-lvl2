package hexlet.code.formatters;

import hexlet.code.ComparedEntry;

import java.util.Map;

public class Plain {
    public static String format(Map<String, ComparedEntry> diffBuilder) {
        StringBuilder resultString = new StringBuilder();

        boolean firstLine = true;
        int size = diffBuilder.size();
        for (Map.Entry<String, ComparedEntry> each : diffBuilder.entrySet()) {
            switch (each.getValue().getAction()) {
                case ADDED -> resultString
                        .append(firstLine ? "" : "\n")
                        .append("Property '")
                        .append(each.getKey())
                        .append("' was added with value: ")
                        .append(toPlainValue(each.getValue().getValue()));
                case REMOVED -> resultString
                        .append(firstLine ? "" : "\n")
                        .append("Property '")
                        .append(each.getKey())
                        .append("' was removed");
                case CHANGED -> resultString
                        .append(firstLine ? "" : "\n")
                        .append("Property '")
                        .append(each.getKey())
                        .append("' was updated. From ")
                        .append(toPlainValue(each.getValue().getLastValue()))
                        .append(" to ")
                        .append(toPlainValue(each.getValue().getValue()));
                case NOTCHANGED -> {
                    continue;
                }
                default -> throw new IllegalStateException("Unexpected action: " + each.getValue().getAction());
            }
            firstLine = false;
        }
        return resultString.toString();
    }

    private static Object toPlainValue(Object value) {
        if (value == null) {
            return "null";
        }
        if ((value instanceof Integer) || (value instanceof Boolean)) {
            return value;
        }
        if (value instanceof String) {
            return "'%s'".formatted(value);
        }
        return "[complex value]";
    }
}
