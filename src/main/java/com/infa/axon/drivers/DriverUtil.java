package com.infa.axon.drivers;

import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.ApplicationContext;
import com.infa.axon.utility.InitializeModules;

public class DriverUtil {
	
	DriverFactory driverFactory;
	
	public DriverUtil(DriverFactory driverFactory){
		this.driverFactory = driverFactory;
	}
	
	public void waitForPageLoad() {		
		WebDriver webDriver = driverFactory.getWebDriver();
		final JavascriptExecutor js = (JavascriptExecutor) webDriver;
		WebDriverWait wait = new WebDriverWait(webDriver, 40, 500); 
		wait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver arg0) { 
				try{
					boolean response = (Boolean)js.executeScript("return jQuery.active == 0");
					return response;
				}catch(Exception e){
					return false;
				}

			}

		});
		wait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver arg0) { 
				try{
					boolean response = js.executeScript("return document.readyState").toString().equals("complete");
					return response;
				}catch(Exception e){
					return false;
				}

			}

		});
		wait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver arg0) {
				try {
					Boolean response = (Boolean) js.executeScript("return $('.icon-spinner2').is(':visible') == false");				
					return response;
				} catch (Exception e) {
					return false;
				}
			}
		});
		
	}

}
