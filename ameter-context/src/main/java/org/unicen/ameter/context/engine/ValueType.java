package org.unicen.ameter.context.engine;

import java.util.Objects;

/**
 * Created by Mauro Giorda on 18/09/2016.
 */
public class ValueType {

    private final Class<?> type;
    private final Object value;

    public ValueType(Class<?> type, Object value) {

        Objects.requireNonNull(type, "type cannot be null");

        this.type = type;
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
