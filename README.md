
# Shopping Website Automation

This project is for end-to-end testing of the "https://automationexercise.com/" website using the Page Object Model (POM) pattern. The following testing procedure will cover a wide range of functionalities, including user registration, login, product searching, cart management, checkout, and more.

## Target/goals
following scenarios, which will be replicated
1. Visit https://automationexercise.com/  
2. Verify that the home page is visible successfully  
3. Sign Up with all the required and non-required fields (Date of Birth) including assertions.  
4. Log out from the website.   
5. Login User with incorrect email and password. Verify the warning/error message.
6. Login User with the correct email and password. Verify All Products and product detail page.  
7. Search Product. You can choose your desired product. Verify 'SEARCHED PRODUCTS' is visible.  
8. Add Products to Cart.  
9. Add quantity as negative such as (-2) for the product and click on Add to Cart button and Continue Shopping button.  
10. Verify Price, negative Quantity and Negative Total.  
11. Add the same Product to the cart, add a quantity of 10 for the product, and click on the Add to Cart button and Continue Shopping button.  
12. Verify Price, Quantity and Total.  
13. Verify Proceed to Checkout Button is not disabled and click on the   
14. Verify the details of DELIVERY ADDRESS and BILLING ADDRESS are the same.  
15. Place an Order and fill up Payment details. You can put dummy card information  
16. Download the invoice.  
17. Verify the invoice.txt file exists in the project, verify the text in the file and after that remove the text file from the project.   
18. Log out from the website.





## Test workflow

### Test file information
The following instructions will help you navigate those testing pages. As I am following the page object model, I will create some packages. At the package level, there is a list of classes where you can create methods, use methods for particular pages, and run and test the testing pages separately and parallelly as well.  


1. Set Environment
i) pom.xml [dependencies set]  
ii) BrowserSetup[create separate package ]

2. Page Object Model: create methods, using methods for separate page and create test cases of those pages  
i) Browser Control/setup [package name:AutomationBrowser]  
ii) Methods and Page objects[package name: PageObjectsAndMethods]  
iii) TestCases [package name: TestPageCases ]

3. Create Allure report 
i) pom.xml [dependencies set for allure report]
ii) Testng.xml [to run all test file togather]

Package Visualization:
![PackageImage](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/5e3d908d-d654-4809-a666-8fc0d688c7d0)


##  Set Environment
Setting up a browser that will contain the testing process:  
Create a Maven Project  
Set pom.xml file   
pom.xml file Code:  
Set Under Dependencies
```ruby
  <dependencies>
      <!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.6.1</version>
    <scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.6.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.3.0</version>
</dependency>
  </dependencies>
</project>
```
## BrowserControl/Setup
Create BrowserSetup class under Package  
The package will hold BorwserSetup in which we run the automation Code 
Inside BorwserSetup Class:   
It will hold three Driver(Chrome, Firefox, and Edge), use according to your preferences  
"I prefer to use the Edge Driver (Edge Browser) to run my code."
```ruby
package AutomationBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup {
	private static String BrowserName = System.getProperty("browser", "Edge");
	private static final ThreadLocal<WebDriver> DRIVER_LOCAL = new ThreadLocal<>();
	public static WebDriver getDriver() {
		return DRIVER_LOCAL.get();
	}
	public static void setDriver(WebDriver driver) {
		BrowserSetup.DRIVER_LOCAL.set(driver);
	}
	public static WebDriver getBrowser(String BrowserName) {
		switch (BrowserName.toLowerCase()) {
		case "chrome":
			ChromeOptions option1 = new ChromeOptions();
			option1.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver(option1);
		case "edge":
			EdgeOptions option2 = new EdgeOptions();
			option2.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver(option2);
		case "firefox":
			FirefoxOptions option3 = new FirefoxOptions();
			option3.addArguments("--remote-allow-origins=*");
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver(option3);
		default:
			throw new RuntimeException("Browser Not found");
		}
	}
	@BeforeSuite
	public static synchronized void setBrowser() {
		WebDriver webDriver = getBrowser(BrowserName);
		webDriver.manage().window().maximize();
		setDriver(webDriver);
	}
	@AfterSuite
	public static synchronized void quitBrowser() {
		getDriver().quit();
	}
}
```

