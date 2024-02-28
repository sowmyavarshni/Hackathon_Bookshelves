package testNG;
 
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import globalDriver.DriverSetup;
import pageObjects.Giftcardpage;
import pageObjects.Homepage;
import pageObjects.Searchpage;
 
public class MainTestng extends DriverSetup {
 
	static WebDriver drv;
	DriverSetup ds = new DriverSetup();
	Homepage hp;
	Searchpage sp;
	Giftcardpage gp;
 
//	private static final Logger logger = LogManager.getLogger(MainTestng.class);
 
	@BeforeClass
	@Parameters({ "Browser", "URL" })
	public void launch(String Browser, String url) {
		try {
			logger.info("******* Starting *********");
 
			ds.initDriver(Browser);
			drv = ds.getDriver();
			drv.get(url);
			hp = new Homepage(drv);
			sp = new Searchpage(drv);
			gp = new Giftcardpage(drv);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
	}
 
	@Test(priority = 0)
	public void Searching_Bookshelves() throws InterruptedException {
		try {
			logger.info("Starting test method: Searching_Bookshelves");
			hp.SearchShelves();
			assertEquals(sp.redirected(), true);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Searching_Bookshelves");
	}
 
	@Test(priority = 1, dependsOnMethods = "Searching_Bookshelves")
	public void Apply_Slider() throws InterruptedException {
		try {
			logger.info("Starting test method: Apply_Slider");
			sp.closePopup();
			sp.MoveSlider();
			assertEquals(sp.slider_moved(), true);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Apply_Slider");
	}
 
	@Test(priority = 2, dependsOnMethods = "Searching_Bookshelves")
	public void Exclude_and_Sort() throws InterruptedException {
		try {
			logger.info("Starting test method: Exclude_and_Sort");
			sp.exclude_sort();
			assertTrue(true);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Exclude_and_Sort");
	}
 
	@Test(priority = 3, dependsOnMethods = "Searching_Bookshelves")
	public void Print_Submenu_items_and_productinfo() throws InterruptedException {
		try {
			logger.info("Starting test method: Print_Submenu_items_and_productinfo");
			sp.gotoLiving();
			sp.prntSubitems();
			sp.prntNames();
			assertEquals(sp.list_empty(), false);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Print_Submenu_items_and_productinfo");
	}
 
	@Test(priority = 4)
	public void Go_To_GiftCardsSection() throws InterruptedException {
		try {
			logger.info("Starting test method: Go_To_GiftCardsSection");
			sp.gotoGiftCrds();
			assertEquals(sp.getGiftCrdTitle(), "Gift Cards");
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Go_To_GiftCardsSection");
	}
 
	@Test(priority = 5, dependsOnMethods = "Go_To_GiftCardsSection")
	public void Enter_Amount() throws InterruptedException {
		try {
			logger.info("Starting test method: Enter_Amount");
			gp.putAmtandProceed();
			assertEquals(gp.amtEntered(), true);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Enter_Amount");
	}
 
	@Test(priority = 6, dependsOnMethods = "Enter_Amount", dataProvider = "IncorrectDataProvider")
	public void Fill_Form_with_Invalid_info(String rn, String re, String rm, String cn, String ce, String cm, String ca,
			String z) throws IOException {
		try {
			logger.info("Starting test method: Fill_Form_with_Invalid_info");
			gp.fill_form(rn, re, rm, cn, ce, cm, ca, z);
			assertTrue(true);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Fill_Form_with_Invalid_info");
	}
 
	@DataProvider
	public Object[][] IncorrectDataProvider() throws IOException {
		return gp.getDataFromExcel("Invalid");
	}
 
	@Test(priority = 7, dependsOnMethods = "Fill_Form_with_Invalid_info")
	public void Capture_ErrorMessage() throws IOException {
		try {
			logger.info("Starting test method: Capture_ErrorMessage");
			assertEquals(gp.empty_Error(), false);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Capture_ErrorMessage");
	}
 
	@Test(priority = 8, dependsOnMethods = "Capture_ErrorMessage", dataProvider = "CorrectDataProvider")
	public void Fill_Form_with_Valid_data(String rn, String re, String rm, String cn, String ce, String cm, String ca,
			String z) throws IOException {
		try {
			logger.info("Starting test method: Fill_Form_with_Valid_data");
			gp.fill_form(rn, re, rm, cn, ce, cm, ca, z);
			assertTrue(true);
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("Completed test method: Fill_Form_with_Valid_data");
	}
 
	@Test(priority = 9, dependsOnMethods = "Fill_Form_with_Valid_data")
	public void Validate_Entered_Details() throws IOException, InterruptedException {
		try 
		{
			logger.info("Starting test method: Validate_Entered_Details");
			assertEquals(gp.chk_details(), true);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			logger.error("Test failed");
			Assert.fail();
		}
			
		
		logger.info("Completed test method: Validate_Entered_Details");
	}
 
	@DataProvider
	public Object[][] CorrectDataProvider() throws IOException {
		return gp.getDataFromExcel("Valid");
	}
 
	@AfterClass
	public void close() {
		try {
			drv.quit();
		} catch (Exception e) {
			logger.error("Failed to close the browser");
		}
	}
}