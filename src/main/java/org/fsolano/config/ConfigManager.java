package org.fsolano.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        String route = System.getProperty("config.properties","src/test/java/GlobalProperties.properties");
        try {
            FileInputStream fileInputStream = new FileInputStream(route);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key)
    {
        return properties.getProperty(key);
    }
}
