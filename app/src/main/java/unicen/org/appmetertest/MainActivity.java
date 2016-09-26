package unicen.org.appmetertest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.unicen.ameter.context.engine.ContextContainer;
import org.unicen.ameter.core.engine.Execution;
import org.unicen.ameter.core.engine.ExecutionPlan;
import org.unicen.ameter.core.engine.OperationExecution;
import org.unicen.ameter.core.model.MetricBatchResult;
import org.unicen.ameter.core.model.MetricBatchRunner;
import org.unicen.ameter.core.model.Operation;
import org.unicen.ameter.core.model.RunConfiguration;
import org.unicen.ameter.core.support.EmptyOperation;
import org.unicen.ameter.core.support.TimeMetricBatchRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.myservice.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        System.out.println("androidBuild: " + gson.toJson(AndroidBuild.INSTANCE));

        InputStream assetInput = openAsset("context.json");
        ContextContainer contextContainer = ContextContainer.createFrom(assetInput);

        ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Application", "Running tests", true);

        int i = 1;
        List<MetricBatchResult> results = new ArrayList<>();

        ExecutionPlan executionPlan = contextContainer.getBean(ExecutionPlan.class);
        for(Execution execution : executionPlan.getExecutions()) {

            MetricBatchRunner runner = execution.getRunner();
            for(OperationExecution operationExecution : execution.getOperations()) {

                Operation operation = operationExecution.getOperation();
                RunConfiguration config = operationExecution.getConfig();

                progressDialog.setMessage("Running test " + i);

                MetricBatchResult result = runner.execute(operation, config);
                results.add(result);
                i++;
            }
        }

        System.out.println(gson.toJson(results));

        finish();
    }

    private InputStream openAsset(String assetName) {

        try {
            return getAssets().open(assetName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    private static void displayLogcat(TextView textView) {
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log=new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }

            textView.setText(log.toString());
        } catch (IOException e) {
        }
    }
}
