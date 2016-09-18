package unicen.org.appmetertest;

import org.junit.Test;
import org.unicen.ameter.context.engine.ContextContainer;
import org.unicen.ameter.core.support.EmptyOperation;
import org.unicen.ameter.core.support.TimeMetricBatchResult;
import org.unicen.ameter.core.support.TimeMetricBatchRunner;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        InputStream assetInput = getClass().getResourceAsStream("context.json");
        ContextContainer contextContainer = ContextContainer.createFrom(assetInput);

        // TODO: 05/09/2016 Complete
        TimeMetricBatchRunner runner = contextContainer.getBean(TimeMetricBatchRunner.class);
        TimeMetricBatchResult result = runner.execute(EmptyOperation.INSTANCE);

        System.out.println(result);

        assertEquals(4, 2 + 2);
    }
}