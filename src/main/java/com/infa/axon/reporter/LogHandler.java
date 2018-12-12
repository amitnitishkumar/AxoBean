package com.infa.axon.reporter;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.aventstack.extentreports.markuputils.Markup;
import com.infa.axon.utility.FileHelper;

public class LogHandler {
	private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            String filename = FileHelper.getLogFilePath(Thread.currentThread().getName());
            logger = createLogger(filename);
        }
        return logger;
    }

    private static Logger createLogger(String filename) {
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %x - %m%n";
        layout.setConversionPattern(conversionPattern);

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        RollingFileAppender rollingFileAppender = new RollingFileAppender();
        rollingFileAppender.setFile(filename);
        rollingFileAppender.setLayout(layout);
        rollingFileAppender.activateOptions();
        rollingFileAppender.rollOver();

        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(rollingFileAppender);

        return rootLogger;
    }

    public static void info(Object msg) {
        getLogger().info(msg);
    }

    public static void info(Markup m) {
        getLogger().info(m);
    }
    
    public static void fail(Object msg) {
        getLogger().fatal(msg);
    }

    public static void fail(Object msg, Throwable throwable) {
        getLogger().fatal(msg, throwable);
    }

    public static void error(Object msg) {
        getLogger().error(msg);
    }

    public static void error(Object msg, Throwable throwable) {
        getLogger().error(msg, throwable);
    }

    public static void warn(Object msg) {
        getLogger().warn(msg);
    }

    public static void warn(Object msg, Throwable throwable) {
        getLogger().warn(msg, throwable);
    }

}
