package TestPageCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.LinkToHomePage;
import PageObjectsAndMethods.Methods;

public class HomePage extends BrowserSetup{
	LinkToHomePage home = new LinkToHomePage();
	Methods method = new Methods();
	
	@Test(description = "Scenario 1 & 2 Home page visibility test")
	public void LinkToHome() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		assertEquals(getDriver().getTitle(), home.HomeTitle);
		System.out.println("Home Page Title:  " +getDriver().getTitle());
		method.takeScreenshot("Home Page");
		
	}

}