## Page Object model
### Methods
Create a package that includes methods and Methods make it easier to implement parallel testing. My method page contains methods that cover more test scenarios in less time.  
Methods class includes methods of getElement, getText, Click, Hover, Field fillup, Wait element for visible, Dropdown, click wait element, Scroll up, Scroll down, Scroll middle, Clear field value, and take screenshot for allure report.

```ruby
package PageObjectsAndMethods;

import static AutomationBrowser.BrowserSetup.getDriver;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Methods {
	public WebElement getElement(By locator) {
		//import static AutomationBrowser.BrowserSetup.getDriver;
		return getDriver().findElement(locator);//driver = getDriver();
	}
	//click element by locator
	public void clickElement(By locator) {
		getElement(locator).click();
	}
	// Entering field value
	public void FieldValue(By locator, String text) {
		getElement(locator).sendKeys(text);
	}
	//Wait for desire element
	public void WaitElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	//wait for desire element and click
	public void clickWaitElement(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
	}
	//mouse pointer over any option or hovering
	public void Hover(By locator) {
		Actions action = new Actions(getDriver());
		//action.moveToElement(driver.findElement(locator)).perform();
		action.moveToElement(getElement(locator)).perform();;
	}
	// Dropdown option to select numeric value
	public void DropDownSelectByValue(By locator, String text) {
		WebElement dropDownField = getElement(locator);
		Select select = new Select(dropDownField);
		select.selectByValue(text);
	}
	// Drop down menu to see the desire option which hold text and select it
	public void DropDownSelectByVisibleText(By locator, String text) {
		WebElement dropDownField = getElement(locator);
		Select select = new Select(dropDownField);
		select.selectByVisibleText(text);
	}
	//Get the text from inspected area
	public String getText(By locator) {
		WebElement element =getElement(locator);
		return element.getText();
		
	}
	//Scroll up 
	public void ScrollUp() {
		JavascriptExecutor js =(JavascriptExecutor)getDriver();
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	//Scroll down
	public void ScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	//scroll middle
	public void ScrollMiddle() {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");

	}
	//clear the input field value
	public void clearFieldAndInput(String text) {
		Actions action = new Actions(getDriver());
		action.keyDown(Keys.CONTROL);
		action.sendKeys("a");
		action.keyUp(Keys.CONTROL);
		action.sendKeys(Keys.DELETE);
		action.sendKeys(text);
		action.build().perform();
	}
	//for allure report
	public void takeScreenshot(String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
	    }
}


```
### Scenario Replication Classes

### Targeted Scenario(1,2)  
1. Visit https://automationexercise.com/  
2. Verify that the home page is visible successfully  

Overview of the selecting path of the Home page image:  
![HomePageselect](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/f0ddf969-116c-4f30-a64a-59ff69e88cd0)

Here, I build a class that has the xpaths required to produce scenarios.  
Then, I create another class under a separate package and create an object for the previous class that generates test scenarios 1 and 2.  
Used Methods: "assertequals" to match the homepage title.

Method:
PackageName: PageObjectsAndMethods  
CLassName: LinkToHomePage
```ruby
package PageObjectsAndMethods;

import org.openqa.selenium.By;

public class LinkToHomePage extends Methods{
	public By Homepage =By.xpath("//title[normalize-space()='Automation Exercise']");
	public String HomeTitle = "Automation Exercise";
}
```
For testSuite Run:    
PackageName: TestPageCases  
CLassName: HomePage  

```ruby
package TestPageCases;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.LinkToHomePage;

public class HomePage extends BrowserSetup{
	LinkToHomePage home = new LinkToHomePage();
	
	@Test(description = "Scenario 1 & 2 Home page visibility test")
	public void LinkToHome() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		assertEquals(getDriver().getTitle(), home.HomeTitle);
		System.out.println("Home Page Title:  " +getDriver().getTitle());
		//screenShot
	}

}
```
Execution Result image:  
![TestResult 1 2](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/068e9c14-cf0f-4c07-8bf4-3efe308e46f9)


