package org.unicen.ameter.core.support;

import org.unicen.ameter.core.model.RunConfiguration;

/**
 * Created by Mauro Giorda on 04/09/2016.
 */
public class SimpleRunnerConfiguration implements RunConfiguration {

    private final int operationIterations;

    private int warmupIterations = 0;

    public SimpleRunnerConfiguration(int operationIterations) {

        if(operationIterations < 1) {
            throw new IllegalArgumentException("Operation iterations must be greater than 0 - " + operationIterations);
        }

        this.operationIterations = operationIterations;
    }

    public void setWarmupIterations(int warmupIterations) {
        this.warmupIterations = warmupIterations;
    }

    @Override
    public int getWarmupIterations() {
        return warmupIterations;
    }

    @Override
    public int getOperationIterations() {
        return operationIterations;
    }

    @Override
    public String toString() {
        return "SimpleRunnerConfiguration{" +
                "warmupIterations=" + warmupIterations +
                ", operationIterations=" + operationIterations +
                '}';
    }
}
