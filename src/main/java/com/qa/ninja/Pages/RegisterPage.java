package com.qa.ninja.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ninja.base.BasePage;
import com.qa.ninja.utils.Constants;
import com.qa.ninja.utils.ElementUtil;

public class RegisterPage extends BasePage {
	
	private ElementUtil elementUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subcribeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subcribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By accountSuccessCreatedMsg = By.cssSelector("#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public boolean accountRegistration(String firstName, String lastName, String email, String telePhone, String password, String subscribe) {
		
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telePhone, telePhone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPassword, password);

		if(subscribe.equals("yes")) {
			elementUtil.doClick(subcribeYes);
		} else {
			elementUtil.doClick(subcribeNo);
		}
		
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueButton);
		
		String text = elementUtil.doGetText(accountSuccessCreatedMsg);
		if(text.contains(Constants.ACCOUNT_CREATED_SUCCESS_MSG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
