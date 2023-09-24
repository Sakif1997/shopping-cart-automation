package PageObjectsAndMethods;


import org.openqa.selenium.By;

public class logInValidate extends Methods{
	public By LoginSignupPageRedirectButton = By.xpath("//a[normalize-space()='Signup / Login']");
	public By loginPage = By.xpath("//h2[normalize-space()='Login to your account']");
	public By EmailAdressField = By.xpath("//input[@data-qa='login-email']");
	public By PasswordField = By.xpath("//input[@placeholder='Password']");
	public By LoginButton = By.xpath("//button[normalize-space()='Login']");
	public By InvalidMessage = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");
	public String invalidloginmessage = "Your email or password is incorrect!";
	public By FeaturedProduct = By.xpath("//h2[normalize-space()='Features Items']");
	public By Logout =By.xpath("//a[normalize-space()='Logout']");
	//Invalid input validation method
	public void LoginWithInvalidEmail() throws InterruptedException{
		clickElement(LoginSignupPageRedirectButton);
		WaitElementVisible(loginPage);
		FieldValue(EmailAdressField, "sakif,com");
		FieldValue(PasswordField, "dkna1");
		Thread.sleep(2000);
		clickElement(LoginButton);
	}
	public void LoginWithInvalidPass() throws InterruptedException{
		clickElement(EmailAdressField);
		clearFieldAndInput("sakif4646@gmail.com");
		clickElement(PasswordField);
		clearFieldAndInput("dKna1");
		Thread.sleep(2000);
		clickElement(LoginButton);	
	}
	//valid input validation method
	public void validInput() throws InterruptedException{
		clickElement(LoginSignupPageRedirectButton);
		WaitElementVisible(loginPage);
		FieldValue(EmailAdressField, "sakif4646@gmail.com");
		FieldValue(PasswordField, "12345");
		Thread.sleep(2000);
		clickElement(LoginButton);
		
	}
}
