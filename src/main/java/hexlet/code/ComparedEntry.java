package hexlet.code;

public final class ComparedEntry {
    private final String key;
    private final Object lastValue;
    private final Object value;
    private final KeyAction action;

    public ComparedEntry(String key, Object lastValue, Object value, KeyAction action) {
        this.key = key;
        this.lastValue = lastValue;
        this.value = value;
        this.action = action;
    }

    public String getKey() {
        return key;
    }

    public Object getLastValue() {
        return lastValue;
    }

    public Object getValue() {
        return value;
    }

    public KeyAction getAction() {
        return action;
    }
}


