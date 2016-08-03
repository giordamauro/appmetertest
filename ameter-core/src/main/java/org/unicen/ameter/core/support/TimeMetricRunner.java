package org.unicen.ameter.core.support;

import org.omg.CORBA.Object;
import org.unicen.ameter.core.model.MetricRunner;
import org.unicen.ameter.core.model.Operation;
import org.unicen.ameter.core.model.RunnerConfiguration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Mauro Giorda on 02/08/2016.
 */
public class TimeMetricRunner implements MetricRunner<TimeMetricResult, Operation<Object>, Object> {

    private final RunnerConfiguration runnerConfiguration;

    public TimeMetricRunner(RunnerConfiguration runnerConfiguration) {
        this.runnerConfiguration = runnerConfiguration;
    }

    @Override
    public RunnerConfiguration getConfiguration() {
        return runnerConfiguration;
    }

    @Override
    public TimeMetricResult runOperationAtomically(Operation operation) {

        double start = System.currentTimeMillis();
        operation.execute();
        double end = System.currentTimeMillis();

        final double executeTimeMillis = end - start;

        return new TimeMetricResult(executeTimeMillis);
    }

    @Override
    public Collection<TimeMetricResult> runOperation(Operation operation) {

        Objects.requireNonNull(operation, "Operation cannot be null");

        List<TimeMetricResult> results = new ArrayList<>();

        for(int i = 0; i < runnerConfiguration.getWarmupIterations(); i++){
            runOperationAtomically(operation);
        }

        for(int i = 0; i < runnerConfiguration.getOperationIterations(); i++){

            TimeMetricResult result = runOperationAtomically(operation);
            results.add(result);
        }

        return results;
    }
}
