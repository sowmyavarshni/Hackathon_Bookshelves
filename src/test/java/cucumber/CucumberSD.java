package cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import globalDriver.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Giftcardpage;
import pageObjects.Homepage;
import pageObjects.Searchpage;

public class CucumberSD extends DriverSetup
{
	public static WebDriver driver;
	Properties pro = new Properties();
	FileInputStream fis;
	public static Homepage hp;
	public static Searchpage sp;
	public static Giftcardpage gp;
	
	@AfterAll
	public static void close() 
	{
		driver.quit();
	}
	
	@After
	public void addSS(Scenario scenario) 
	{
		if(!scenario.isFailed()) 
		{
			byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Passed Test Screenshot");
		}
	}
	
	@Given("the user is on the shopping website")
	public void the_user_is_on_the_shopping_website() throws FileNotFoundException, IOException 
	{
	   fis = new FileInputStream((System.getProperty("user.dir")+"/configuration.properties"));
	   pro.load(fis);
	   initDriver(pro.getProperty("Browser"));
	   driver = getDriver();
	   driver .get(pro.getProperty("URL"));
	      hp = new Homepage(getDriver());
		  sp =new Searchpage(getDriver());
		  gp = new Giftcardpage(getDriver());
	}

	@When("the user Searches for Bookshelves")
	public void the_user_searches_for_bookshelves() 
	{
		 hp.SearchShelves();
		 assertEquals(sp.redirected(),true);
	}

	@When("selects bookshelf type and Max price to {int}")
	public void selects_bookshelf_type_and_max_price_to(Integer int1) throws InterruptedException 
	{
		 sp.closePopup();
		 sp.MoveSlider();
		 assertEquals(sp.slider_moved(),true);
	}

	@When("excludes out of stock and sorts by High to Low")
	public void excludes_out_of_stock_and_sorts_by_high_to_low() throws InterruptedException 
	{
		 sp.exclude_sort();
		 assertTrue(true);
	}

	@Then("the user should see the top three items with their details")
	public void the_user_should_see_the_top_three_items_with_their_details() 
	{
		sp.prntNames();
		assertTrue(true);
	}

	@When("the user navigates to the Living section and selects Seating & Chairs")
	public void the_user_navigates_to_the_living_section_and_selects_seating_chairs() 
	{
		sp.gotoLiving();
		assertTrue(true);
	}

	@Then("the user should see all available items under Seating & Chairs")
	public void the_user_should_see_all_available_items_under_seating_chairs() throws InterruptedException 
	{
		sp.prntSubitems();
		assertEquals(sp.list_empty(),false);
	}

	@When("the user clicks on GiftCards Section")
	public void the_user_clicks_on_gift_cards_section() throws InterruptedException 
	{
		 sp.gotoGiftCrds();
	     assertEquals(sp.getGiftCrdTitle(),"Gift Cards"); 
	}

	@When("the user chooses Anniversary and Enters Gift Card Amount")
	public void the_user_chooses_birthday_Anniversary_and_enters_gift_card_amount() throws InterruptedException 
	{
		 gp.putAmtandProceed(); 
		 assertEquals(gp.amtEntered(),true); 
	}

	@When("user fills the form with invalid data")
	public void user_fills_the_form_with_invalid_data() throws IOException 
	{
	    gp.fill_form_invalid_Manual();
	    assertTrue(true);
	}

	@Then("the user captures error message")
	public void the_user_captures_error_message() 
	{
		assertEquals(gp.empty_Error(),false); 
	}

	@When("user fills the form with valid data")
	public void user_fills_the_form_with_valid_data() throws IOException 
	{
		gp.fill_form_valid_Manual();
	    assertTrue(true);
	}

	@Then("user validates the entered data")
	public void user_validates_the_entered_data() throws InterruptedException 
	{
		assertEquals(gp.chk_details(), true);
	}
	
}
