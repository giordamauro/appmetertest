package org.unicen.ameter.core.support;

import org.unicen.ameter.core.model.MetricBatchRunner;
import org.unicen.ameter.core.model.Operation;
import org.unicen.ameter.core.model.RunConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mauro Giorda on 02/08/2016.
 *
 * @param <O> Operation type
 */
public class TimeMetricBatchRunner<O> implements MetricBatchRunner<TimeMetricResult, Operation<O>> {

    private final TimeMetricRunner timeMetricRunner;

    /**
     * Public constructor.
     *
     * @param timeMetricRunner    The object providing time metrics itself - Not null
     */
    public TimeMetricBatchRunner(TimeMetricRunner timeMetricRunner) {

        Objects.requireNonNull(timeMetricRunner, "TimeMetricRunner cannot be null");

        this.timeMetricRunner = timeMetricRunner;
    }

    @Override
    public TimeMetricBatchResult execute(Operation operation, RunConfiguration runConfiguration) {

        Objects.requireNonNull(operation, "Operation cannot be null");

        List<TimeMetricResult> results = new ArrayList<>();

        for (int i = 0; i < runConfiguration.getWarmupIterations(); i++) {
            timeMetricRunner.execute(operation);
        }

        for (int i = 0; i < runConfiguration.getOperationIterations(); i++) {

            TimeMetricResult result = timeMetricRunner.execute(operation);
            results.add(result);
        }

        return new TimeMetricBatchResult(results);
    }
}
