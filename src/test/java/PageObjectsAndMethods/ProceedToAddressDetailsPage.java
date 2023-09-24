package PageObjectsAndMethods;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;

public class ProceedToAddressDetailsPage extends Methods {
	public By ProceedToCheckoutButton =By.xpath("//a[normalize-space()='Proceed To Checkout']");
	public By AddressPage =By.xpath("//h2[normalize-space()='Address Details']");
	public By DeliveryAddress =By.xpath("(//li[@class='address_address1 address_address2'][normalize-space()='Dhanmondi'])[1]");
	public By BillingAddress =By.xpath("(//li[@class='address_address1 address_address2'][normalize-space()='Dhanmondi'])[2]");
	public By PlaceOrderButton = By.xpath("//a[normalize-space()='Place Order']");
	public By PaymentPage =By.xpath("//h2[normalize-space()='Payment']");
	public void proceedToCheckoutButton() throws InterruptedException{
		takeScreenshot("Proceed to checkout Button visible");
		clickElement(ProceedToCheckoutButton);
		WaitElementVisible(AddressPage);
	}
	public void AddressDetail() throws InterruptedException{
		assertEquals(getText(DeliveryAddress), getText(BillingAddress));
		takeScreenshot("Address details");
		Thread.sleep(2000);
		clickElement(PlaceOrderButton);
		WaitElementVisible(PaymentPage);
		Thread.sleep(2000);
	}

}
