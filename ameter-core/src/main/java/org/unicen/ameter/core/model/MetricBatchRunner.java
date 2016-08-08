package org.unicen.ameter.core.model;

import java.util.Collection;

public interface MetricBatchRunner<M, O> extends MetricRunner<MetricBatchResult<M>, O> {

	RunnerConfiguration getConfiguration();
}
