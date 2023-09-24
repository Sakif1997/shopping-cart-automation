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
		
	}
}
