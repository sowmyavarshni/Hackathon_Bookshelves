package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage 
{
    WebDriver driver;
	
	public Homepage(WebDriver driver)
	{
		  this.driver = driver;
		  PageFactory.initElements(driver, this);
	}

	   @FindBy(xpath = "//*[@id='search']") WebElement searchbar;
	   @FindBy(xpath = "//span[@class='search-icon icofont-search']") WebElement searchbtn;
	   
	   
	   public void SearchShelves() 
	   {
		  searchbar.sendKeys("Bookshelves");
		  searchbtn.click();
	   }
}
