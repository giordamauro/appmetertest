package org.unicen.ameter.context.engine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;

import org.unicen.ameter.context.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class ContextContainer {

    private static final Gson gson = new GsonBuilder().create();

    private final Map<String, String> placeholderProperties;
    private final BeanContainer beanContainer;

    public static ContextContainer createFrom(String contextFile) {

        Objects.requireNonNull(contextFile, "ContextFile cannot be null");

        Reader reader = getFileReader(contextFile);

        Context context = readJsonContext(reader);
        ContextContainer container = new ContextContainer(context);

        return container;
    }

    public static ContextContainer createFrom(InputStream contextInput) {

        Objects.requireNonNull(contextInput, "ContextInput cannot be null");

        Reader reader = new InputStreamReader(contextInput);
        Context context = readJsonContext(reader);
        ContextContainer container = new ContextContainer(context);

        return container;
    }

    private static Reader getFileReader(String contextFile) {

        try {
            Reader reader = new FileReader(contextFile);

            return reader;
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Cannot read context", e);
        }
    }

    private static Context readJsonContext(Reader reader) {

        Objects.requireNonNull(reader, "reader cannot be null");

        try {
            JsonReader jsonReader = new JsonReader(reader);
            return gson.fromJson(jsonReader, Context.class);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot read context", e);
        }
    }

    private ContextContainer(Context context) {
        Objects.requireNonNull(context, "Context cannot be null");

        PlaceholderContainer placeholderContainer = new PlaceholderContainer(context.getPlaceholders());
        this.placeholderProperties = placeholderContainer.getPlaceholderProperties();

        this.beanContainer = new BeanContainer(context.getBeans(), placeholderProperties);
    }

    public String getProperty(String name) {

        String value = placeholderProperties.get(name);
        if (value == null) {
            throw new IllegalStateException("Property not found: " + name);
        }
        return value;
    }

    public <T> T getBean(Class<T> beanClass) {
        return beanContainer.getBeanByClass(beanClass);
    }

    public <T> T getBean(Class<T> beanClass, String beanId) {
        return null;
    }
}
