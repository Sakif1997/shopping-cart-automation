package TestPageCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.Methods;
import PageObjectsAndMethods.ProductPage;

public class VerifySearchProduct extends BrowserSetup{
	ProductPage product = new ProductPage();
	Methods method = new Methods();
	@Test(description = "Scenario 7: Verifying Search Product Visibility")
	public void SearchProductVisible() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		product.searchProduct();
		method.WaitElementVisible(product.waitSearchElement);
		//screenshot
		assertEquals(method.getText(product.waitSearchElement),product.SearchProduct);
		Thread.sleep(2000);
		method.ScrollMiddle();
		Thread.sleep(2000);
		//screenshot
	}

}
