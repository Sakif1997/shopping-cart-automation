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
