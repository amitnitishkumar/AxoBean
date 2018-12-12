package com.infa.axon.reporter;

import java.io.File;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.infa.axon.utility.FileHelper;
import com.infa.axon.utility.PropertyReader;
import com.infa.axon.utility.Utils;

/**
 * Created by akashs on 2/24/2017.
 */
public class ExtentReportHelper {
	private ExtentReports extentReports; // This object is directly
												// linked with the html file.

	private HashMap<String, ExtentTest> testMap; // Testcase name ->
														// ExtentTest object.
	private String browser;
	
	public String getExtentReportFilePath() {
		String reportFilePath = FileHelper.getExtentReportsDirectory() + File.separator+ "report";
								
		return reportFilePath;
	}

	public ExtentReports getExtentReport() {
		return this.extentReports;
	}

	public void initializeExtentReports(String browser) {
		this.browser = browser;
		initializeMaps();
		String reportFilePath = getExtentReportFilePath();
		extentReports = createExtentReports(reportFilePath);
		setSystemDetailsInReport();
	}

	// Add system details like Hostname, URL etc.
	private void setSystemDetailsInReport() {
        //extentReports.setSystemInfo("Hostname", Utils.getSystemHostname());
        //extentReports.setSystemInfo("Axon URL", Utils.getAxonUrlFor(Utils.getBrowserType(browser)));
        extentReports.setSystemInfo("Browser Type", browser);
        extentReports.setSystemInfo("Axon Version", PropertyReader.readItem("Version"));
        extentReports.setSystemInfo("Axon Instance Type", PropertyReader.readItem("Instance"));
        extentReports.setSystemInfo("Test Priority Level", PropertyReader.readItem("priority"));
    }

	/**
	 * Creates a singleton of extentReports object.
	 */
	private ExtentReports getExtentReports() {
		if (extentReports == null) {
			initializeExtentReports(browser);
		}
		return extentReports;
	}

	private void initializeMaps() {
		this.testMap = new HashMap<String, ExtentTest>();
	}

	private ExtentReports createExtentReports(String reportFilePath) {
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(createExtentHtmlReporter(reportFilePath));
		return extentReports;
	}

	/**
	 * The htmlReporter is linked to the actual file in the file system. It is
	 * wrapped by the extentReports object for all operations.
	 */
	private ExtentHtmlReporter createExtentHtmlReporter(String reportFilePath) {
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
		return extentHtmlReporter;
	}

	public ExtentTest createTestInModule(String testName, String moduleName) {
		ExtentTest test = getTest(testName);
		test.assignCategory(moduleName);
		return test;
	}

	/**
	 * Returns a test object if testName already exists. Else, creates a new
	 * test object for testName. Side-effect: testMap is updated for newly
	 * created tests.
	 */
	public ExtentTest getTest(String testName) {
		ExtentReports extentReports = getExtentReports();
		if (this.testMap.containsKey(testName)) {
			return this.testMap.get(testName);
		} else {
			ExtentTest extentTest = extentReports.createTest(testName);
			this.testMap.put(testName, extentTest);
			return extentTest;
		}
	}

	/**
	 * Creates subTest inside a parent test. The sub-tests are added to the
	 * testMap as well. (Sub-tests are tests, so these can be accessed by
	 * getTest()) NOTE: Overloaded: String, String -> ExtentTest
	 */
	public ExtentTest createSubTest(ExtentTest test, String subTestName) {
		ExtentTest subTest = test.createNode(subTestName);
		testMap.put(subTestName, subTest);
		return subTest;
	}

	/**
	 * Check description in overloaded method. NOTE: Overloaded: ExtentTest,
	 * String -> ExtentTest
	 */
	public ExtentTest createSubTest(String testName, String subTestName) {
		return getTest(testName).createNode(subTestName);
	}

	public void closeReport() {
		extentReports.flush();
	}
	
	
}
