package BaseClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import PageClasses.LandingPage;


public class PageBaseClass {
	public static WebDriver driver;
	public  Properties prop;
	public String browser = null;
	
	
	public void InvokeBrowser(String browserNameKey) {
		if (prop == null) {
			prop = new Properties();
			FileInputStream file;
			try {
				file = new FileInputStream(System.getProperty("user.dir")
						+ "\\Resources.ConfigFiles\\GlobalVariable.properties");
				prop.load(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//reportFail(e.getMessage());
				e.printStackTrace();
			}

		}

		try {
			if (prop.getProperty(browserNameKey).equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\Resources.Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}

			else if (prop.getProperty(browserNameKey).equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\Resources.Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

			}

		} catch (Exception e) {
		}
		
		

}
	public LandingPage OpenWebSite(String websiteURLKey){
		driver.get(prop.getProperty(websiteURLKey));
		return PageFactory.initElements(driver, LandingPage.class);
	}
	
	public void takeScreenShot() throws IOException {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(System.getProperty("user.dir") + "\\Resources.Screenshots\\"
				+ UtilityClasses.DateUtils.getTimeStamp() + ".png");
		FileUtils.copyFile(sourceFile, destinationFile);
	System.out.println("Screenshot Captured");

	}
	
	public void waitforspecifictime(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void explicitWait(WebElement we) {
		
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(we));
		
	
	}
	public void selectDropDownValue(WebElement webElement, String value) {
		Select select = new Select(webElement);
			select.selectByVisibleText(value);
	}

	
	public void QuitBrowser(){
		driver.quit();
	}
}