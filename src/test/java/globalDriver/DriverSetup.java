package globalDriver;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup 
{
    static WebDriver driver;
    public Logger logger = LogManager.getLogger(this.getClass());
	public void initDriver(String br) 
	{
		 if(br.equals("Chrome"))
		  {
			  driver = new ChromeDriver();
		  }
		  else
		  {
			  driver = new EdgeDriver();
		  }
		 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().window().maximize();
	}
	 
     public WebDriver getDriver() 
     {
     	return driver;
     }
}
