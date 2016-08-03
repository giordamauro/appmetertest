package org.unicen.ameter.core.model;

import java.util.Collection;

public interface MetricRunner<M, O extends Operation<R>, R> {

	RunnerConfiguration getConfiguration();

	M runOperationAtomically(O operation);

	Collection<M> runOperation(O operation);
}
