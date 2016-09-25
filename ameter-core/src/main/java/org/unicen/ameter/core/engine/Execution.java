package org.unicen.ameter.core.engine;

import org.unicen.ameter.core.model.MetricBatchRunner;

import java.util.List;
import java.util.Objects;

/**
 * Created by Mauro Giorda on 25/09/2016.
 */
public class Execution {

    private final MetricBatchRunner runner;
    private final List<OperationExecution> operations;

    public Execution(MetricBatchRunner runner, List<OperationExecution> operations) {

        Objects.requireNonNull(runner, "runner cannot be null");
        Objects.requireNonNull(operations, "operations cannot be null");

        this.runner = runner;
        this.operations = operations;
    }

    public MetricBatchRunner getRunner() {
        return runner;
    }

    public List<OperationExecution> getOperations() {
        return operations;
    }
}
