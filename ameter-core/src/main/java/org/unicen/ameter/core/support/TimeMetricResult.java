package org.unicen.ameter.core.support;

/**
 * Created by Mauro Giorda on 02/08/2016.
 * This class holds the Time Metric results for one operation execution.
 */
public class TimeMetricResult {

    private final long timeInNanoseconds;

    /**
     * Default Consutructor. Enforces immutability
     * @param timeInNanoseconds long nanoseconds Must be positive
     */
    public TimeMetricResult(long timeInNanoseconds) {

        if(timeInNanoseconds < 0) {
            throw new IllegalArgumentException("TimeInNanoseconds cannot be negative");
        }

        this.timeInNanoseconds = timeInNanoseconds;
    }

    public long getTimeInNanoseconds() {
        return timeInNanoseconds;
    }

    @Override
    public String toString() {
        return "TimeMetricResult{" +
                "timeInNanoseconds=" + timeInNanoseconds +
                '}';
    }
}
