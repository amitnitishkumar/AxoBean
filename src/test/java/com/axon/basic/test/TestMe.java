package com.axon.basic.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;

public class TestMe {
	@Given("^User has opened Mercury Tours WebSite$")
	public void execute(){
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
	}

}
