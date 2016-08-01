package unicen.org.appmetertest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.unicen.ameter.context.engine.ContextContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        ProgressDialog progressdialog = ProgressDialog.show(MainActivity.this, "Application", "Running tests", true);
    }

    private InputStream openAsset(String assetName) {

        try {
            InputStream asset = getAssets().open(assetName);

            return asset;
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
