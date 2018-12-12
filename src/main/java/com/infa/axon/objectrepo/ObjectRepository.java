package com.infa.axon.objectrepo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ObjectRepository {
	static Properties objectRepo;    

    public static void loadAllObjects() {
    	objectRepo = loadRepository(objectRepo, "src/main/resources" + File.separator + "objectrepository.properties");      
    }

    public static Properties loadRepository(Properties propObj, String filePath) {
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

    public static synchronized String readObject(String propertyName){
        return objectRepo.getProperty(propertyName);
    }
    
    public static synchronized void writeObject(String propertyName, String value){
    	objectRepo.put(propertyName, value);
    }
    

}
