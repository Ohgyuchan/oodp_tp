package classes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum State {
    TODO,
    DOING,
    DONE;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
