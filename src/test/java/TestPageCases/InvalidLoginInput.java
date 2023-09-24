package TestPageCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.Methods;
import PageObjectsAndMethods.logInValidate;

public class InvalidLoginInput extends BrowserSetup{
	logInValidate logininputs = new logInValidate();
	Methods methods = new Methods();
	
	@Test(description = "Login User with invalid email and Invalid password. Verifying the warning/error message.")
	public void invalidInputresponse() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		//for InvalidEmail
		logininputs.LoginWithInvalidEmail();
		Thread.sleep(2000);
		methods.takeScreenshot("Invalid Indication");
		//for Invalid Pass
		logininputs.LoginWithInvalidPass();
		methods.WaitElementVisible(logininputs.InvalidMessage);
		assertEquals(methods.getText(logininputs.InvalidMessage), logininputs.invalidloginmessage);
		methods.takeScreenshot("Invalid Indication");
		System.out.println("Warning/Error message Validation: "+methods.getText(logininputs.InvalidMessage));
		Thread.sleep(2000);
	}

}
