package TestCases;


import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMenuClass;
import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPortfolioPage;
import PageClasses.PortfolioLoginPage;
import Utilities.ConstantValue;
import Utilities.TestDataProvider;

public class MyPortfolioTest extends PageBaseClass {
	
	LandingPage landingpage;
	MoneyPage moneypage;
PortfolioLoginPage portfoliologinpage;
MyPortfolioPage myportfoliopage;
TopMenuClass topmenu;	
	@Test(dataProvider="getOpenPortfoliotestdata",groups={"Regression","LoginTest"})
	public void openPortfolio(Hashtable<String,String> dataTabe){
		logger= report.createTest("LoginRediffMail Test:"+dataTabe.get("Comments"));
	PageBaseClass  pagebaseclass= new PageBaseClass();
	pagebaseclass.invokeBrowser("Chrome");
	landingpage=pagebaseclass.OpenApplication();
		moneypage=landingpage.clickMoneyLink();
	portfoliologinpage=moneypage.clickSignInLink();
	myportfoliopage =portfoliologinpage.doLogin(dataTabe.get("Username"),dataTabe.get("Password"));
	myportfoliopage.getTitle("Rediff Moneywiz | My Portfolio(s)");
	topmenu =myportfoliopage.getTopMenu(); 
	topmenu.SignOutApplication();
	
	}
	
	@Test(dataProvider="getOpenPortfoliotest",groups={"Regression","LoginTest"})
	public void VerifyPortfolio(Hashtable<String,String> dataTabe){
		logger= report.createTest("LoginRediffMail Test:"+dataTabe.get("Comments"));
	PageBaseClass  pagebaseclass= new PageBaseClass();
	pagebaseclass.invokeBrowser("Chrome");
	landingpage=pagebaseclass.OpenApplication();
		moneypage=landingpage.clickMoneyLink();
	portfoliologinpage=moneypage.clickSignInLink();
	portfoliologinpage.enterUser(dataTabe.get("Username"));
	portfoliologinpage.submitUserName();
	portfoliologinpage.verifyPasswordField();
	
	}
	
	
	
	@Test(dataProvider="pnames",groups={"Regression","AddPortfolio"})
	public void addportfolio(Hashtable<String,String> tal){
		logger= report.createTest("LoginRediffMail Test:"+tal.get("Comments"));
		PageBaseClass  pagebaseclass= new PageBaseClass();
		pagebaseclass.invokeBrowser("Chrome");
		landingpage=pagebaseclass.OpenApplication();
			moneypage=landingpage.clickMoneyLink();
		portfoliologinpage=moneypage.clickSignInLink();
		myportfoliopage =portfoliologinpage.doLogin(ConstantValue.username,ConstantValue.password);
		myportfoliopage.getTitle("Rediff Moneywiz | My Portfolio(s)");
		myportfoliopage.ClickCreateportfolio();
		waitForPageLoad();
		myportfoliopage.enterportfolioname(tal.get("PortfolioName"));
		myportfoliopage=myportfoliopage.clickOncreate();
		waitForPageLoad();
		myportfoliopage.showDropDown(tal.get("PortfolioName"));
		report.flush();
		
	}
	
	
	@Test(dataProvider="pnames",groups={"Regression","DeletePortfolio"})
	public void deleteportfolio(Hashtable<String,String> tal){
		logger= report.createTest("LoginRediffMail Test:"+tal.get("Comments"));
		PageBaseClass  pagebaseclass= new PageBaseClass();
		pagebaseclass.invokeBrowser("Chrome");
		landingpage=pagebaseclass.OpenApplication();
			moneypage=landingpage.clickMoneyLink();
		portfoliologinpage=moneypage.clickSignInLink();
		myportfoliopage =portfoliologinpage.doLogin(ConstantValue.username,ConstantValue.password);
		myportfoliopage.getTitle("Rediff Moneywiz | My Portfolio(s)");
		myportfoliopage = myportfoliopage.selectPortfolio(tal.get("PortfolioName"));
		myportfoliopage = myportfoliopage.deletePortFolio();
		waitForPageLoad();
myportfoliopage.isPorfolioDeleted(tal.get("PortfolioName"));
report.flush();
	
	
	}	
	
	
	@DataProvider
	public Object[][] getOpenPortfoliotestdata(){
		return TestDataProvider.getTestData("TestData_Testmanagement.xlsx", "LoginTestCases", "Rediff");
		
	}
	
	@DataProvider
	public Object[][] getOpenPortfoliotest(){
		return TestDataProvider.getTestData("TestData_Testmanagement.xlsx", "LoginTestCases", "Rediff1");
		
	}
	@DataProvider
	public Object[][] pnames(){
		return TestDataProvider.getTestData("TestData_Testmanagement.xlsx", "LoginTestCases", "Rediff2");
		
	}
}
