package org.unicen.ameter.core.support;

import org.junit.Test;
import org.unicen.ameter.core.model.RunnerConfiguration;

public class TimeMetricRunnerTest {

    @Test
    public void timeMetricRunnerIntegrationTest() {
        //TODO: Implement RunnerConfiguration (does it need to be an interface? To be extensible
        // TODO: 03/08/2016 How does TimeMetricRunner returns Average times?


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

        TimeMetricBatchRunner runner = new TimeMetricBatchRunner(config, new TimeMetricRunner());

        TimeMetricBatchResult results = runner.execute(EmptyOperation.INSTANCE);

        int i = 0;
        for (TimeMetricResult result : results.getMetricIterations()) {

            System.out.println("Iteration " + i + ": " + result.getTimeInNanoseconds());
            i++;
        }
    }
}