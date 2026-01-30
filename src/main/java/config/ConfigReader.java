package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read configuration values from a properties file.
 * This class loads the config.properties file located in the Config
 * directory at the root of the project.  Values are cached once
 * loaded to avoid reading the file multiple times.  Only simple
 * Java IO classes are used to keep the implementation straightforward.
 */
public class ConfigReader {

    private static Properties properties;

    /**
     * Loads the configuration file if it hasn't been loaded yet.
     */
    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                // Build the path to the config file relative to the project
                String path = System.getProperty("user.dir") + "/Config/config.properties";
                FileInputStream inputStream = new FileInputStream(path);
                properties.load(inputStream);
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Failed to load configuration: " + e.getMessage());
            }
        }
    }

    /**
     * Returns the value associated with the given key in the config file.
     * If the key does not exist, null is returned.
     *
     * @param key Property key from the config file
     * @return String value or null
     */
    public static String getProperty(String key) {
        loadProperties();
        return properties.getProperty(key);
    }
}