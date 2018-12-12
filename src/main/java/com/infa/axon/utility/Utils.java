package com.infa.axon.utility;

import java.io.File;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.infa.axon.reporter.LogHandler;

public class Utils {	
	Function<String, String>	getValue;
	

	

	public static String normalizePath(String path) {
		String normalizedPath = path.replace("/", File.separator);
		return normalizedPath;
	}
	
	public static String captureScreenshot(String testcaseName, String moduleName, WebDriver webDriver) {
		String screenshotPath = FileHelper.getScreenshotDirectory() + File.separator + moduleName + File.separator + testcaseName + ".png";
		captureScreenshot(webDriver, screenshotPath);
		return screenshotPath;
	}

	private static void captureScreenshot(WebDriver webDriver, String screenshotPath) {
		// This is a logical (in-memory) representation of the image file.
		File screenshotLogicalFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			File screenshotPhysicalFile = new File(screenshotPath);
			screenshotPhysicalFile.getParentFile().mkdirs(); // Create parent directories if they don't exist.
			FileUtils.copyFile(screenshotLogicalFile, screenshotPhysicalFile);
		} catch (Exception e) {
			LogHandler.getLogger().error("Failure in screenshot capture.", e);
			e.printStackTrace();
		}
	}
	
	
	
	
}