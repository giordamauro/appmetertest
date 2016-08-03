package org.unicen.ameter.core.support;

/**
 * Created by Mauro Giorda on 02/08/2016.
 */
public class TimeMetricResult {

    private final double timeInMilliseconds;

    public TimeMetricResult(double timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public double getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    @Override
    public String toString() {
        return "TimeMetricResult{" +
                "timeInMilliseconds=" + timeInMilliseconds +
                '}';
    }
}
