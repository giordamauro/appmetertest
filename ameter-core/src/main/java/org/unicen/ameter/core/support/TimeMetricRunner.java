package org.unicen.ameter.core.support;

import org.unicen.ameter.core.model.MetricRunner;
import org.unicen.ameter.core.model.Operation;

/**
 * Created by Mauro Giorda on 02/08/2016.
 */
public class TimeMetricRunner implements MetricRunner<TimeMetricResult, Operation> {

    @Override
    public TimeMetricResult execute(Operation operation) {

        long start = System.nanoTime();
        operation.execute();
        long end = System.nanoTime();

        final long executeTimeNanos = end - start;

        return new TimeMetricResult(executeTimeNanos);
    }
}
