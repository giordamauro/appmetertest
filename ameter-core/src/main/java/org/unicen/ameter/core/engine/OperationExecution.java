package org.unicen.ameter.core.engine;

import org.unicen.ameter.core.model.Operation;
import org.unicen.ameter.core.model.RunConfiguration;

import java.util.Objects;

/**
 * Created by Mauro Giorda on 25/09/2016.
 */
public class OperationExecution {

    private final Operation operation;
    private final RunConfiguration config;

    public OperationExecution(Operation operation, RunConfiguration config) {

        Objects.requireNonNull(operation, "execution cannot be null");
        Objects.requireNonNull(config, "execution cannot be null");

        this.operation = operation;
        this.config = config;
    }

    public Operation getOperation() {
        return operation;
    }

    public RunConfiguration getConfig() {
        return config;
    }
}