### Next Targeted scenario(3,4)
3. Sign Up with all the required and non-required fields (Date of Birth) including assertions  
4. Log out from the website.  
Overview of the selecting path of the page image:  
![s 3   4](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/34ebd17c-6a7a-4c0e-a534-8706658a20c5)
To replicate those scenario:
Here, I build a class that has the xpaths required to produce scenarios, and the SignUp method to fill out the form requires fields to create a user account.
Then, I create another class under a separate package in which I use that page object and the Signup method that generates test scenarios 3 and 4.  

Method:   
PackageName: PageObjectsAndMethods  
CLassName: UserSignUpPage  
```ruby
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
	public By logoutButton = By.xpath("//a[normalize-space()='Logout']");
	public void SignUpForm() throws InterruptedException{
		clickElement(LoginSignupPageRedirectButton);
		WaitElementVisible(SignUpLoginPage);
		System.out.println("Login/signup Page Title:  " +getDriver().getTitle());
		Thread.sleep(2000);
		FieldValue(UserName, "Sakif Abdullah");
		FieldValue(EmailAddress, "sakif46466@gmail.com");
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
		//screenshot)
		Thread.sleep(2000);
		clickElement(CreateAccountButton);
		WaitElementVisible(AccountCreatedPage);
	}

}

```
For testSuite Run:  
PackageName: TestPageCases 
CLassName: SignUp
```ruby
package TestPageCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.Methods;
import PageObjectsAndMethods.UserSignUpPage;
public class SignUp extends BrowserSetup{
	UserSignUpPage usersignup = new UserSignUpPage();
	Methods method = new Methods();
	@Test(description = "Scenario 3 & 4 SignUp Validation")
	public void SignUpV() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		usersignup.SignUpForm();
		assertEquals(getDriver().getTitle(), usersignup.AccountCreatedPageTitle);
		System.out.println("New SignUp ID genarate confirmation page"+getDriver().getTitle());
		Thread.sleep(2000);
		//screenshot
		method.clickElement(usersignup.logoutButton);
	}
	

}
```
Execution Result Image:    
![TestResult 3  $](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/16f2b803-0316-417e-917c-2f4bc4825dfc)

### Next Targeted scenario(5,6)
5. Login User with incorrect email and password. Verify the warning/error message.  
6. Login User with the correct email and password. Verify All Products and product
detail page.  
Overview of the selecting path of the page image:  
![loginva](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/2ade4e0a-00a1-4140-8993-08ba4d283fab)  
To replicate those scenario:
Here, I build a class that has the xpaths required to produce scenarios. This class contains methods for invalid (invalid email and password) and valid input procedures by using custom methods from the Methods class.  
Then, I create another two classes under a separate package in which I use those valid and invalid input methods to validate login and also verify warning or error messages by using assertion methods like assertEqual to compare actual values with expected values, which tends to generate test scenarios 5 and 6.  

Method:  
PackageName: PageObjectsAndMethods  
CLassName: logInValidate  
```ruby
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
```

For testSuite Run:  
PackageName: TestPageCases  
CLassName: InvalidLoginInput   
```ruby
package TestPageCases;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.Methods;
import PageObjectsAndMethods.logInValidate;

public class InvalidLoginInput extends BrowserSetup{
	logInValidate logininputs = new logInValidate();
	Methods methods = new Methods();
	
	@Test(description = "Login User with invalid email and Invalid password. Verifying the warning/error message.")
	public void invalidInputresponse() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		//for InvalidEmail
		logininputs.LoginWithInvalidEmail();
		Thread.sleep(2000);
		//screenshot
		//for Invalid Pass
		logininputs.LoginWithInvalidPass();
		methods.WaitElementVisible(logininputs.InvalidMessage);
		assertEquals(methods.getText(logininputs.InvalidMessage), logininputs.invalidloginmessage);
		//screenshot
		System.out.println("Warning/Error message Validation: "+methods.getText(logininputs.InvalidMessage));
		Thread.sleep(2000);
	}

}
```
Execution Result Image:   
![Invalidtest result](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/8e96f780-5f89-4ac5-8158-2617f0998741)

