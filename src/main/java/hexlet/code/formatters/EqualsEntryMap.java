package hexlet.code.formatters;

public class EqualsEntryMap {
    protected static boolean isEquals(Object entry1, Object entry2) {
        if ((entry1 != null) && (entry2 != null)) {
            return entry1.equals(entry2);
        } else {
            return (entry1 == null) && (entry2 == null);
        }

    }
}
