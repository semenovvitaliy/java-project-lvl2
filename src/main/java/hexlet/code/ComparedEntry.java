package hexlet.code;

public final class ComparedEntry {
    private final Object thisLastValue;
    private final Object thisValue;
    private final KeyAction thisAction;

    public ComparedEntry(Object lastValue, Object value, KeyAction action) {
        thisLastValue = lastValue;
        thisValue = value;
        thisAction = action;
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


