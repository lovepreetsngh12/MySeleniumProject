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

public class MyStockTest extends PageBaseClass {
	LandingPage landingpage;
	MoneyPage moneypage;
	PortfolioLoginPage portfoliologinpage;
	MyPortfolioPage myportfoliopage;
	TopMenuClass topmenu;

	@Test(dataProvider="getStockData")
	public void AddStockTest(Hashtable<String, String> dataTable) {
		logger = report.createTest("Add Portfolio:" + dataTable.get("PortfolioName"));
		PageBaseClass pagebaseclass = new PageBaseClass();
		pagebaseclass.invokeBrowser("Chrome");
		landingpage = pagebaseclass.OpenApplication();
		moneypage = landingpage.clickMoneyLink();
		portfoliologinpage = moneypage.clickSignInLink();
		myportfoliopage = portfoliologinpage.doLogin(ConstantValue.username, ConstantValue.password);
		myportfoliopage.getTitle("Rediff Moneywiz | My Portfolio(s)");
		myportfoliopage.ClickCreateportfolio();
		waitForPageLoad();
		myportfoliopage.enterportfolioname(dataTable.get("PortfolioName"));
		myportfoliopage = myportfoliopage.clickOncreate();
		waitForPageLoad();
		myportfoliopage.showDropDown(dataTable.get("PortfolioName"));
		waitForPageLoad();
		myportfoliopage.AddStock();
		myportfoliopage.enterStockName(dataTable.get("StockName"));
		myportfoliopage.AddDate();
		selectDateIncalendar("26/09/2019");
		myportfoliopage.StockQuantity(dataTable.get("Quantity"));
		myportfoliopage.StockPrice(dataTable.get("Price"));
		myportfoliopage = myportfoliopage.ClickonAdd();
		waitForPageLoad();
		myportfoliopage.VerifyStock("Bosch Ltd.");
	
		report.flush();
	}
	
	
	@DataProvider
	public Object[][] getStockData(){
		return TestDataProvider.getTestData("StockData.xlsx", "StockDat", "AddStockTest");
		
	}

}
