package org.unicen.ameter.core.support;

/**
 * Created by Mauro Giorda on 02/08/2016.
 * This class holds the Time Metric results for one operation execution.
 */
public class TimeMetricResult {

    private final long timeInNanoseconds;

    /**
     * Default Consutructor. Enforces immutability
     * @param timeInNanoseconds long nanoseconds
     */
    public TimeMetricResult(long timeInNanoseconds) {
        this.timeInNanoseconds = timeInNanoseconds;
    }

    public long getTimeInNanoseconds() {
        return timeInNanoseconds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimeMetricResult{");
        sb.append("timeInNanoseconds=").append(timeInNanoseconds);
        sb.append('}');
        return sb.toString();
    }
}
