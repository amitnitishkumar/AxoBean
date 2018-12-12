/*package com.infa.axon.reporter;

import java.io.File;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.infa.axon.dataAccess.DefectDetailsXML;
import com.infa.axon.utility.DBUtils;
import com.infa.axon.utility.Utils;

*//**
 * Created by akashs on 3/2/2017.
 *//*
public class UnifiedLogger {
	private ExtentTest extentTest;
	private String testcaseName, moduleName;
	private int testRunId, testCaseId;
	private WebDriver webDriver;
	private String browser;
	private int stepCount;
	private String testStatus;
	private int failCount;

	*//**
	 * Create the object with the testcase name and module from the beginning. Each
	 * instance of the unified logger will be mapped to a single instance of the
	 * driver, in all the test cases.
	 *//*
	public UnifiedLogger(String testcaseName, String moduleName, String testClassName, WebDriver webDriver,
			String browser) {
		// This creates a testcase for the class. Keep in mind (just in case) that the
		// test-class is assigned
		// to a category every single time. So, this MIGHT lead to issues in the future.
		//ExtentReportHelper extentReportHelper = LauncherUtil.extentContainer.get(browser);
		//ExtentTest testClass = extentReportHelper.createTestInModule(testClassName, moduleName);
		//extentTest = extentReportHelper.createSubTest(testClass, testcaseName); // This puts the current testcase under
																				// the test-class.

		// These two details cannot be extracted from extentTest. Hence, storing them as
		// separate attributes.
		this.testcaseName = testcaseName;
		this.moduleName = moduleName;
		this.webDriver = webDriver;
		this.browser = browser;

		// For DB Report		
		this.stepCount = 1;
		this.failCount = 0;
		this.testStatus = "Fail";
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	
	public int getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}
	
	public ExtentTest getExtentTest() {
		return this.extentTest;
	}

	public void setExtentTest(ExtentTest extentTest) {
		this.extentTest = extentTest;
	}

	public String getBrowser() {
		return browser;
	}

	public String getTestcaseName() {
		return this.testcaseName;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public WebDriver getWebDriver() {
		return this.webDriver;
	}

	public void info(String message) {
		LogHandler.info(message);
		ExtentLogger.info(getExtentTest(), message);		
	}

	public void info(Markup m) {
		LogHandler.info(m);
		ExtentLogger.info(getExtentTest(), m);
	}

	public void pass(String message) {
		if(failCount==0)
			this.testStatus = "Pass";
		LogHandler.info(message);
		ExtentLogger.pass(getExtentTest(), "<font color=\"green\">" + message + "</font>");
				
	}

	public void warn(String message) {
		LogHandler.warn(message);
		this.testStatus = "Warn";
		ExtentLogger.warn(getExtentTest(), message);		
	}

	public void fail(String message) {
		this.testStatus = "Fail";
		failCount++;
		LogHandler.fail(message);
		String relativeScreenshotPath = Utils.captureScreenshot(getTestcaseName(), getModuleName(), getWebDriver());
		String absoluteScreenshotPath = System.getProperty("user.dir") + File.separator + relativeScreenshotPath;
		ExtentLogger.fail(getExtentTest(), "<font color=\"red\">" + message + "</font>");
			
		*//**Commenting the below line and updating the method call with networkPath
         * change by : @Chethan
         * Date : 10th Oct 2018
         *//*      
        ExtentLogger.logScreenshot(getExtentTest(), absoluteScreenshotPath, getTestcaseName());
       
        
		if (getBrowser().equalsIgnoreCase("chrome")) {
			getWebDriver().manage().logs().get(LogType.BROWSER).filter(Level.SEVERE).forEach(log -> ExtentLogger
					.fail(getExtentTest(), "<B style=\"color: red;\">Console Error Found : </B>" + log.getMessage()));
		}		
		

		// DB Report
		
	}

	public void failSoft(String message) {		
		failCount++;
		LogHandler.fail(message);
		String relativeScreenshotPath = Utils.captureScreenshot(getTestcaseName(), getModuleName(), getWebDriver());
		String absoluteScreenshotPath = System.getProperty("user.dir") + File.separator + relativeScreenshotPath;
		ExtentLogger.fail(getExtentTest(), message);

		*//**Commenting the below line and updating the method call with networkPath
         * change by : @Chethan
         * Date : 10th Oct 2018
         *//*      
        //ExtentLogger.logScreenshot(getExtentTest(), absoluteScreenshotPath, getTestcaseName());
        

		// DB Report
		
	}

	*//**
	 * Will not be re-using the other fail method here, as the defect xml will have
	 * to be handled different in case there are no exceptions.
	 *//*
	public void fail(String message, Throwable exception) {
		this.testStatus = "Fail";
		failCount++;
		LogHandler.fail(message, exception);
		String relativeScreenshotPath = Utils.captureScreenshot(getTestcaseName(), getModuleName(), getWebDriver());
		String absoluteScreenshotPath = System.getProperty("user.dir") + File.separator + relativeScreenshotPath;
		String networkScreenshotPath = Utils.getPathOnNetwork(absoluteScreenshotPath);
		ExtentLogger.fail(getExtentTest(), message, exception);
		ExtentLogger.logScreenshot(getExtentTest(), networkScreenshotPath, getTestcaseName());
		DefectDetailsXML.logDefectInXML(message, exception, getTestcaseName(), getModuleName(), networkScreenshotPath);

		// This fails the testcase in TestNG
		Assert.fail("TEST_CAPTURED : " + message);

		// DB Report
		DBUtils.updateTestResult(testCaseId, "Step " + Integer.toString(stepCount++), message, "Fail");
	}

	*//**
	 * Errors should be used only for automation errors and NOT product errors.
	 * Product errors should be logged as failures rather than errors.
	 *//*
	public void error(String message) {
		this.testStatus = "Fail";
		failCount++;
		LogHandler.error(message);
		ExtentLogger.error(getExtentTest(), message);
		//DB Report
        DBUtils.updateTestResult(testCaseId, "Step " + Integer.toString(stepCount++), message, "Fail");
	}

	public void error(String message, Throwable exception) {
		this.testStatus = "Fail";
		failCount++;
		LogHandler.error(message, exception);
		ExtentLogger.error(getExtentTest(), message, exception);
	}

	public void failVerification(String message) {
		this.testStatus = "Fail";
		failCount++;
		this.fail(message);
//        LauncherUtil.getFailedTests().add(getTestcaseName());
	}

	public void failVerification(String message, Throwable exception) {
		this.testStatus = "Fail";
		failCount++;
		this.fail(message);
//        LauncherUtil.getFailedTests().add(getTestcaseName());
	}
}
*/