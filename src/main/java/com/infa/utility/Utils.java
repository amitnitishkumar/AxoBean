package com.infa.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

public class Utils {	
	Function<String, String>	getValue;
	

	

	public static String normalizePath(String path) {
		String normalizedPath = path.replace("/", File.separator);
		return normalizedPath;
	}
	
	
}