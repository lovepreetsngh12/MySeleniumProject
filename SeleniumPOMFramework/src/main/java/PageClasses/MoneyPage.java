package PageClasses;





import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMenuClass;

public class MoneyPage extends PageBaseClass {
	public TopMenuClass topmenu;
	public MoneyPage(){
		
		topmenu=PageFactory.initElements(driver,TopMenuClass.class);
	}
	
@FindBy(xpath="//*[@id='signin_info']/a[1]")
	public WebElement signInBtn;
public PortfolioLoginPage clickSignInLink(){
	logger.log(Status.INFO, "clicking the signin button");
	try{
		signInBtn.click();
		reportPass("signin button clicked successfully");
	}
	catch(Exception e){
	reportFail(e.getMessage());
	}
	return PageFactory.initElements(driver, PortfolioLoginPage.class);
}	
	
}
