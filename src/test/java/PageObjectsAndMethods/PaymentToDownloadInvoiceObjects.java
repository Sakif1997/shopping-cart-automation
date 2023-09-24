package PageObjectsAndMethods;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;

public class PaymentToDownloadInvoiceObjects extends Methods{
	public By PaymentPage =By.xpath("//h2[normalize-space()='Payment']");
	public By NameonCardField =By.xpath("//input[@name='name_on_card']");
	public By CardNumberField =By.xpath("//input[@name='card_number']");
	public By CVCField =By.xpath("//input[@placeholder='ex. 311']");
	public By ExpirationField =By.xpath("//input[@placeholder='MM']");
	public By YearField =By.xpath("//input[@placeholder='YYYY']");
	public By PayAndConfirmOrderButton =By.xpath("//button[@id='submit']");
	public By waitOrderPlacePage = By.xpath("//b[normalize-space()='Order Placed!']");
	public By OrderConfirmationMessage = By.xpath("//p[normalize-space()='Congratulations! Your order has been confirmed!']");
	public String orderconfirmationmesaage ="Congratulations! Your order has been confirmed!";
	public By DownloadInvoiceButton =By.xpath("//a[normalize-space()='Download Invoice']");
	public By Logout =By.xpath("//a[normalize-space()='Logout']");
	public By WaitSignoutLogInPage =By.xpath("//h2[normalize-space()='OR']");
	public void PaymentInfo() throws InterruptedException{
		FieldValue(NameonCardField, "Sakif");
		FieldValue(CardNumberField, "123");
		FieldValue(CVCField, "443");
		FieldValue(ExpirationField, "12");
		FieldValue(YearField, "2023");
		Thread.sleep(2000);
		takeScreenshot("Field Value Input Image");
		clickElement(PayAndConfirmOrderButton);
		WaitElementVisible(waitOrderPlacePage);
	}
	public void DownloadInvoiceAndLogout() throws InterruptedException{
		assertEquals(getText(OrderConfirmationMessage), orderconfirmationmesaage);
		clickElement(DownloadInvoiceButton);
		Thread.sleep(2000);
		takeScreenshot("DOwnloading Invoice");
		clickElement(Logout);
		WaitElementVisible(WaitSignoutLogInPage);
		Thread.sleep(2000);
	}
}