For testSuite Run:  [Run as TestNG]  
PackageName: TestPageCases  
CLassName: ValidLoginInput  
```ruby
package TestPageCases;

import org.testng.annotations.Test;

import AutomationBrowser.BrowserSetup;
import PageObjectsAndMethods.logInValidate;
import PageObjectsAndMethods.Methods;

public class ValidLoginInput extends BrowserSetup {
	logInValidate logininputs = new logInValidate();
	Methods methods = new Methods();
	@Test(description = "Scenario 6: Login User with the correct email and password. Verifying All featured product are visible")
	public void validLoginInput() throws InterruptedException{
		getDriver().get("https://automationexercise.com/");
		Thread.sleep(2000);
		logininputs.validInput();
		Thread.sleep(2000);
		methods.WaitElementVisible(logininputs.FeaturedProduct);
		//Screenshot
		methods.ScrollMiddle();
		methods.ScrollDown();
		//screenshot
		Thread.sleep(2000);
		methods.ScrollUp();
		Thread.sleep(2000);
	}

}
```
Execution Result Image:  
![Valid Test result](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/db1a3288-863c-4953-afa8-ed5e813eb760)

### Next Targeted scenario(7)
7. Search Product. You can choose your desired product. Verify 'SEARCHED
PRODUCTS' is visible.  
Overview of the selecting path of the page image:  
![searchInspect](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/fda6070c-d7bb-4595-a0c7-f50554113a67)

To replicate those scenario:
Here, I build a class that has the xpaths required to produce scenarios, and the searchProduct method contain the procedure of search of the desire product.
Then, I create another class under a separate package in which I use that page object and using assertion methods like assertEqual to verify the search product matched with search result, that generates test scenarios 7.  


Method:  
PackageName: PageObjectsAndMethods  
CLassName: ProductPage    
```ruby
package PageObjectsAndMethods;

import org.openqa.selenium.By;

public class ProductPage extends Methods{
	public By ProductOption =By.xpath("//a[@href='/products']");
	public By SearchBar = By.xpath("//input[@id='search_product']");
	public By clickSearch =By.xpath("//button[@id='submit_search']");
	public By waitSearchElement = By.xpath("(//p[contains(text(),'Madame Top For Women')])[1]");
	public String SearchProduct ="Madame Top For Women";
	public void searchProduct() throws InterruptedException{
		clickElement(ProductOption);
		WaitElementVisible(SearchBar);
		ScrollMiddle();
		FieldValue(SearchBar, "Madame");
		Thread.sleep(2000);
		clickElement(clickSearch);
		//WaitElementVisible(waitSearchElement);
		//ScrollDown();
	}
}
```
For testSuite Run:  [Run as TestNG]  
PackageName: TestPageCases  
CLassName: VerifySearchProduct    
```ruby
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

```
Execution Result Image:  
![S7 testresult](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/a8b82dab-9a3c-4bf5-8f00-c4244a991712)


### Next Targeted scenario(8-12)
8. Add Products to Cart.  
9. Add quantity as negative such as (-2) for the product and click on Add to Cart button and Continue Shopping button.    
10. Verify Price, negative Quantity and Negative Total.  
11. Add the same Product to the cart, add a quantity of 10 for the product, and click on the Add to Cart button and Continue Shopping button.  
12. Verify Price, Quantity and Total.  
Overview of the selecting path of the page image:  
![Addqua](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/41906a48-f56c-42a6-83f3-3921300c7292)


