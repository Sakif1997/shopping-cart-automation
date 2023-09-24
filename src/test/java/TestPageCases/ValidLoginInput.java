package TestPageCases;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.logInValidate;
import PageObjectsAndMethods.Methods;

public class ValidLoginInput extends BrowserSetup {
	logInValidate logininputs = new logInValidate();
	Methods methods = new Methods();
	@Test(description = "Scenario 6: Login User with the correct email and password. Verifying All featured product are visible")
	public void validLoginInput() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		logininputs.validInput();
		Thread.sleep(2000);
		methods.WaitElementVisible(logininputs.FeaturedProduct);
		methods.ScrollMiddle();
		methods.ScrollDown();
		methods.takeScreenshot("Feature Product");
		methods.ScrollUp();
		Thread.sleep(2000);
		methods.clickElement(logininputs.Logout);
	}
}
