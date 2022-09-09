package hexlet.code;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class DiffBuilder {

    private LinkedList<ComparedEntry> listEntry = new LinkedList<>();
    private final int size;

    public DiffBuilder(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> allKeysSet = new TreeSet<>(map1.keySet());
        allKeysSet.addAll(map2.keySet());

        for (String keyEntry : allKeysSet) {
            if (!map1.containsKey(keyEntry)) {
                this.listEntry.add(new ComparedEntry(keyEntry, null, map2.get(keyEntry), KeyAction.ADDED));
            } else if (!map2.containsKey(keyEntry)) {
                this.listEntry.add(new ComparedEntry(keyEntry, map1.get(keyEntry), null, KeyAction.REMOVED));
            } else if (isEquals(map1.get(keyEntry), map2.get(keyEntry))) {
                this.listEntry.add(new ComparedEntry(
                        keyEntry,
                        map1.get(keyEntry),
                        map1.get(keyEntry),
                        KeyAction.NOTCHANGED));
            } else {
                this.listEntry.add(new ComparedEntry(
                        keyEntry,
                        map1.get(keyEntry),
                        map2.get(keyEntry),
                        KeyAction.CHANGED));
            }
        }
        this.size = this.listEntry != null ? this.listEntry.size() : 0;
    }

    public final int getSize() {
        return this.size;
    }

    private static boolean isEquals(Object entry1, Object entry2) {
        if ((entry1 != null) && (entry2 != null)) {
            return entry1.equals(entry2);
        } else {
            return (entry1 == null) && (entry2 == null);
        }
    }

    public final LinkedList<ComparedEntry> getList() {
        return new LinkedList<>(listEntry);
    }
}
