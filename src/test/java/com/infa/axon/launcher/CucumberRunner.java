package com.infa.axon.launcher;

import java.net.MalformedURLException;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;

import com.infa.axon.drivers.DriverFactory;
import com.infa.axon.utility.InitializeModules;
import com.infa.axon.utility.PropertyReader;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/resources/features"},
		plugin   = {"pretty","html:reports/html/chrome"},
		tags     = {"@first"},		
		glue     = {"com.axon.basic","com.infa.axon.testcases"}
		
		)
public class CucumberRunner extends AbstractTestNGCucumberTests {
	@BeforeClass
	public void setupPreConfig() {	
		    InitializeModules.intializeAll();
		    ApplicationContext context = InitializeModules.getContext();
		    PropertyReader.loadAllProperties();
			DriverFactory driverFactory = context.getBean("driverfactory", DriverFactory.class);
            try {
				driverFactory.createInstance();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
