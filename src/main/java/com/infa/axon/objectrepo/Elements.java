package com.infa.axon.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.ApplicationContext;

import com.infa.axon.drivers.DriverFactory;
import com.infa.axon.utility.InitializeModules;

public class Elements {	
	
	DriverFactory driverFactory;
	
	public Elements(DriverFactory driverFactory){
		this.driverFactory = driverFactory;		
	}
	
	public WebElement getElementIfVisible(String element) throws Exception{
		WebDriver driver = driverFactory.getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver,20);
		String loctaor="";
		String locatorValue = "";
		WebElement elementToReturn = null;
		if(element == null)
			throw new Exception();
		String repoValue = ObjectRepository.readObject(element);
        if(repoValue.contains("~:")){
        	loctaor = repoValue.split("~:")[0].trim().toLowerCase();
        	locatorValue = repoValue.split("~:")[1].trim();
        	switch(loctaor){
        	case "xpath":
        		elementToReturn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));    
        		break;
        	case "name":
        		elementToReturn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
        	}
		}
		
		return elementToReturn;
	}

}
