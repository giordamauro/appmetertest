package org.unicen.ameter.core.support;

import org.junit.Test;
import org.unicen.ameter.core.model.RunConfiguration;

public class TimeMetricRunnerTest {

    @Test
    public void timeMetricRunnerIntegrationTest() {
        // TODO: 10/08/2016 Document every class (Model classes)
        // TODO: 10/08/2016 Integrate test with Android app.
        // TODO: 10/08/2016 Think how to export values -> JSON
        // TODO: 10/08/2016 Parallel batch runner? Implement

        RunConfiguration config = new RunConfiguration() {
            @Override
            public int getWarmupIterations() {
                return 5;
            }

            @Override
            public int getOperationIterations() {
                return 10;
            }
        };

        TimeMetricBatchRunner runner = new TimeMetricBatchRunner(new TimeMetricRunner());

        TimeMetricBatchResult results = runner.execute(EmptyOperation.INSTANCE, config);

        System.out.println("Overhead average time: " + results.getAvgTimeInNanoseconds() + " nanoseconds");
    }
}