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
		takeScreenshot("Product Image");
		Thread.sleep(2000);
		clickElement(ViewProduct);
		WaitElementVisible(ProductPage);
		clickElement(Productquantityfield);
		clearFieldAndInput("-2");
		Thread.sleep(2000);
		takeScreenshot("Negative quantity");
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
		takeScreenshot("Positive Quantity");
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
