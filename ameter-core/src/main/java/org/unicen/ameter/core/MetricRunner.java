package org.unicen.ameter.core;

import java.util.Collection;

public interface MetricRunner<M, O extends Operation<R>, R> {

	RunnerConfiguration getConfiguration();
	
	Collection<M> runOperation(O operation);
}
