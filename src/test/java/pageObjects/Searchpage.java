package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Searchpage 
{
    WebDriver driver;
    
	public Searchpage(WebDriver driver)
	{
		  this.driver = driver;
		  PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[normalize-space()='Price']") WebElement price_btn;
	@FindBy(xpath="//*[@class='noUi-handle noUi-handle-upper']") WebElement slider;
	@FindBy(id="filters_availability_In_Stock_Only") WebElement Exclude_box;
	@FindBy(xpath="//li[text()='Price: High to Low']") WebElement HTL;
	@FindBy(xpath="//*[normalize-space()='Recommended']") WebElement sortby;
	@FindBy(xpath="//*[normalize-space()='Close']") WebElement close_btn;
	@FindBy(xpath="//span[normalize-space()='Living']") WebElement living_btn;
	@FindBy(xpath="//div[@class='product-title product-title-sofa-mattresses']/span") List<WebElement> product_names;
	@FindBy(xpath="//div[@class='price-number']/span") List<WebElement> product_prices;
	@FindBy(xpath="//*[normalize-space()='Gift Cards' and @class='featuredLinksBar__link']") WebElement giftcard_btn;
	@FindBy(xpath="//*[@id='topnav_wrapper']/ul/li[3]/div/div/ul/li[1]/ul/li") List<WebElement> subitems;
	@FindBy(xpath="//span[@class='key']") WebElement title_chk;
	@FindBy(xpath="//*[@class='selrange-filter']") WebElement slider_chk;
	@FindBy(xpath="//*[normalize-space()='Category']") WebElement category_btn;
	@FindBy(xpath="//*[@id='filters_primary_category_Kids_Bookshelves']") WebElement category_optn;
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/h1") WebElement giftcard_title;
	
	public boolean slider_moved() 
	{
		return slider_chk.isDisplayed();
	}
	
	public boolean list_empty() 
	{
		if(product_names.size() > 3 && product_prices.size() > 3 && subitems.size() > 1) 
		{
			return false;
		}
		return true;
	}
	
	public boolean redirected() 
	{
	  if(title_chk.getText().contains("Book")) 
	  {
		  return true;
	  }
	   return false;	
	}
	
	public String getGiftCrdTitle() 
	{
		String gcstring = giftcard_title.getText().trim();
		return gcstring;
	}
	
	public void MoveSlider() throws InterruptedException 
	{		  
		  Actions act = new Actions(driver);
		  act.moveToElement(category_btn).perform();
		  category_optn.click();
		  Thread.sleep(2700);
		  act.moveToElement(price_btn).perform(); 
		  act.clickAndHold(slider).moveByOffset(-211, 0).release().build().perform();
		  Thread.sleep(2700);
		  scroll_up();
	}
	
	public void closePopup() throws InterruptedException 
	{	
		Thread.sleep(5000);
		close_btn.click();
	}
	
	public void exclude_sort() throws InterruptedException 
	{
		Exclude_box.click();
		Thread.sleep(2700); 
		Actions act = new Actions(driver);
		act.moveToElement(sortby).perform();
		HTL.click();
		Thread.sleep(2700);
		scroll_up();
	}
	
	public void prntNames() 
	{
		System.out.println();
		System.out.println("Product Details :");
		System.out.println();
		for(int i=0; i<=2; i++) 
		{
			String pNames = product_names.get(i).getText();
			String pPrice = product_prices.get(i).getText();
			System.out.println(pNames +" | " +pPrice);
		}
		
		living_btn.click();
	}
	
	public void gotoGiftCrds() throws InterruptedException 
	{
		scroll_down();
		scroll_up();
		giftcard_btn.click();
		Thread.sleep(10000);
	}
	
	public void scroll_up() 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0);");
	}
	
	public void scroll_down() 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,250);");
	}
	
	public void gotoLiving() 
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(living_btn).perform();	
	}
	
	public  void prntSubitems() throws InterruptedException
	{
		Thread.sleep(8000);
		System.out.println("Sub Menu Items :");
		System.out.println();
		List<WebElement> list = driver.findElements(By.xpath("//*[@id='topnav_wrapper']/ul/li[3]/div/div/ul/li[1]/ul/li"));
        for (WebElement item : list) 
        {
            String name = item.getText();
            System.out.println(name);
        }
	}	
}