To replicate those scenario:
Here, I build a class that has the xpaths required to produce scenarios, and the Add Product to Cart (negative quantity, positive quantity) method contains the procedure for adding the product to the cart.
Then, I create another two classes under a separate package in which I use those page objects for adding negative or positive product quantity to the cart and using assertion methods like assertEqual to verify the product price, quantity, and total to match with the correct desire form, which generates test scenarios 8, 9, 10, 11, and 12. 
Method:  
PackageName: PageObjectsAndMethods  
CLassName: ProductPage    
```ruby
package PageObjectsAndMethods;

import org.openqa.selenium.By;

public class AddToCart extends Methods {
	public By ProductImage = By.xpath("//img[@alt='ecommerce website products']");
	public By ViewProduct =By.xpath("//a[normalize-space()='View Product']");
	public By ProductPage = By.xpath("//h2[normalize-space()='Madame Top For Women']");
	public By Productquantityfield =By.xpath("//input[@id='quantity']");
	public By AddToCartButton =By.xpath("//button[normalize-space()='Add to cart']");
	public By CountinueShoppingButton = By.xpath("//button[normalize-space()='Continue Shopping']");
	public By CartOption = By.xpath("(//i[@class='fa fa-shopping-cart'])[1]");
	public By CartProduct =By.xpath("//a[normalize-space()='Madame Top For Women']");
	public By CartProductQuantity =By.xpath("//button[normalize-space()='-2']");
	public By CartProductPrice =By.xpath("//p[@class='cart_total_price']");
	public String NegativeQuantity ="-2";
	public By SameProductRedirectToProductPage = By.xpath("//a[normalize-space()='Madame Top For Women']");
	public By positiveProductQuantity =By.xpath("//button[normalize-space()='8']");
	public By postiveProductQuantityPrice = By.xpath("//p[@class='cart_total_price']");
	public String AfterAddProductQuantity ="8";


	public void NegativeProductQuantity() throws InterruptedException{
		WaitElementVisible(ProductImage);
		Hover(ProductImage);
		//screenshot
		Thread.sleep(2000);
		clickElement(ViewProduct);
		WaitElementVisible(ProductPage);
		clickElement(Productquantityfield);
		clearFieldAndInput("-2");
		Thread.sleep(2000);
		//screenshot
		clickElement(AddToCartButton);
		WaitElementVisible(CountinueShoppingButton);
		Thread.sleep(2000);
		clickElement(CountinueShoppingButton);
		Thread.sleep(2000);
	}
	public void AddProductAfterNegativeProductQuantity() throws InterruptedException{
		clickElement(SameProductRedirectToProductPage);
		WaitElementVisible(ProductPage);
		clickElement(Productquantityfield);
		clearFieldAndInput("10");
		Thread.sleep(2000);
		//screenshot
		clickElement(AddToCartButton);
		WaitElementVisible(CountinueShoppingButton);
		Thread.sleep(2000);
		clickElement(CountinueShoppingButton);
		Thread.sleep(2000);
	}
	
	public void CartPage() throws InterruptedException{
		clickElement(CartOption);
		WaitElementVisible(CartProduct);
		ScrollMiddle();
		Thread.sleep(2000);
	}

}

```
For testSuite Run:  [Run as TestNG]  
PackageName: TestPageCases  
CLassName: VerifySearchProduct    
```ruby
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

```
Execution Result Image:  
![Test result 8,9,10,11,12](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/cb296fe8-7119-4959-b5d5-9db15c6a37fe)

### Next Targeted scenario(13-18)
13. Verify Proceed to Checkout Button is not disabled and click on the button  
14. Verify the details of DELIVERY ADDRESS and BILLING ADDRESS are the same.  
15. Place an Order and fill up Payment details. You can put dummy card information.  
16. Download the invoice.  
17. Verify the invoice.txt file exists in the project, verify the text in the file and after that remove the text file from the project.  
18. Log out from the website.  

To replicate those scenarios, I have to login to the page. And we can repeat 8, 9, 10, 11, and 12 steps and then replicate targeted scenarios (13, 14, 15, 16, 17, and 18).  
Overview of the selecting path of the page image:  
![mm1](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/592ed22c-d75c-4d4c-a16c-b0199d21e6d2)  
![mm2](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/adb35c10-3407-47a9-93d7-af3db965c743)  
![mm3](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/9a7237dc-e786-4f6e-88e8-462234b8266a)  
![mm4](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/c153a3de-fe11-4e97-a1e7-91d8ae6c5e4f)  

Used Methods:
"LoginToShoppingCart" method navigates to a website, performs login actions, searches for a product, adds it to the cart (including negative quantity scenarios), takes a screenshot, and more.  
"VerifyProcedTocheckout" method verifies the behavior of the "Proceed to Checkout" button.
"AdressVerificationAndPlaceorder" method verifies the details of the delivery address and billing address.
"PaymentToConfirmOrderAndDownloadInvoice" method simulates the process of placing an order and filling out payment details.
"InvoiceVerification method verifies" the downloading of an invoice, checks the downloaded text, and logs out.

