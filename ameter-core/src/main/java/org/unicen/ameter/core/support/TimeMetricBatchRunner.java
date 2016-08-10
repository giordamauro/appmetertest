package org.unicen.ameter.core.support;

import org.unicen.ameter.core.model.MetricBatchRunner;
import org.unicen.ameter.core.model.Operation;
import org.unicen.ameter.core.model.RunnerConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mauro Giorda on 02/08/2016.
 *
 * @param <O> Operation type
 */
public class TimeMetricBatchRunner<O> implements MetricBatchRunner<TimeMetricResult, Operation<O>> {

    private final RunnerConfiguration runnerConfiguration;
    private final TimeMetricRunner timeMetricRunner;

    /**
     * Public constructor.
     *
     * @param timeMetricRunner    The object providing time metrics itself - Not null
     * @param runnerConfiguration The config for this batch run - Not null
     */
    public TimeMetricBatchRunner(TimeMetricRunner timeMetricRunner, RunnerConfiguration runnerConfiguration) {

        Objects.requireNonNull(timeMetricRunner, "TimeMetricRunner cannot be null");
        Objects.requireNonNull(runnerConfiguration, "RunnerConfiguration cannot be null");

        this.runnerConfiguration = runnerConfiguration;
        this.timeMetricRunner = timeMetricRunner;
    }

    @Override
    public RunnerConfiguration getConfiguration() {
        return runnerConfiguration;
    }


    @Override
    public TimeMetricBatchResult execute(Operation operation) {

        Objects.requireNonNull(operation, "Operation cannot be null");

        List<TimeMetricResult> results = new ArrayList<>();

        for (int i = 0; i < runnerConfiguration.getWarmupIterations(); i++) {
            timeMetricRunner.execute(operation);
        }

        for (int i = 0; i < runnerConfiguration.getOperationIterations(); i++) {

            TimeMetricResult result = timeMetricRunner.execute(operation);
            results.add(result);
        }

        return new TimeMetricBatchResult(results);
    }
}
