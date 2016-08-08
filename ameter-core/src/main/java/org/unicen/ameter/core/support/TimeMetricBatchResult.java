package org.unicen.ameter.core.support;

import org.unicen.ameter.core.model.MetricBatchResult;

import java.util.Collection;
import java.util.List;

/**
 * Created by Mauro Giorda on 02/08/2016.
 */
public class TimeMetricBatchResult implements MetricBatchResult<TimeMetricResult> {

    private final double avgTimeInMilliseconds;
    private final List<TimeMetricResult> metricIterations;


    public TimeMetricBatchResult(List<TimeMetricResult> metricIterations) {
        this.metricIterations = metricIterations;

        // TODO: 08/08/2016 Calculate
        this.avgTimeInMilliseconds = 0;
    }

    @Override
    public Collection<TimeMetricResult> getMetricIterations() {
        return metricIterations;
    }

    public double getAvgTimeInMilliseconds() {
        return avgTimeInMilliseconds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimeMetricBatchResult{");
        sb.append("avgTimeInMilliseconds=").append(avgTimeInMilliseconds);
        sb.append(", metricIterations=").append(metricIterations);
        sb.append('}');
        return sb.toString();
    }
}
