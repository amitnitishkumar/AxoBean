package com.infa.axon.testcases.login;

import org.springframework.context.ApplicationContext;

import com.infa.axon.modules.Login;
import com.infa.axon.utility.InitializeModules;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LoginTest {
	
	@Given("^User has lauched Application$")
	public void lauchApplication(){
		ApplicationContext context = InitializeModules.getContext();
		Login objLogin = context.getBean("loginmodule", Login.class);
		objLogin.launchApplication();
		
	}
	
	@When("^User login to Axon$")
	public void loginToAxon() throws Exception{
		ApplicationContext context = InitializeModules.getContext();
		Login objLogin = context.getBean("loginmodule", Login.class);
		objLogin.loginToAxon();
		
	}
	

}
