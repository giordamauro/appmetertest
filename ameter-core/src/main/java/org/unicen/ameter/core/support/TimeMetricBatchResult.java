package org.unicen.ameter.core.support;

import org.unicen.ameter.core.model.MetricBatchResult;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mauro Giorda on 02/08/2016.
 * TimeMetric for a batch run of one Operation.
 */
public class TimeMetricBatchResult implements MetricBatchResult<TimeMetricResult> {

    private final double avgTimeInNanoseconds;
    private final List<TimeMetricResult> metricIterations;


    /**
     * Constructor enforcing immutability.
     *
     * @param metricIterations A list of {@link TimeMetricResult} Cannot be null or empty.
     */
    public TimeMetricBatchResult(List<TimeMetricResult> metricIterations) {

        Objects.requireNonNull(metricIterations, "MetricIterations cannot be null");
        if(metricIterations.isEmpty()) {
            throw new IllegalArgumentException("MetricIteratons cannot be empty");
        }

        long timeSum = 0;
        for (TimeMetricResult result: metricIterations
             ) {
            timeSum += result.getTimeInNanoseconds();
        }

        this.metricIterations = metricIterations;
        this.avgTimeInNanoseconds = timeSum / metricIterations.size();
    }

    @Override
    public Collection<TimeMetricResult> getMetricIterations() {
        return metricIterations;
    }

    public double getAvgTimeInNanoseconds() {
        return avgTimeInNanoseconds;
    }

    @Override
    public String toString() {
        return "TimeMetricBatchResult{" +
                "avgTimeInNanoseconds=" + avgTimeInNanoseconds +
                ", metricIterations=" + metricIterations +
                '}';
    }
}
