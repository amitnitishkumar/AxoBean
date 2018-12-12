package com.infa.axon.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.infa.axon.objectrepo.ObjectRepository;
import com.infa.axon.reporter.LogHandler;
import com.infa.axon.utility.PropertyReader;
import com.infa.axon.utility.Utils;

public class DriverFactory {	
	WebDriver		webDriver;	
		

		

	public void createInstance() throws MalformedURLException {
		String			folderLocation	= System.getProperty("user.dir");		
        String 	browser = PropertyReader.readItem("browser");
		LogHandler.info(String.format("Creating an instance of webDrive - Browser: %s",
				browser));

		switch (browser.toLowerCase()) {
		case "chrome":

			String chromeDriverPath = "";
			
				LogHandler.info("Executing on "+System.getProperty("os.name")+". Fetching Win ChromeDriver");
				chromeDriverPath = Utils.normalizePath(PropertyReader.readItem("chromeDriver"));
				System.setProperty("webdriver.chrome.driver", folderLocation + File.separator + chromeDriverPath);

			if(chromeDriverPath == "") {
				LogHandler.fail("Chrome Driver initialization failed check the OS!!");
			}					
			DesiredCapabilities capChrome = DesiredCapabilities.chrome();
			capChrome.setBrowserName("chrome");
			String downloadPath = System.getProperty("user.dir")+ File.separator + "output" + File.separator + "DownloadDir";
			ChromeOptions options = new ChromeOptions();
			options.addArguments("no-network-profilr-warnin");
			options.addArguments("safebrowsing-disable-auto-update");
			options.addArguments("disable-infobars");
			options.addArguments("start-maximized");			
			options.addExtensions(new File(PropertyReader.readItem("extension")));
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("credentials_enable_service", false);
			prefs.put("password_manager_enabled", false);
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.default_directory",  downloadPath);
			options.setExperimentalOption("prefs", prefs);
			webDriver = new ChromeDriver(options);				
			break;		

		default:
			System.err.println("Please check the Browser values in Configuration property file. Exiting Automation !!! ");
			System.exit(0);
			break;
		}		
		webDriver.manage().deleteAllCookies();		
		ObjectRepository.loadAllObjects();
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void navigateToUrl(String url) {
		getWebDriver().navigate().to(url);
	}

}
