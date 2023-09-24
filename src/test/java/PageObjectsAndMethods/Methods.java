package PageObjectsAndMethods;

import static AutomationBrowser.BrowserSetup.getDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;


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

