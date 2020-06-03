package BaseClasses;

import java.io.File;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.LandingPage;
import Utilities.DateUtils;
import Utilities.ExtentReportManager;
import junit.framework.Assert;

public class PageBaseClass {
	public static WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;	
	public void invokeBrowser(String browsername) {
		try{
			if (browsername.equalsIgnoreCase("chrome")) {
		}
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");

					//System.setProperty("webdriver.chrome.driver", "D:\\sem 5\\chromedriver.exe");

					driver = new ChromeDriver();
					// System.out.println(System.getProperty("user.dir"));
					driver.manage().window().maximize();
					
		}

		catch(Exception e){
		//	reportFail(e.getMessage());
			System.out.println(e.getMessage());
			
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

				
	}
	public LandingPage OpenApplication(){
		logger.log(Status.INFO, "Opening the Website");
		driver.get("https://www.rediff.com");
		logger.log(Status.PASS,"Successfully opened Rediffmail");
return PageFactory.initElements(driver,LandingPage.class);
	}
	
	public void getTitle(String expected){
		try{
		Assert.assertEquals(driver.getTitle(), expected);
		reportPass("Expected:"+expected);
		reportPass("Actual:"+driver.getTitle());
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
	}
	
	public void reportFail(String reportString){
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
		
	}
	public void reportPass(String reportString){
		logger.log(Status.PASS, reportString);
	}
	
	
	public void takeScreenShotOnFailure(){
		TakesScreenshot takeScreenShot = (TakesScreenshot)driver;
	File sourceFile=	takeScreenShot.getScreenshotAs(OutputType.FILE);
	File destinationFile = new File(System.getProperty("user.dir")+"//ScreenShots//"+DateUtils.getTimeStamp()+".png");
	try {
		FileUtils.copyFile(sourceFile, destinationFile);
		logger.addScreenCaptureFromPath(System.getProperty("user.dir")+"//ScreenShots//"+DateUtils.getTimeStamp()+".png");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}
	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void veriyElementIsDisplayed(WebElement webElement){
		try {
			if(webElement.isDisplayed()){
				reportPass("Password Box is Displayed");
			}else {
				reportFail("Password box is not appeared");
			}
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	public void acceptAlert(){
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logger.log(Status.PASS, "Page Alert Accepted");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	public void cancelAlert(){
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();;
			logger.log(Status.PASS, "Page Alert Rejected");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	public void selectDropDownValue(WebElement webElement, String value){
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			logger.log(Status.PASS, "Selectd the Value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void selectDateIncalendar(String date) {

		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date expectedDate = dateFormat.parse(date);

			String day = new SimpleDateFormat("dd").format(expectedDate);
			String month = new SimpleDateFormat("MMMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);

			String expectedMonthYear = month + " " + year;

			while (true) {
				String displayDate = driver.findElement(By.className("dpTitleText")).getText();

				if (expectedMonthYear.equals(displayDate)) {

					driver.findElement(By.xpath("//td[text()= '" + day + "']")).click();

					break;
				} else if (expectedDate.compareTo(currentDate) > 0) {
					driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[4]/button")).click();
				} else {
					driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[2]/button")).click();
				}

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	
	
	
	
	
	
	public List getAllElementsofDropDown(WebElement we){
		try{
			Select sel = new Select(we);
		List<WebElement> allelements=sel.getOptions();
		return allelements;
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
		return null;
	}

	
	@AfterMethod
	public void flushreport(){
		report.flush();
		driver.close();
	}
	
	@AfterSuite
	public void teardown(){
		driver.quit();
	}
	
	
}