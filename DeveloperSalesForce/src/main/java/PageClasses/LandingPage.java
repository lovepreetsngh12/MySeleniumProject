package PageClasses;



import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClasses.PageBaseClass;

public class LandingPage extends PageBaseClass {
		
		@FindBy(xpath="//*[@class='btn btn-success']")
		public WebElement SignUpbtn;
		
		@FindBy(xpath="//*[@id='email']")
		public WebElement emailid;
			
		@FindBy(xpath="//*[@id='deorg_form']/div[3]/div")
		public WebElement errormsg;

		
		@FindBy(xpath="//*[@id='country']")
		public WebElement countryname;
			
		
		@FindBy(xpath="//*[@class='login_link']/a")
		public WebElement Loginbtn;
	
		public void ClickOnSignUp(){
			SignUpbtn.click();
		}
		
		public void EnterEmailid(String em){
			explicitWait(emailid);
			emailid.sendKeys(em);
			emailid.sendKeys(Keys.TAB);
			
			
		}
		
		public void GetErrorMsg(){
			explicitWait(errormsg);
			String error= errormsg.getText();
			System.out.println(error);
		}
		
		public void ChooseCountry(String cntname){
			explicitWait(countryname);
			
			selectDropDownValue(countryname,cntname);
			waitforspecifictime(4000);
			
		}
	
		public LoginPage ClickOnLoginBtn(){
			Loginbtn.click();			
			return PageFactory.initElements(driver, LoginPage.class);
		}

}
