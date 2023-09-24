package TestPageCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.BonusTaskObjects;
import PageObjectsAndMethods.Methods;

public class BonusTask extends BrowserSetup{
	BonusTaskObjects taskobjects = new BonusTaskObjects();
	Methods method = new Methods();
	
	
	@Test(description = "Bonus Task: Verifying and match Blue Top Women's products from the menu bar and search bar.")
	public void MatchProductWithSearch() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		taskobjects.ManualAndAutoSearchProduct();
		method.takeScreenshot("Matched with Product");
		Thread.sleep(2000);
		//Match Product
		assertEquals(taskobjects.BlueTopSearch, taskobjects.BlueTop);
		System.out.println("Search Product Name: " +method.getText(taskobjects.BlueTopSearch));
		//Match Price
		assertEquals(taskobjects.BlueTopSearchPrice, taskobjects.BlueTopPrice);
		System.out.println("Search Product Price: " +method.getText(taskobjects.BlueTopSearchPrice));

	}

}
