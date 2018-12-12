package com.axon.basic.test;

import cucumber.api.java.en.Given;

public class TestMe {
	@Given("^User has opened Mercury Tours WebSite$")
	public void execute(){
		System.out.println("Hello World");
	}

}
