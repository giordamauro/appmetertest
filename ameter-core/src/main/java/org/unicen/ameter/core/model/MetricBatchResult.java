package org.unicen.ameter.core.model;

import java.util.Collection;

/**
 * Created by Mauro Giorda on 08/08/2016.
 */
public interface MetricBatchResult<M> {

    Collection<M> getMetricIterations();
}
