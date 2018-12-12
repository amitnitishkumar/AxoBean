package com.infa.axon.modules;

import com.infa.axon.drivers.DriverFactory;
import com.infa.axon.drivers.DriverUtil;
import com.infa.axon.objectrepo.Elements;
import com.infa.axon.utility.PropertyReader;

public class Login  {
	Elements element;
	DriverFactory browser;
	DriverUtil wdUtil;
	
	public Login(DriverFactory browser, Elements element, DriverUtil wdUtil){
		this.browser = browser;
		this.element = element;
		this.wdUtil = wdUtil;
	}
	
	
	public void launchApplication(){
		browser.navigateToUrl(PropertyReader.readItem("Url"));
		wdUtil.waitForPageLoad();
	}
	
	public void loginToAxon() throws Exception{
		element.getElementIfVisible("lnkLogin_LoginPage").click();
		element.getElementIfVisible("txtEmail_LoginPage").sendKeys(PropertyReader.readItem("AxonUsername"));
		element.getElementIfVisible("txtPwd_LoginPage").sendKeys(PropertyReader.readItem("AxonPassword"));
		element.getElementIfVisible("btnLogin_LoginPage").click();
		
	}

}
