package com.infa.utility;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.infa.reporter.LogHandler;

public class FileHelper {

	/********************************************************************************************
	 * @Function_Name : deleteExecutionCompleteFile
	 * @Description : Check for Execution Complete File If the Files Exist then delete the Files
	 *******************************************************************************************/
	public static boolean deleteExecutionCompleteFile() {
		String folderLocation = System.getProperty("user.dir");
		File executionCompleteFile = new File(folderLocation + File.separator + "ExecutionComplete.txt");
		if (executionCompleteFile.exists()) {
			try {
				FileUtils.forceDelete(executionCompleteFile);
				if (executionCompleteFile.exists()) {
					LogHandler.warn("Unable to delete ExecutionComplete File: " + executionCompleteFile);
				} else {
					LogHandler.info("ExecutionComplete File " + executionCompleteFile + " deleted !!");
				}
			} catch (IOException e) {
				LogHandler.warn("Tried to delete file : " + e.getMessage());
			}
		} else {
			LogHandler.warn("ExecutionComplete File does not Exist.");
		}
		return true;
	}

	/********************************************************************************************
	 * @Function_Name : deleteDefectFile
	 * @Description : Check for BGAutomation log file If the Files Exist then delete the Files
	 *******************************************************************************************/
	public static boolean deleteDefectXMLFile() {
		String folderLocation = System.getProperty("user.dir");
		File logFile = new File(folderLocation + File.separator + "TestDefects.xml");
		if (logFile.exists()) {
			try {
				FileUtils.forceDelete(logFile);
				if (logFile.exists()) {
					LogHandler.warn("Unable to delete Defect Xml: " + logFile);
				} else {
					LogHandler.info("Defect Xml " + logFile + " deleted !!");
				}
			} catch (IOException e) {
				LogHandler.warn("Tried to delete file : " + e.getMessage());
			}
		} else {
			LogHandler.warn("Defect Xml does not Exist.");
		}
		return true;
	}

	/********************************************************************************************
	 * @Function_Name : deleteLogFile
	 * @Description : Check for BGAutomation log file If the Files Exist then delete the Files
	 *******************************************************************************************/
	public static boolean deleteLogFile() {
		File logFile = new File(FileHelper.getLogFilePath(Thread.currentThread().getName()));
		if (logFile.exists()) {
			try {
				FileUtils.forceDelete(logFile);
				if (logFile.exists()) {
					System.err.println("Unable to delete Log File: " + logFile);
				} else {
					System.out.println("Log File " + logFile + " deleted !!");
				}
			} catch (IOException e) {
				System.out.println("Tried to delete file : " + e.getMessage());
			}
		} else {
			System.err.println("Log File does not Exist.");
		}
		return true;
	}

	/********************************************************************************************
	 * @Function_Name : deleteExecutionFiles
	 * @Description : Check for Files inside Execution Directory. If the Files Exist then delete the Files
	 *******************************************************************************************/
	public static boolean deleteExecutionFiles() {
		String folderLocation = System.getProperty("user.dir");
		File screenshotDir = new File(folderLocation + File.separator + "BatchExecution");
		if (screenshotDir.exists() && screenshotDir.isDirectory()) {
			try {
				FileUtils.cleanDirectory(screenshotDir);
				if (screenshotDir.list() != null) {
					System.out.println("Files inside Screenshot Directory deleted !!");
				} else {
					System.err.println("Files inside Screenshot Directory could not be deleted");
				}
			} catch (IOException e) {
				System.err.println("Unable to delete Files inside Screenshots Directory");
				e.printStackTrace();
			}
		} else {
			System.err.println("Screenshot Directory does not Exist.");
		}
		return true;
	}
	
	/********************************************************************************************
	 * @Function_Name : deleteDownloadFiles
	 * @Description : Check for Files inside Execution Directory. If the Files Exist then delete the Files
	 *******************************************************************************************/
	public static boolean deleteDownloadedFiles() {
		File screenshotDir = new File(FileHelper.getDownloadDirectory());
		if (screenshotDir.exists() && screenshotDir.isDirectory()) {
			try {
				FileUtils.cleanDirectory(screenshotDir);
				if (screenshotDir.list() != null) {
					System.out.println("Files inside Screenshot Directory deleted !!");
				} else {
					System.err.println("Files inside Screenshot Directory could not be deleted");
				}
			} catch (IOException e) {
				System.err.println("Unable to delete Files inside Screenshots Directory");
				e.printStackTrace();
			}
		} else {
			System.err.println("Screenshot Directory does not Exist.");
		}
		return true;
	}

