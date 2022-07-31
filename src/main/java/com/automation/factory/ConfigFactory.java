package com.automation.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class ConfigFactory {
    private static Properties properties;
    private static final String propertyFilePath= "src" + File.separator + "main" + File.separator + "resources" + File.separator
            + "config.properties";
    BufferedReader reader;

    public ConfigFactory() {
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            properties.load(reader);
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getPathToDriver() {
        String driverPath = properties.getProperty("pathToDriver");
        if(driverPath != null) {
            return driverPath;
        } else {
            throw new RuntimeException("Driver path is unavailable in config file");
        }
    }
}
