package org.unicen.ameter.core.engine;

import java.util.List;
import java.util.Objects;

/**
 * Created by Mauro Giorda on 25/09/2016.
 */
public class ExecutionPlan {

    private final List<Execution> executions;

    public ExecutionPlan(List<Execution> executions) {

        Objects.requireNonNull(executions, "executions cannot be null");

        this.executions = executions;
    }

    public List<Execution> getExecutions() {
        return executions;
    }
}
