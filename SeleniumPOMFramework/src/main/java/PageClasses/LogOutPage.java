package PageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMenuClass;

public class LogOutPage extends PageBaseClass {
	public TopMenuClass topmenu;
	public LogOutPage(){
		
			topmenu=PageFactory.initElements(driver,TopMenuClass.class);
		
	}

	@FindBy(xpath="//*[@id='wrapper']/div[4]/a")
	public WebElement loginagain;
	
	public PortfolioLoginPage LoginAgain(){
		logger.log(Status.INFO, "Clicking the login again link");
		try{loginagain.click();
		reportPass("Login again successfully clicked");
		}
		catch(Exception e){
			reportFail(e.getMessage());
			
		}return PageFactory.initElements(driver,PortfolioLoginPage.class);
	}
}
