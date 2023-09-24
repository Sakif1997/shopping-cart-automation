package TestPageCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.AddToCart;
import PageObjectsAndMethods.Methods;
import PageObjectsAndMethods.ProductPage;

public class VerifyNegativeCartProductDetails extends BrowserSetup{
	ProductPage product = new ProductPage();
	AddToCart cartproductinfo = new AddToCart();
	Methods method = new Methods();
	@Test(priority = 0, description = "Scenario 8,9,10: Search product, Add quantity of that product negative(-2), Verifying Price, negative Quantity and Negative Total. ")
	public void quantityToProductdetails() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		product.searchProduct();
		cartproductinfo.NegativeProductQuantity();
		cartproductinfo.CartPage();
		Thread.sleep(2000);
		//screenshot
		assertEquals(method.getText(cartproductinfo.CartProductQuantity), cartproductinfo.NegativeQuantity);
		System.out.println("Verify Negative Quantity Visible which is: " +method.getText(cartproductinfo.CartProductQuantity));
		System.out.println("Verif Neagative Quantity Product Total price: "+method.getText(cartproductinfo.CartProductPrice));
	}
	@Test (priority = 1, description = "scenario 11,12 : Verifying after negation; add a quantity of 10 for the product, Verifying total quantity,price")
	public void QuantityAddToSameProduct() throws InterruptedException{
		cartproductinfo.AddProductAfterNegativeProductQuantity();
		cartproductinfo.CartPage();
		Thread.sleep(2000);
		//screenshot
		assertEquals(method.getText(cartproductinfo.positiveProductQuantity), cartproductinfo.AfterAddProductQuantity);
		System.out.println("Verify total Product Quantity Visible after adding 10 same product which is: " +method.getText(cartproductinfo.positiveProductQuantity));
		System.out.println("Verify  Total Product quantity price: "+method.getText(cartproductinfo.postiveProductQuantityPrice));
	}

}
