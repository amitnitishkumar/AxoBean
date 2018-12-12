package com.infa.axon.reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;

import java.io.IOException;

/**
 * Created by akash on 26/2/17.
 *
 *  Moving the loggers to ExtentReports to a separate class to allow easy modifications to these.
 */
public class ExtentLogger {

    public static void info(ExtentTest test, String message) {
        test.info(message);
    }
    
    public static void info(ExtentTest test, Markup m) {
        test.info(m);
    }

    public static void pass(ExtentTest test, String message) {
        test.pass(message);
    }

    public static void warn(ExtentTest test, String message) {
        test.warning(message);
    }

    public static void fail(ExtentTest test, String message) {
        test.fail(message);
    }

    /**
     *  Overloaded: This accepts a throwable to display the stacktrace.
     */
    public static void fail(ExtentTest test, String message, Throwable exception) {
        ExtentLogger.fail(test, message);
        test.fail(exception);
    }

    public static void error(ExtentTest test, String message) {
        test.error(message);
    }

    /**
     *  Overloaded: This accepts a throwable to display the stacktrace.
     */
    public static void error(ExtentTest test, String message, Throwable exception) {
        ExtentLogger.error(test, message);
        test.error(exception);
    }

    public static void logScreenshot(ExtentTest test, String imagePath, String imageTitle) {
        try {
            test.addScreenCaptureFromPath(imagePath, imageTitle);
        } catch (IOException e) {
            LogHandler.error(e);
            e.printStackTrace();
        }
    }
}