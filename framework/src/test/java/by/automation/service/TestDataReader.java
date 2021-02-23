package by.automation.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getData(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
