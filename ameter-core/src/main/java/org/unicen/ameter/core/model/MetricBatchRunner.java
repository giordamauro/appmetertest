package org.unicen.ameter.core.model;

public interface MetricBatchRunner<M, O> {

    MetricBatchResult<M> execute(O operation, RunConfiguration runConfiguration);
}
