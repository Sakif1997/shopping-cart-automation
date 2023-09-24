package TestPageCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.Methods;
import PageObjectsAndMethods.UserSignUpPage;
public class SignUp extends BrowserSetup{
	UserSignUpPage usersignup = new UserSignUpPage();
	Methods method = new Methods();
	@Test(description = "Scenario 3 & 4 SignUp Validation and logout")
	public void SignUpV() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		usersignup.SignUpForm();
		assertEquals(getDriver().getTitle(), usersignup.AccountCreatedPageTitle);
		System.out.println("New SignUp ID genarate confirmation page"+getDriver().getTitle());
		Thread.sleep(2000);
		method.takeScreenshot("New ID generated");
		method.clickElement(usersignup.ContinueButton);
		method.WaitElementVisible(usersignup.logoutButton);
		method.clickElement(usersignup.logoutButton);
		Thread.sleep(2000);
	}
	

}
