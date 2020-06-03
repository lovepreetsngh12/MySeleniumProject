package PageClasses;

//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMenuClass;

public class LandingPage extends PageBaseClass {
/* All Web Elements of landing pages and operations*/
	//public ExtentTest logger;
	public TopMenuClass topmenu;
	public LandingPage(){
		topmenu=PageFactory.initElements(driver,TopMenuClass.class);
	}

	
	@FindBy(xpath="/html/body/div[2]/div/div[2]/a[2]")
	public WebElement moneyLink;
	public MoneyPage clickMoneyLink(){
		logger.log(Status.INFO, "Clicking the money link present in header");
		moneyLink.click();
		logger.log(Status.PASS, "Money Link Clicked successfully");
		return PageFactory.initElements(driver, MoneyPage.class);
	}
}
