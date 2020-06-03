package PageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMenuClass;
import junit.framework.Assert;

public class PortfolioLoginPage extends PageBaseClass {
	public TopMenuClass topmenu;
public	PortfolioLoginPage(){
		topmenu = PageFactory.initElements(driver, TopMenuClass.class);
	}
	
	
	@FindBy(id="useremail")
	public WebElement useremailtt;

	@FindBy(id = "emailsubmit")
	public WebElement emailSubmit_button;

	@FindBy(id = "userpass")
	public WebElement password_TextBox;

	@FindBy(id = "loginsubmit")
	public WebElement submitLogin_button;
	
	
	public MyPortfolioPage doLogin(String user,String pass){
	useremailtt.sendKeys(user);
		emailSubmit_button.click();
		waitForPageLoad();
	password_TextBox.sendKeys(pass);
		submitLogin_button.click();
		waitForPageLoad();
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String curr = driver.getTitle();
	if(curr.equals("Indian stock markets: Login to Portfolio"))
		Assert.fail("Login Failed");
		return PageFactory.initElements(driver, MyPortfolioPage.class);

	}
	
	
	public void enterUser(String userName){
		useremailtt.sendKeys(userName);
		logger.log(Status.PASS, "Entered the UserName : " + userName);
	}
	
	public void submitUserName(){
		emailSubmit_button.click();
		logger.log(Status.PASS, "Clicked the UserName Submit Buttton");
	}
	
	public void enterPassword(String password){
		password_TextBox.sendKeys(password);
		logger.log(Status.PASS, "Entered the Password : " + password);
	}
	
	public MyPortfolioPage submitLogin(){
		submitLogin_button.click();
		logger.log(Status.PASS, "Cliecked the Submit Login Button");
		
		return PageFactory.initElements(driver, MyPortfolioPage.class);
		
	}
	
	public void verifyPasswordField(){
		veriyElementIsDisplayed(password_TextBox);
	}
	

}
