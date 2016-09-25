package unicen.org.appmetertest;

import org.junit.Test;
import org.unicen.ameter.context.engine.ContextContainer;
import org.unicen.ameter.core.engine.Execution;
import org.unicen.ameter.core.engine.ExecutionPlan;
import org.unicen.ameter.core.engine.OperationExecution;
import org.unicen.ameter.core.model.MetricBatchResult;
import org.unicen.ameter.core.model.MetricBatchRunner;
import org.unicen.ameter.core.model.Operation;
import org.unicen.ameter.core.model.RunConfiguration;
import org.unicen.ameter.core.support.EmptyOperation;
import org.unicen.ameter.core.support.TimeMetricBatchResult;
import org.unicen.ameter.core.support.TimeMetricBatchRunner;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void testLoadJsonContext() throws Exception {

        // TODO: 05/09/2016 Load ExecutionPlan -> configure in JSON (empty operation, same iterations)

        InputStream assetInput = getClass().getResourceAsStream("context.json");
        ContextContainer contextContainer = ContextContainer.createFrom(assetInput);

        ExecutionPlan executionPlan = contextContainer.getBean(ExecutionPlan.class);
        for(Execution execution : executionPlan.getExecutions()) {

            MetricBatchRunner runner = execution.getRunner();
            for(OperationExecution operationExecution : execution.getOperations()) {

                Operation operation = operationExecution.getOperation();
                RunConfiguration config = operationExecution.getConfig();

                MetricBatchResult result = runner.execute(operation, config);

                System.out.println(result);
            }
        }
    }
}