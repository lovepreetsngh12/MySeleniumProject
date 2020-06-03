package BaseClasses;

//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import PageClasses.LogOutPage;

public class TopMenuClass extends PageBaseClass {

	
	
	@FindBy(xpath="//*[@id='headcontent']/div[1]/ul/li[2]/a")
	public WebElement myportfoliolink;
	
	@FindBy(xpath="//*[@id='signin_info']/a")
	public WebElement signoutbutton;
	
	public LogOutPage SignOutApplication(){
		try{
			logger.log(Status.INFO, "Clicking the Signout Button");
		signoutbutton.click();
		reportPass("Signed out successfully");
		}catch(Exception e){
			reportFail(e.getMessage());
		
		}
		
		return PageFactory.initElements(driver, LogOutPage.class);
	}
	

}
