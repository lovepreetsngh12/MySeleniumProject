package TestClass;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClasses.PageBaseClass;
import PageClasses.LandingPage;
import PageClasses.LoginPage;
import UtilityClasses.TestDataProvider;

public class TestOne extends PageBaseClass {
static LandingPage lp;
static LoginPage lop;
	
	@Test(dataProvider="DataRequiredForTesting")
	public void TestCaseone(Hashtable<String,String> table) throws IOException{
	
	PageBaseClass object1 = new PageBaseClass();
	object1.InvokeBrowser("browser");
	lp=object1.OpenWebSite("Url");
	
	lp.ClickOnSignUp();
	lp.EnterEmailid(table.get("email id"));
	lp.GetErrorMsg();
	lp.ChooseCountry(table.get("country"));
	lop=lp.ClickOnLoginBtn();
	lop.ClickOnCheckBox();
	object1.QuitBrowser();
	}
	@DataProvider
	public Object[][] DataRequiredForTesting(){
		return TestDataProvider.getTestData("InputData.xlsx", "RawData", "SalesForce Automation");
	}
}
