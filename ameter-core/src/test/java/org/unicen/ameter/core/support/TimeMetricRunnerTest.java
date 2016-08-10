package org.unicen.ameter.core.support;

import org.junit.Test;
import org.unicen.ameter.core.model.RunnerConfiguration;

public class TimeMetricRunnerTest {

    @Test
    public void timeMetricRunnerIntegrationTest() {
        // TODO: 10/08/2016 Document every class (Model classes)
        // TODO: 10/08/2016 Integrate test with Android app.
        // TODO: 10/08/2016 Think how to export values
        // TODO: 10/08/2016 Parallel batch runner? Implement

        RunnerConfiguration config = new RunnerConfiguration() {
            @Override
            public int getWarmupIterations() {
                return 5;
            }

            @Override
            public int getOperationIterations() {
                return 10;
            }
        };

        TimeMetricBatchRunner runner = new TimeMetricBatchRunner(new TimeMetricRunner(), config);

        TimeMetricBatchResult results = runner.execute(EmptyOperation.INSTANCE);

        System.out.println("Overhead average time: " + results.getAvgTimeInNanoseconds() + " nanoseconds");
    }
}