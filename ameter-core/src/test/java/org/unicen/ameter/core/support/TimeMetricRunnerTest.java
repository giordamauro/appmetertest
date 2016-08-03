package org.unicen.ameter.core.support;

import org.junit.Test;
import org.unicen.ameter.core.model.RunnerConfiguration;

import java.util.Collection;

/**
 * Created by Mauro Giorda on 03/08/2016.
 */
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

        TimeMetricRunner runner = new TimeMetricRunner(config);

        Collection<TimeMetricResult> results = runner.runOperation(EmptyOperation.INSTANCE);

        int i = 0;
        for (TimeMetricResult result: results) {

            System.out.println("Iteration " + i + ": " + result.getTimeInMilliseconds());
            i++;
        }
    }
}