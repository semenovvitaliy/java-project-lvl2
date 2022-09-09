package hexlet.code.formatters;

import hexlet.code.ComparedEntry;
import hexlet.code.DiffBuilder;

public class Stylish {
    public static String getInStylish(DiffBuilder diffBuilder) {
        StringBuilder str = new StringBuilder("{");

        int size = diffBuilder.getSize();
        if (size > 0) {
            str.append("\n");
        }

        for (ComparedEntry each : diffBuilder.getList()) {
            switch (each.getAction()) {
                case ADDED -> str.append("  + ")
                    .append(each.getKey())
                    .append(": ")
                    .append(each.getValue())
                    .append("\n");
                case REMOVED -> str.append("  - ")
                    .append(each.getKey())
                    .append(": ")
                    .append(each.getLastValue())
                    .append("\n");
                case NOTCHANGED -> str.append("    ")
                        .append(each.getKey())
                        .append(": ")
                        .append(each.getValue())
                        .append("\n");
                case CHANGED -> {
                    str.append("  - ")
                            .append(each.getKey())
                            .append(": ")
                            .append(each.getLastValue())
                            .append("\n");
                    str.append("  + ")
                            .append(each.getKey())
                            .append(": ")
                            .append(each.getValue())
                            .append("\n");
                }
                default -> throw new IllegalStateException("Unexpected action: " + each.getAction());
            }
        }
        str.append("}\n");
        return str.toString();
    }
}
