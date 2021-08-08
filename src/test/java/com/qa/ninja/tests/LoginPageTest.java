package com.qa.ninja.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.ninja.base.BaseTest;
import com.qa.ninja.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


// @Listeners(ExtentReportListener.class) -- to access listener extent report from class level


@Epic("Epic 100: define login page features...")

@Story("US 101: define login page class features with title, forgot password link and login functionality")
public class LoginPageTest extends BaseTest{
	
	
	@Description("verify LoginPage Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is :" +title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("verify Header Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void verifyHeaderTitleTest() {
		Assert.assertTrue(loginPage.headerTitleExist());
	}
	
	@Description("verify Register Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void verifyRegisterLinkTest() {
		Assert.assertTrue(loginPage.registerLinkExist());
	}
	
	@Description("verify forgotPassword Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void verifyForgotPasswordLinkTest() {
		Assert.assertTrue(loginPage.forgotPasswordLinkExist());
	}
	
	@Description("verify LoginPage with username and password Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}

}
