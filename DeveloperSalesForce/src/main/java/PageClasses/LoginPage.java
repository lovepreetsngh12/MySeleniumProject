package PageClasses;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseClasses.PageBaseClass;

public class LoginPage extends PageBaseClass {

	
	@FindBy(xpath="//*[@id='rememberUn']")
	public WebElement checkbox;
	
	public void ClickOnCheckBox() throws IOException{
		explicitWait(checkbox);
		checkbox.click();
		takeScreenShot();
	}
}
