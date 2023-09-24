package PageObjectsAndMethods;

import org.openqa.selenium.By;

public class LinkToHomePage extends Methods{
	public By Homepage =By.xpath("//title[normalize-space()='Automation Exercise']");
	public String HomeTitle = "Automation Exercise";
}
