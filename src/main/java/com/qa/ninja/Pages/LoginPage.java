package com.qa.ninja.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ninja.base.BasePage;
import com.qa.ninja.utils.Constants;
import com.qa.ninja.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. create private By Locators:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type = 'submit' and @value]");
	private By headerTitle = By.linkText("Your Store");
	//private By registerLink = By.linkText("Continue");
	private By forgotPasswordLink = By.cssSelector(".form-group a");
	
	//By locators for Register Page
	private By registerLink = By.linkText("Register");
	

	// 2. create constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// 3. create public page action methods

	@Step("getting the login page title....")
	public String getLoginPageTitle() {
		// return driver.getTitle();
		return elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}

	public boolean headerTitleExist() {
		//return driver.findElement(headerTitle).isDisplayed();
		return elementUtil.doIsDisplayed(headerTitle);
	}

	@Step("checking forgot pwd link exists....")
	public boolean forgotPasswordLinkExist() {
		//return driver.findElement(forgotPasswordLink).isDisplayed();
		return elementUtil.doIsDisplayed(forgotPasswordLink);
	}

	public boolean registerLinkExist() {
		//return driver.findElement(registerLink).isDisplayed();
		return elementUtil.doIsDisplayed(registerLink);
	}

	@Step("login with username : {0} and password : {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("logging with username :" + un + " and password " + pwd);
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);

		return new AccountsPage(driver);

	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		
		return new RegisterPage(driver);
	}

}
