package PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMenuClass;
import org.testng.*;

public class MyPortfolioPage extends PageBaseClass {

	public TopMenuClass topmenu;

	public MyPortfolioPage() {

		topmenu = PageFactory.initElements(driver, TopMenuClass.class);

	}
	
	public MyPortfolioPage selectPortfolio(String Value){
		selectDropDownValue(drop, Value);
		return PageFactory.initElements(driver, MyPortfolioPage.class);
		
	}
		@FindBy(id="deletePortfolio")
	public WebElement deletePortfolio_Btn;
	
		public MyPortfolioPage deletePortFolio(){
			try {
				deletePortfolio_Btn.click();
				acceptAlert();
				logger.log(Status.PASS, "Deleted the Portfolio");
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		
		return PageFactory.initElements(driver,MyPortfolioPage.class);
		}
	
	
	
	
	
	

	@FindBy(id = "createPortfolioButton")
	public WebElement createbutton;

	public MyPortfolioPage clickOncreate() {
		try {
			createbutton.click();
			logger.log(Status.PASS, "clicked on create portfolio button");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
return PageFactory.initElements(driver,MyPortfolioPage.class);
	}

	@FindBy(id = "create")
	public WebElement create_textbox;

	public void enterportfolioname(String pname) {
		try {
			create_textbox.clear();
			create_textbox.sendKeys(pname);
			logger.log(Status.PASS, "Entered the name for portfolio");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	@FindBy(id = "createPortfolio")
	public WebElement createport;

	public void ClickCreateportfolio() {
		try {
			createport.click();
			logger.log(Status.PASS, "click on the create portfolio button");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	
	@FindBy(id="portfolioid")
	public WebElement drop;
	
	public void showDropDown(String pname){
	try{	boolean flag=false;
		List<WebElement> opt = getAllElementsofDropDown(drop);
		for(WebElement o:opt){
			if(o.getText().equalsIgnoreCase(pname)){
				flag=true;
			}
			else{
				flag=false;
			}
		}
		Assert.assertTrue(flag);
		logger.log(Status.PASS,"portfolio:"+pname+"present");
	}catch(Exception e){
		reportFail(e.getMessage());
	}
	}
	
	public void isPorfolioDeleted(String portfolio){
		boolean flag = false;
		try {
			List<WebElement> allOptions = getAllElementsofDropDown(drop);
			for (WebElement option : allOptions) {
				if (!option.getText().equalsIgnoreCase(portfolio)){
					flag=true;
				}else{
					flag = false;
				}
			}
			Assert.assertTrue(flag);
			logger.log(Status.PASS, "Given Portfolio : " + portfolio + " , is not Prsent in Portfolio List");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	
	

	@FindBy(xpath = "//*[@id='headcontent']/div[1]/div[1]/a/span")
	public WebElement moneyBiz_text;

	public TopMenuClass getTopMenu() {
		return topmenu;
	}
	
	
	@FindBy(id="addStock")
	public WebElement addstock;
	
	public void AddStock(){
		try{
			addstock.click();
			logger.log(Status.PASS, "Add Stock button clicked");
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
	}
	
	@FindBy(id="addstockname")
	public WebElement stockname;
	
	@FindBy(xpath="//*[@id='ajax_listOfOptions']/div[1]")
	public WebElement stockvalue;
	
	
	public void enterStockName(String sname){
		try{
			stockname.sendKeys(sname);
			waitForPageLoad();
			stockvalue.click();
			logger.log(Status.PASS, "Entered the name for stock");
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
	}
	
	
	@FindBy(id="stockPurchaseDate")
	public WebElement date;
	
	
	public void AddDate(){
		try{
			date.click();
			logger.log(Status.PASS, "Going to enter date for stocks");
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
	}
	@FindBy(id="addstockqty")
	public WebElement stockqty;

	public void StockQuantity(String qq){
	try{	stockqty.sendKeys(qq);
		logger.log(Status.PASS, "Quantity of stock entered successfully");
	}
	catch(Exception e){
		reportFail(e.getMessage());
		}
	}
	
	@FindBy(id="addstockprice")
	public WebElement stockprice; 
	
	
public void	StockPrice(String price){
	try{
		stockprice.sendKeys(price);
		logger.log(Status.PASS,"Price of stock entered Successfully");
	}
	catch(Exception e){
		reportFail(e.getMessage());
	}
}


@FindBy(id="addStockButton")
public WebElement AddButton; 


public MyPortfolioPage	ClickonAdd(){
try{
	AddButton.click();;
	logger.log(Status.PASS,"Stock added Successfully");
}
catch(Exception e){
	reportFail(e.getMessage());
}
return PageFactory.initElements(driver,MyPortfolioPage.class);
}

@FindBy(id="stock")
public WebElement stocktable;

public void VerifyStock(String StockName){
	boolean flag= false;
	try {
		List<WebElement> tableRows = stocktable.findElements(By.xpath("//*[@id='stock']/tbody/tr"));
		
		for (WebElement row : tableRows) {
			List<WebElement> tableColumsn = row.findElements(By.xpath("//*[@id='stock']/tbody/tr/td"));
			
			for (WebElement column : tableColumsn) {
				if(column.getText().equalsIgnoreCase(StockName)){
					flag=true;
				}
			}
		}
		Assert.assertTrue(flag, "Given Stock : " +StockName + " is present in this Portfolio");
		logger.log(Status.PASS, "Given Stock : " +StockName + " is present in this Portfolio");
	} catch (Exception e) {
		reportFail(e.getMessage());
	}
	
}


}
