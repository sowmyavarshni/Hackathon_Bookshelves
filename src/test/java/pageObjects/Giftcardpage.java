package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Giftcardpage 
{
    WebDriver driver;
    String excelPath = System.getProperty("user.dir")+"/Excel/GiftCardDetails.xlsx", amt="55000",rname,remail,rmob,cname,cemail,cmob,cadd,zip;
    String[] InvalidDetails = {"John Wick","johnwick@gmail.com","9175854211","Harry Potter","harrypotter","9175854233","United Kingdom","600130"},ValidDetails = {"John Wick","johnwick@gmail.com","9175854211","Harry Potter","harrypotter@gmail.com","9175854233","United Kingdom","600130"},
    		 hDetails= {"Recipient Name","Recipient Email","Recipient Mob","Customer name","Customer email","Customer Mob","Customer Address","Zip"};
	
	public Giftcardpage(WebDriver driver)
	{
      this.driver = driver;
	  PageFactory.initElements(driver, this);
	}

    @FindBy(xpath = "//*[@id='app-container']/div/main/section/section[1]/ul/li[3]/div/h3") WebElement birthanniver_btn;
	@FindBy(xpath = "//*[@name='amount_select']") WebElement amt_txtbx;
	@FindBy(xpath = "//*[normalize-space()='Next']") WebElement next_btn;
	@FindBy(xpath = "//*[@name='recipient_name']") WebElement rname_txtbx;
	@FindBy(xpath = "//*[@name='recipient_email']") WebElement remail_txtbx;
	@FindBy(xpath = "//*[@name='recipient_mobile_number']") WebElement rmob_txtbx;
	@FindBy(xpath = "//*[@name='customer_name']") WebElement cname_txtbx;
	@FindBy(xpath = "//*[@name='customer_email']") WebElement cemail_txtbx;
	@FindBy(xpath = "//*[@name='customer_mobile_number']") WebElement cmob_txtbx;
	@FindBy(xpath = "//*[@name='customer_address']") WebElement cadd_txtbx;
	@FindBy(xpath = "//*[@name='zip']") WebElement zip_txtbx;
	@FindBy(xpath = "//button[normalize-space()='Confirm']") WebElement confirm_btn;
	@FindBy(xpath = "//*[@name='customer_email']") WebElement err_msg;
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[1]/div[2]/div[1]") WebElement rname_chk;
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[1]/div[2]/div[2]") WebElement remail_chk;
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[1]/div[2]/div[3]") WebElement rmob_chk;
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[2]/div[2]/div[1]") WebElement cname_chk;
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[2]/div[2]/div[2]") WebElement cemail_chk;
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[2]/div[2]/div[3]") WebElement cmob_chk;
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[2]/div[2]/div[4]") WebElement cadd_chk;
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[2]/div[2]/div[5]") WebElement zip_chk;
	
	
	public void fill_form_invalid_Manual() throws IOException 
	{
		getExcelData_Manual();
		rname_txtbx.sendKeys(rname);
		remail_txtbx.sendKeys(remail);
		rmob_txtbx.sendKeys(rmob);
		cname_txtbx.sendKeys(cname);
		String flseEmail = cemail.substring(0, cemail.indexOf("@"));
		cemail_txtbx.sendKeys(flseEmail);
		cmob_txtbx.sendKeys(cmob);
		cadd_txtbx.sendKeys(cadd);
		zip_txtbx.sendKeys(zip);
	}
	
	public void fill_form_valid_Manual() throws IOException 
	{
		cemail_txtbx.clear();
		cemail_txtbx.sendKeys(cemail);
	}
	
	public void getExcelData_Manual() throws IOException 
	{
		createExcel();
		FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook wbuk = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = wbuk.getSheet("DataSet1");
        rname = sheet.getRow(2).getCell(0).getStringCellValue();
        remail = sheet.getRow(2).getCell(1).getStringCellValue();
        rmob = sheet.getRow(2).getCell(2).getStringCellValue();
        cname = sheet.getRow(2).getCell(3).getStringCellValue();
        cemail = sheet.getRow(2).getCell(4).getStringCellValue();
        cmob = sheet.getRow(2).getCell(5).getStringCellValue();
        cadd = sheet.getRow(2).getCell(6).getStringCellValue();
        zip = sheet.getRow(2).getCell(7).getStringCellValue();
        wbuk.close();
	}
	public Object[][] getDataFromExcel(String type) throws IOException
	{
		createExcel();
		FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wbuk = new XSSFWorkbook(fis);
        XSSFSheet sheet = wbuk.getSheet("DataSet1");
	    Object[][] DataArray = new Object[1][8];
	    switch (type) 
	    {
        case "Invalid":
            for (int i = 0; i < 8; i++) 
            {
            	DataArray[0][i] = sheet.getRow(1).getCell(i).getStringCellValue();
            }
            break;
        case "Valid":
            for (int i = 0; i < 8; i++) 
            {
            	DataArray[0][i] = sheet.getRow(2).getCell(i).getStringCellValue();
            }
            break;
        default:
            System.out.println("Invalid value");
            break;
        }
	    wbuk.close();
	    return DataArray;
	}
	
	public void putAmtandProceed() throws InterruptedException 
	{
		birthanniver_btn.click();
		amt_txtbx.sendKeys(amt);
		next_btn.click();
		Thread.sleep(3000);
	}
	
	public void fill_form(String rn,String re,String rm,String cn,String ce,String cm,String ca,String z) throws IOException 
	{
		clear_txt();
		rname_txtbx.sendKeys(rn);
		remail_txtbx.sendKeys(re);
		rmob_txtbx.sendKeys(rm);
		cname_txtbx.sendKeys(cn);
		cemail_txtbx.sendKeys(ce);
		cmob_txtbx.sendKeys(cm);
		cadd_txtbx.sendKeys(ca);
		zip_txtbx.sendKeys(z);
	}
	
	public void clear_txt() 
	{
		rname_txtbx.clear();
		remail_txtbx.clear();
		rmob_txtbx.clear();
		cname_txtbx.clear();
		cemail_txtbx.clear();
		cmob_txtbx.clear();
		cadd_txtbx.clear();
		zip_txtbx.clear();
	}
	
	public boolean amtEntered() 
	{
		return rname_txtbx.isDisplayed();
	}
	
	public boolean empty_Error() 
	{
		String errMsg = err_msg.getAttribute("validationMessage");
		
		if(errMsg.equals("")) 
		{
			return true;
		}
		
		System.out.println();
		System.out.println("Error Message Captured : "+errMsg);
		System.out.println();
		return false;
	}
	
	public boolean chk_details() throws InterruptedException 
	{
		Thread.sleep(4000);
		confirm_btn.click();
		Thread.sleep(10000);
		boolean correct = true;
			
		 if(!rname_chk.getText().contains(ValidDetails[0])) 
		{
			correct = false;
		}
		else if(!remail_chk.getText().contains(ValidDetails[1])) 
		{			
			correct = false;
		}
        else if(!rmob_chk.getText().contains(ValidDetails[2])) 
        {
			correct = false;
		}
        else if(!cname_chk.getText().contains(ValidDetails[3])) 
        {
			correct = false;
		}
        else if(!cemail_chk.getText().contains(ValidDetails[4])) 
        {
			correct = false;
		}
        else if(!cmob_chk.getText().contains(ValidDetails[5])) 
        {
			correct = false;
		}
        else if(!cadd_chk.getText().contains(ValidDetails[6])) 
        {    
			correct = false;
		}
        
		return correct;
	}
	
	public void createExcel() throws IOException 
	{
		File file = new File(excelPath);
		if(!file.exists()) 
		{
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet shit = workbook.createSheet("DataSet1");
		    XSSFRow row1 = shit.createRow(0);
            for(int i=0; i<hDetails.length; i++) 
            {
            	row1.createCell(i).setCellValue(hDetails[i]);
            }
            XSSFRow row2 = shit.createRow(1);
            for(int i=0; i<InvalidDetails.length; i++) 
            {
            	row2.createCell(i).setCellValue(InvalidDetails[i]);
            }
            XSSFRow row3 = shit.createRow(2);
            for(int i=0; i<ValidDetails.length; i++) 
            {
            	row3.createCell(i).setCellValue(ValidDetails[i]);
            }
		    FileOutputStream fOut = new FileOutputStream(excelPath);
			workbook.write(fOut);
			workbook.close();
		}
	}
	
}