	/**
	 * Find the latest file from the directory	
	 */
	public static File lastFileModified(String dir) {
		File fl = new File(dir);
		File[] files = fl.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		long lastMod = Long.MIN_VALUE;
		File choice = null;
		for (File file : files) {
			if (file.lastModified() > lastMod) {
				choice = file;
				lastMod = file.lastModified();
			}
		}
		boolean flag = true;
		String temp=choice.getName();
		do
		{    	
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!reNameFile(choice.getAbsolutePath(), dir+File.separator+"test"+temp))
			{
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fl = new File(dir);
				files = fl.listFiles(new FileFilter() {
					public boolean accept(File file) {
						return file.isFile();
					}
				});
				lastMod = Long.MIN_VALUE;
				choice = null;
				for (File file : files) {
					if (file.lastModified() > lastMod) {
						choice = file;
						lastMod = file.lastModified();
					}
					temp=choice.getName();
				}
			}
			else
			{
				flag=false;
				reNameFile(dir+File.separator+"test"+temp,dir+File.separator+temp);
			}
				
		}while(flag);
		return choice;
	}
	
	/**
	 * Find the latest file from the directory with Name
	 */
	public static File lastFileModifiedWithName(String dir, String fileName) {
		File folder = new File(dir);
		Optional<File> mostRecentFile =
			    Arrays.stream(folder.listFiles())
			        .filter(f -> f.isFile())
			        .filter(f4 -> f4.getName().contains(fileName))
			        .max((f1, f2) -> Long.compare(f1.lastModified(),f2.lastModified()));
	    return mostRecentFile.get();
	}

    /**
     *  This adds login properties into the existing test case properties object.
     *  Login.properties in login directory.
     */
	public static Properties getTestDataForClassWithLoginData(String testClassName, String moduleName)
            throws IOException {

		Properties moduleData = getTestDataForClass(testClassName, moduleName);
		//loadTestDataIntoObject(moduleData, "Login", "login");  // commenting as login.properties are no longer used, instead using config.properties
		return moduleData;
	}

	/**
	 *  Keeping this method even though it has a single line of code. This is because we can easily make changes to
	 *  this functionality locally, without modifying the BeforeMethod.
	 */
	public static Properties getTestDataForClass(String testClassName, String moduleName) throws IOException {
		Properties moduleData = new Properties();
		loadTestDataIntoObject(moduleData, testClassName, moduleName);
		return moduleData;
	}

	/**
	 * 	Writing this method cuz it's too hard to remember this huge line of code every single time
     * 	it needs to be written.
	 */
	public static void loadTestDataIntoObject(Properties moduleData, String testClassName, String moduleName)
            throws IOException {
		moduleData.load(new FileInputStream(new File(FileHelper.getTestDataFileFor(testClassName, moduleName))));
	}

	/**
	 * Side-effect: Will create the output directory if it doesn't exist.
	 */

	public static synchronized String getFrameworkOutputDirectory() {
		String frameworkDirectory = PropertyReader.readItem("framework_output_directory");
		return frameworkDirectory;
	}
	
	public static synchronized String getWebCrawlerOutputDirectory() {
		String frameworkDirectory = getFrameworkOutputDirectory() + File.separator
			+PropertyReader.readItem("webCrawler_dir_path");
		return frameworkDirectory;
	}

	public static synchronized String getExtentReportsDirectory() {
		String extentReportsDirectory = getFrameworkOutputDirectory() + File.separator
                                        + PropertyReader.readItem("extent_report_path");
		return extentReportsDirectory;
	}

	public static synchronized String getMailReportDirectory() {
		String mailReportPath = getFrameworkOutputDirectory() + File.separator
                                + PropertyReader.readItem("email_report_path");
		return mailReportPath;
	}

	public static synchronized String getXmlOutputDirectory() {
		String xmlOutputDirectory = getFrameworkOutputDirectory() + File.separator
                                    + PropertyReader.readItem("xml_output_path");
		return xmlOutputDirectory;
	}

	public static synchronized String getScreenshotDirectory() {
		String screenshotDirectory = getFrameworkOutputDirectory() + File.separator
                                     + PropertyReader.readItem("screenshot_dir_path");
		return screenshotDirectory;
	}

	public static synchronized String getTimeStampDirectory() {
		String screenshotDirectory = getFrameworkOutputDirectory() + File.separator
                                     + PropertyReader.readItem("timeStamp_dir_path");
		return screenshotDirectory;
	}

	public static synchronized String getMailReport() {
		String mailReport = getMailReportDirectory() + File.separator + PropertyReader.readItem("reports_file_name")
                            + "_" + Thread.currentThread().getName() + ".html";
		return mailReport;
	}

	public static synchronized String getXmlOutputFile() {
		String xmlOutputFile = getXmlOutputDirectory() + File.separator + PropertyReader.readItem("reports_file_name")
                               + "_" + Thread.currentThread().getName() + ".xml";
		return xmlOutputFile;
	}

	public static synchronized String getLogFilePath(String threadID) {
		String logFilePath = getFrameworkOutputDirectory() + File.separator + PropertyReader.readItem("logs_dir_path")
                             + File.separator + "axomation_%s.log";
		return String.format(logFilePath, threadID);
	}

	public static synchronized String getTimeStampPath(String param1) {
		String logFilePath = getFrameworkOutputDirectory() + File.separator
                             + PropertyReader.readItem("timeStamp_dir_path") + "_" + param1 + ".xml";
		return logFilePath;
	}

	public static synchronized void createDirectory(String directoryName) {
		File dirFile = new File(directoryName);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
	}

	public static String getFrameworkResourcesDirectory() {
		String resourcesDirectory = "resources";
		return resourcesDirectory;
	}

	public static String getTestDataPath() {
		String filePath = getFrameworkResourcesDirectory() + File.separator + "testData";
		return filePath;
	}

	public static String getTestDataModulePath(String moduleName) {
        String filePath = getTestDataPath() + File.separator + moduleName;
        return filePath;
    }

	public static String getTestDataFileFor(String testClassName, String moduleName) {
	    String testDataFilePath = getTestDataModulePath(moduleName) + File.separator + testClassName + ".properties";
		return testDataFilePath;
	}
	
	public static String getBulkUploadFileFor(String bulkUploadFile){
		String bulkUploadFilePath = getTestDataPath() + File.separator + "BulkUpload" + File.separator + bulkUploadFile;
		return new File(bulkUploadFilePath).getAbsolutePath();
	}
	
	public static String getValeListFileFor(String file) {
		String filePath = getTestDataPath() + File.separator + "DataSet" + File.separator + "UploadNewItems"+ File.separator + file;
		return new File(filePath).getAbsolutePath();
	}
	
	public static String getDownloadDirectory()
	{
		return System.getProperty("user.dir")+ File.separator + "output" + File.separator + "DownloadDir";
	}
	
	public static String getAutomationDirectory()
	{
		return System.getProperty("user.dir");
	}
