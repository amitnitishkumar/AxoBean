package com.axon.laucher;

import org.testng.annotations.BeforeClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/resources/features"},
		plugin   = {"pretty","html:reports/html/chrome"},
		tags     = {"@first"},		
		glue     = {"com.axon.basic.test"}
		
		)
public class CucumberRunner extends AbstractTestNGCucumberTests {
	@BeforeClass
	public void start(){
		System.out.println("Start Test");
	}

}
