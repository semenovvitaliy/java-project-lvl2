package hexlet.code.formatters;

import hexlet.code.ComparedEntry;

import java.util.Map;

public class Stylish {
    public static String format(Map<String, ComparedEntry> diffBuilder) {
        StringBuilder str = new StringBuilder("{");

        int size = diffBuilder.size();
        if (size > 0) {
            str.append("\n");
        }

        for (Map.Entry<String, ComparedEntry> each : diffBuilder.entrySet()) {
            switch (each.getValue().getAction()) {
                case ADDED -> str.append("  + ")
                    .append(each.getKey())
                    .append(": ")
                    .append(each.getValue().getValue())
                    .append("\n");
                case REMOVED -> str.append("  - ")
                    .append(each.getKey())
                    .append(": ")
                    .append(each.getValue().getLastValue())
                    .append("\n");
                case NOTCHANGED -> str.append("    ")
                        .append(each.getKey())
                        .append(": ")
                        .append(each.getValue().getValue())
                        .append("\n");
                case CHANGED -> {
                    str.append("  - ")
                            .append(each.getKey())
                            .append(": ")
                            .append(each.getValue().getLastValue())
                            .append("\n");
                    str.append("  + ")
                            .append(each.getKey())
                            .append(": ")
                            .append(each.getValue().getValue())
                            .append("\n");
                }
                default -> throw new IllegalStateException("Unexpected action: " + each.getValue().getAction());
            }
        }
        str.append("}");
        return str.toString();
    }
}