PackageName: PageObjectsAndMethods  
CLassName: ProceedToAddressDetailsPage    
```ruby
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
		//screenshot
		clickElement(ProceedToCheckoutButton);
		WaitElementVisible(AddressPage);
	}
	public void AddressDetail() throws InterruptedException{
		assertEquals(getText(DeliveryAddress), getText(BillingAddress));
		//screenshot
		Thread.sleep(2000);
		clickElement(PlaceOrderButton);
		WaitElementVisible(PaymentPage);
		Thread.sleep(2000);
	}

}
```
PackageName: PageObjectsAndMethods  
CLassName: PaymentToDownloadInvoiceObjects    
```ruby
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
		//screenshot
		clickElement(PayAndConfirmOrderButton);
		WaitElementVisible(waitOrderPlacePage);
	}
	public void DownloadInvoiceAndLogout() throws InterruptedException{
		assertEquals(getText(OrderConfirmationMessage), orderconfirmationmesaage);
		clickElement(DownloadInvoiceButton);
		Thread.sleep(2000);
		//screenshot
		clickElement(Logout);
		WaitElementVisible(WaitSignoutLogInPage);
		Thread.sleep(2000);
	}
}

```

For testSuite Run:  [Run as TestNG]  
PackageName: TestPageCases  
CLassName: VerifyProceedToCheckoutButtonAndAddressDetails      
```ruby
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
		methods.WaitElementVisible(logininputs.FeaturedProduct);
		Thread.sleep(2000);
		product.searchProduct();
		cartproductinfo.NegativeProductQuantity();
		cartproductinfo.CartPage();
		Thread.sleep(2000);
		cartproductinfo.AddProductAfterNegativeProductQuantity();
		cartproductinfo.CartPage();
		Thread.sleep(2000);
		//screenshot
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
```
Execution Result Image:  
![8-18 result](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/41cbf988-4e5f-4f1f-adb6-08f90aa51d66)


## Bonus section
Bonus task:  
Product Name: Blue Top  
Category: Women > Tops  
Price: Rs. 500  
Create an Array/Object/ JSON file using the given details. Then, Search with the product name and verify the details are equal to the created JSON/Array/Object file.  
Overview of the selecting path of the page image:  
![Bonus](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/78f919ab-fdaa-44b2-acab-bc49c75dad2b)

Method:   
PackageName: PageObjectsAndMethods  
CLassName:   BonusTaskObjects

```ruby
package PageObjectsAndMethods;

import org.openqa.selenium.By;

public class BonusTaskObjects extends Methods {
	public By Category =By.xpath("//h2[normalize-space()='Category']");
	public By WomenCategory =By.xpath("//a[normalize-space()='Women']");
	public By Tops = By.xpath("//a[normalize-space()='Tops']");
	public By WaitWomenTopsProduct = By.xpath("//h2[normalize-space()='Women - Tops Products']");
	public By BlueTop = By.xpath("(//p[contains(text(),'Blue Top')])[1]");
	public By BlueTopPrice = By.xpath("(//h2[contains(text(),'Rs. 500')])[1]");
	public By ProductButton = By.xpath("//i[@class='material-icons card_travel']");
	public By SearchBar = By.xpath("//input[@id='search_product']");
	public By ClickSearch = By.xpath("//button[@id='submit_search']");
	public By BlueTopSearch = By.xpath("(//p[contains(text(),'Blue Top')])[1]");
	public By BlueTopSearchPrice = By.xpath("(//h2[contains(text(),'Rs. 500')])[1]");
	
	public void ManualAndAutoSearchProduct() throws InterruptedException{
		WaitElementVisible(Category);
		ScrollDown();
		clickElement(WomenCategory);
		Thread.sleep(3000);
		WaitElementVisible(Tops);
		clickElement(Tops);
		WaitElementVisible(WaitWomenTopsProduct);
		Thread.sleep(2000);
		Hover(BlueTop);
		Thread.sleep(2000);
		takeScreenshot("Blue Top");
		clickElement(ProductButton);
		WaitElementVisible(SearchBar);
		FieldValue(SearchBar, "Blue Top");
		clickElement(ClickSearch);
		WaitElementVisible(BlueTopSearch);
		Hover(BlueTop);

    }

}
```
For testSuite Run:  
PackageName: TestPageCases  
CLassName: BonusTask  
```ruby
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
```
Execution Result Image:  
![bonusResult](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/b1360659-1551-4fd9-8a63-cf33dc693cb9)

