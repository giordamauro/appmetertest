package org.unicen.ameter.core.model;

public interface MetricRunner<M, O> {

	M execute(O operation);
}
