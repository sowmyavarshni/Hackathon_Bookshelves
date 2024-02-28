package testNG;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class XtentReportAdapter implements ITestListener
{
	public ExtentSparkReporter sparkReporter;  // UI of the report
	public ExtentReports extent;  //populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test methods

	@Override
	public void onStart(ITestContext context) 
	{
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/XtentReportTestNG.html");
		sparkReporter.config().setDocumentTitle("Hackathon Project Report"); 
		sparkReporter.config().setReportName("Urban Ladder Report"); 
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Machine Name","HP_ELITEBOOK");
		extent.setSystemInfo("Tester","Sowmya");
		extent.setSystemInfo("OS","Windows 11");
		extent.setSystemInfo("Browser","Chrome/Edge/Firefox");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED is:" + result.getName());
		String ss_path = System.getProperty("user.dir")+"/ScreenShots/"+result.getName()+"_Passed.png";
		TakesScreenshot screenshot = (TakesScreenshot) MainTestng.drv;
	    try 
	    {
			FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), new File(ss_path));
		} 
	    catch (WebDriverException e) 
	    {
			e.printStackTrace();
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	    test.addScreenCaptureFromPath(ss_path);
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
		String ss_path = System.getProperty("user.dir")+"/ScreenShots/"+result.getName()+"_Failed.png";
		TakesScreenshot screenshot = (TakesScreenshot) MainTestng.drv;
	    try 
	    {
			FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), new File(ss_path));
		} 
	    catch (WebDriverException e) 
	    {
			e.printStackTrace();
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	    test.addScreenCaptureFromPath(ss_path);
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
}