## Automation of Scenario 1 to 18 Togather

To run the whole test scenario togather:
By convert the whole testPageCase to convert Test run to TestNG(convert TESTNG)
-instruction image  
![convertTestNg](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/5b6d428f-7af0-4a30-9080-2bc8f05240ce)
-After clicking Convert to TestNG  click on finish option  
![Convert to testNg](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/b073845a-4f49-4e07-bede-0d3670dcb186)


-Now you can ovserve a Testng.xml file under pom.xml file and run that file to see whole testing procedure togather
![345](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/c19256ff-f71b-42c3-a30b-b7796d2a2348)

Test Run Result:  
![FullTestResult](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/85e52d07-044a-45a2-a7c5-c40e27d22b3e)



## Allure Report Creation

To create an allure report, 
first set dependency in the pom.xml file.
<dependency>
		<groupId>io.qameta.allure</groupId>
		<artifactId>allure-testng</artifactId>
		<version>2.19.0</version>
</dependency>
2. then run the testing.xml file 
3. then refresh the whole package and see a "allure-results" file created under Maven Dependencies  
-after runing the testng.xml file and refresh the whole package allure reasult appear
![allureresult](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/ba3d2123-a247-4ba0-9006-e8e24d767115)

4. To get allure report open the whole package terminal
5. then write in terminal to clean previous files> ""allure generate ./allure-results --clean""  
6. then write in terminal to create allure report> ""allure open ./allure-report""
7. terminal gives us http to show us an allure report file directory
Terminal image:
![terminalimage](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/1015a95e-77e5-454d-b411-75a695cacfc0)


8. Create some methods for allure report (like allure ScreenShot) which is added already
9. method add:
```ruby
public void takeScreenshot(String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
	}
```
### Allure report file
file link: ![terminalimage](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/1015a95e-77e5-454d-b411-75a695cacfc0)

File generation instruction video: 
https://drive.google.com/file/d/19D7APR9lLQhL1vU5sN8DLG9THijCuymt/view?usp=drive_link  

report overview:  
![r1](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/b71123f6-f5e9-4e16-9784-7fe548e4200f)  

Report Graph:  
![rg1](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/f52fdf71-4286-45c4-8f3d-1cd381ea69da)  

Report Suite:  
![rtestsuites3](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/58633e44-6620-4d51-b046-c4256d752ace)  

Test execution time report:  
![allure execution time report](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/82924762-dda5-4b42-bc2a-73d0a5aa14da)

Allure Scenario test report:  
![AS1 2](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/a4d78c12-5d9a-4660-87e6-5db51fd0bb3f)
![AS 3 4](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/10620f4c-2cdf-4bbf-a830-c5d1b8946019)
![As6](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/168f69d9-4b7f-4b4f-a2b0-69e0aba1d973)
![As 7](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/b3569ff4-2254-4166-bace-73a0dd14b072)
![As 8,9,10](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/d1e7af33-b571-4162-b8e4-1aafe6d4e12f)
![As 11,12](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/1d25083e-5e79-4ca1-a63f-13e875a99cee)
![As 13](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/7a0d3351-34c7-44d9-9680-4e31ea6c32d5)
![As 14](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/b636f803-16c3-45e6-bb71-c8ff972a7c69)  
![As 15](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/fb985d1d-0a70-4aab-aa8a-6c9a38156dba)
![As 16,17,18](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/8a24e5ed-6392-4a2e-8a88-7b8f5747f6b7)


Allure Bonus task report:  
![ASBonus](https://github.com/Sakif1997/shopping-cart-automation/assets/45315685/4c8411eb-48dc-4407-ac48-18d2a1f7875c)



