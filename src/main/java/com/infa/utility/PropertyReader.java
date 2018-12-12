package com.infa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
    static Properties properties;
    static Properties masterTestData;

    public static void loadAllProperties() {
        properties = loadPropertyFile(properties, "src/main/resources" + File.separator + "config.properties");      
    }

    public static Properties loadPropertyFile(Properties propObj, String filePath) {
        if (propObj != null) return propObj;    // Property file is read only once.

        propObj = new Properties();
        try {
            System.out.println("Loading property file: " + filePath);
            propObj.load(new FileInputStream(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Could not read property file: " + filePath);
        }
        return propObj;
    }

    public static synchronized String readItem(String propertyName){
        return properties.getProperty(propertyName);
    }
    
    public static synchronized void writeItem(String propertyName, String value){
        properties.put(propertyName, value);
    }

    public static synchronized String getTestData(String propertyName) {
        return masterTestData.getProperty(propertyName);
    }
}