//	public static  String getBulkUploadTempleteDownloadDir(String bulkUploadTemplateDownloadDir) {
//		String folderLocation = System.getProperty("user.dir");
//		String extentReportsDirectory = folderLocation+File.separator+getFrameworkOutputDirectory() + File.separator +  bulkUploadTemplateDownloadDir;
//		return extentReportsDirectory;
//	}
//	
//	public static  String getBulkUploadErrorReportDownloadDir(String bulkUploadErrorDownloadDir) {
//		String folderLocation = System.getProperty("user.dir");
//		String extentReportsDirectory = folderLocation+File.separator+getFrameworkOutputDirectory() + File.separator +  bulkUploadErrorDownloadDir;
//		return extentReportsDirectory;
//	}
//	
	
	/******************************************************************
	 * @Description Delete Old Template Files
	 *******************************************************************/

	public static  void removeOldTemplates(String path) {
		try
		{
			//reading location from config.properties
			File file = new File(path);
			if (file.getParent().isEmpty())
				new File(file.getParent()).mkdir();
			else
			{
				File[] templates = file.listFiles();
				for(File template : templates)
				{
					template.delete();
					System.out.println("Deleting Old Template File For "+template);
				}
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			System.out.println("File not found Exception");
		}
	}
	
	/******************************************************************
	 * @author chethanr
	 * @Description: This method returns the Path to the Customizations folder with the file to be created under it
	 * @param: The file to be created
	 * @exception
	 ******************************************************************/
	public static String getCustomizationPath(String file){
		String uploadImagePath = getTestDataPath() + File.separator + "Customizations" + File.separator + file;
		return new File(uploadImagePath).getAbsolutePath();
	}
	
	/******************************************************************
	 * @author chethanr
	 * @Description: This method returns the Path to the Customizations folder 
	 * @param:
	 * @exception
	 ******************************************************************/
	public static String getCustomizationsDirectory() {
		String path = getTestDataPath() + File.separator + "Customizations" + File.separator;
		return new File(path).getAbsolutePath();
	}

	/**
	 * @description Renaming a file
	 * @author mareddy 
	 * @method RenameFile
	 * @param oldFile name and newFile 
	 */
	public static boolean reNameFile(String oldFile, String newFile) {

	    // File (or directory) with old name
	    File file = new File(oldFile);

	    // File (or directory) with new name
	    File file2 = new File(newFile);

	    // Rename file (or directory)
	    boolean success = file.renameTo(file2);

	    return success;
	}
	
	/******************************************************************
	 * @author chethanr
	 * @Description: This method returns the Path to the PDF Downloads folder for Customizations  
	 * @param:
	 * @exception
	 ******************************************************************/
	public static String getPDFDownloadsDirectory_Customization() {
		String path = getDownloadDirectory() + "//" + "Exported_PDFs";
		return path;
	}
	
	public static void copyFile(File source, File dest) throws IOException {
		FileUtils.copyFile(source, dest);
	}
	
	public static List<String> getFilesList(File path) {
		File[] listOfFiles = path.listFiles();
		List<String> files = new ArrayList<String>();
		for(File f : listOfFiles) {
			files.add(f.getName());
		}		
		return  files;
	}
	
	public static List<String> getFilesList(File path, FilenameFilter filter) {
		File[] lisOfFiles = path.listFiles(filter);
		List<String> files = new ArrayList<String>();
		for(File f : lisOfFiles) {
			files.add(f.getName());
		}
		return files;
	}
	
}
