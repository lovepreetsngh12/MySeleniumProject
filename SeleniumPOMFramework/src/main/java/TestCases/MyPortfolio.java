package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseClasses.PageBaseClass;
import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPortfolioPage;
import PageClasses.PortfolioLoginPage;

public class MyPortfolio extends PageBaseClass{
	LandingPage landingpage;
	MoneyPage moneypage;
PortfolioLoginPage portfoliologinpage;
	
	@Test
	public void openPortfolio(){
	landingpage.invokeBrowser("Chrome");
	landingpage.OpenApplication();
	moneypage=landingpage.clickMoneyLink();
	portfoliologinpage = moneypage.clickSignInLink();
	portfoliologinpage.doLogin("lovepreetsngh12@rediffmail.com", "Love@3607");
	//return PageFactory.initElements(driver, MyPortfolioPage.class);
	}

}
