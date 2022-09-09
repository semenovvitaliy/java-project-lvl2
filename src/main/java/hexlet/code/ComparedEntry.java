package hexlet.code;

public final class ComparedEntry {
    private final String thisKey;
    private final Object thisLastValue;
    private final Object thisValue;
    private final KeyAction thisAction;

    public ComparedEntry(String key, Object lastValue, Object value, KeyAction action) {
        thisKey = key;
        thisLastValue = lastValue;
        thisValue = value;
        thisAction = action;
    }

    public String getKey() {
        return thisKey;
    }

    public Object getLastValue() {
        return thisLastValue;
    }

    public Object getValue() {
        return thisValue;
    }

    public KeyAction getAction() {
        return thisAction;
    }
}


