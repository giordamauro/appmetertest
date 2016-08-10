package org.unicen.ameter.core.model;

import java.util.Collection;

/**
 * Created by Mauro Giorda on 08/08/2016.
 * Metric for a batch run with many iterations.
 *
 * @param <M> The atomic Metric's type
 */

@FunctionalInterface
public interface MetricBatchResult<M> {

    /**
     * Getter exposing batch run iteration values.
     *
     * @return A collection with metric iterations
     */
    Collection<M> getMetricIterations();
}
