package TestPageCases;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.AddToCart;
import PageObjectsAndMethods.Methods;
import PageObjectsAndMethods.PaymentToDownloadInvoiceObjects;
import PageObjectsAndMethods.ProceedToAddressDetailsPage;
import PageObjectsAndMethods.ProductPage;
import PageObjectsAndMethods.logInValidate;

public class VerifyProceedToCheckoutButtonAndAddressDetails extends BrowserSetup{
	logInValidate logininputs = new logInValidate();
	ProductPage product = new ProductPage();
	AddToCart cartproductinfo = new AddToCart();
	ProceedToAddressDetailsPage proccedAndAddressobjects= new ProceedToAddressDetailsPage();
	PaymentToDownloadInvoiceObjects paymentTodownload = new PaymentToDownloadInvoiceObjects();
	Methods methods = new Methods();
	@Test(priority = 0, description="Login To cart and replicating scenario 7,8,9,10,11,12")
	public void LoginToShoppingCart() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		logininputs.validInput();
		//methods.WaitElementVisible(logininputs.FeaturedProduct);
		Thread.sleep(2000);
		product.searchProduct();
		cartproductinfo.NegativeProductQuantity();
		cartproductinfo.CartPage();
		Thread.sleep(2000);
		cartproductinfo.AddProductAfterNegativeProductQuantity();
		cartproductinfo.CartPage();
		Thread.sleep(2000);
		methods.takeScreenshot("cart page");
	}
	@Test(priority = 1, description = "Scenario 13: Verify Proceed to Checkout Button is not disabled and click on the button")
	public void VerifyProcedTocheckout() throws InterruptedException{
		proccedAndAddressobjects.proceedToCheckoutButton();
	}
	@Test(priority = 2, description = "Scenario 14: Verifying the details of DELIVERY ADDRESS and BILLING ADDRESS are the same.")
	public void AdressVerificationAndPlaceorder() throws InterruptedException{
		proccedAndAddressobjects.AddressDetail();
	}
	@Test(priority = 3, description = "Scenario 15: Placing an Order and filling up Payment details. You can put dummy card information.")
	public void PaymentToConfirmOrderAndDownloadInvoice() throws InterruptedException {
		paymentTodownload.PaymentInfo();
	}
	@Test(priority = 4, description = "Scenario 16,17 and 18: Downloading Invoice , check Download text and loging out")
	public void InvoiceVerification() throws InterruptedException {
		paymentTodownload.DownloadInvoiceAndLogout();
		
	}

}
