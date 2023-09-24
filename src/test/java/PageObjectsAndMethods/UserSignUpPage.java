package PageObjectsAndMethods;
import static AutomationBrowser.BrowserSetup.getDriver;

import org.openqa.selenium.By;

public class UserSignUpPage extends Methods{
	public By LoginSignupPageRedirectButton = By.xpath("//a[normalize-space()='Signup / Login']");
	public By SignUpLoginPage =By.xpath("//h2[normalize-space()='OR']");
	public By UserName = By.xpath("//input[@placeholder='Name']");
	public By EmailAddress = By.xpath("//input[@data-qa='signup-email']");
	public By SignupButton = By.xpath("//button[normalize-space()='Signup']");
	public By WaitSignUpFormPage = By.xpath("//b[normalize-space()='Enter Account Information']");
	public By RadioButton = By.xpath("//input[@id='id_gender1']");
	//public By Name = By.xpath("//input[@id='name']");
	//public By Email = By.xpath("//input[@id='email']");
	public By Password = By.xpath("//input[@id='password']");
	public By SelectDay =By.xpath("//select[@id='days']");
	public By SelectMonth = By.xpath("//select[@id='months']");
	public By SelectYear =By.xpath("//select[@id='years']");	
	public By FName =By.xpath("//input[@id='first_name']");
	public By LName =By.xpath("//input[@id='last_name']");
	public By Adress =By.xpath("//input[@id='address1']");
	public By Country =By.xpath("//select[@id='country']");
	public By State =By.xpath("//input[@id='state']");
	public By City =By.xpath("//input[@id='city']");
	public By ZipCode =By.xpath("//input[@id='zipcode']");
	public By MobileNum =By.xpath("//input[@id='mobile_number']");
	public By CreateAccountButton = By.xpath("//button[normalize-space()='Create Account']");
	public By AccountCreatedPage = By.xpath("//b[normalize-space()='Account Created!']");
	public String EnterSignUpFormPageTitle ="Automation Exercise - Signup";
	public String AccountCreatedPageTitle= "Automation Exercise - Account Created";
	public By ContinueButton = By.xpath("//a[normalize-space()='Continue']");
	public By logoutButton = By.xpath("//a[normalize-space()='Logout']");
	public void SignUpForm() throws InterruptedException{
		clickElement(LoginSignupPageRedirectButton);
		WaitElementVisible(SignUpLoginPage);
		System.out.println("Login/signup Page Title:  " +getDriver().getTitle());
		Thread.sleep(2000);
		FieldValue(UserName, "Sakif Abdullah");
		FieldValue(EmailAddress, "sakif4695@gmail.com");
		Thread.sleep(2000);
		clickElement(SignupButton);
		WaitElementVisible(WaitSignUpFormPage);
		System.out.println("SingUp page Title:  " +getDriver().getTitle());
		clickElement(RadioButton);
		FieldValue(Password, "12345");
		clickElement(SelectDay);
		DropDownSelectByValue(SelectDay,"6");
		Thread.sleep(2000);
		clickElement(SelectMonth);
		DropDownSelectByVisibleText(SelectMonth,"December");
		Thread.sleep(2000);
		clickElement(SelectYear);
		DropDownSelectByValue(SelectYear,"1997");
		Thread.sleep(2000);
		FieldValue(FName, "Sakif");
		FieldValue(LName, "Abdullah");
		FieldValue(Adress, "Dhanmondi");
		clickElement(Country);
		DropDownSelectByVisibleText(Country, "Australia");
		FieldValue(State, "Sydney");
		FieldValue(City, "New South Wales");
		FieldValue(ZipCode, "2023");
		FieldValue(MobileNum, "123456789");
		Thread.sleep(2000);
		clickElement(CreateAccountButton);
		WaitElementVisible(AccountCreatedPage);
		takeScreenshot("Account Created");
	}

}
