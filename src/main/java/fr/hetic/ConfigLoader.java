package fr.hetic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final String PROP_FILE = "/application.properties";

    public static String getProperty(String key) {
        Properties props = new Properties();
        try (InputStream inputStream = ConfigLoader.class.getResourceAsStream(PROP_FILE)) {
            if (inputStream == null) {
                throw new IOException("Cannot find configuration file " + PROP_FILE);
            }
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(key);
    }
}
