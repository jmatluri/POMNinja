package com.qa.ninja.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.ninja.Pages.AccountsPage;
import com.qa.ninja.Pages.LoginPage;
import com.qa.ninja.Pages.ProductInfoPage;
import com.qa.ninja.Pages.RegisterPage;

public class BaseTest {
	
	public WebDriver driver;
	public BasePage basePage;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;

	public Properties prop;
	
	
	
	@Parameters("browser")
	
	
	@BeforeTest
	//public void setUp() { 
		public void setUp(String browserName) {  //browserName is coming from regression.xml <parameters..>

		//Start browser and driver.get("url")
		
		basePage = new BasePage();
		prop = basePage.init_prop();
		String browser = prop.getProperty("browser");
		
		if(browser != null) {
			browser = browserName; //code for running on different browsers - regression.xml(parameters)
								// make browser in config.properties = browser in <parameters> tag in regression.xml.... to achieve cross browser testing 
		}
		
		driver = basePage.init_driver(browser);		
		loginPage = new LoginPage(driver);
		
		driver.get(prop.getProperty("url"));	
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
