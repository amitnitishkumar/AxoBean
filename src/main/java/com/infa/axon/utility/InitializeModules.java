package com.infa.axon.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitializeModules {
	public static ApplicationContext context;
	
	public static void intializeAll(){
		context = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	public static ApplicationContext getContext(){
		return context;
	}

}
