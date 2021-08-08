package com.qa.ninja.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.ninja.base.BaseTest;
import com.qa.ninja.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("EPIC-200 : Design accounts page")
@Story("US-201 : designing the accounts page with title, header, account sections and product results")
public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountsPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("verify AccountsPage Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accountsPageTitleTest() {
		String text = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(text, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Description("verify AccountsPage Header Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyAccountsPageHeaderTest() {
	String text = accountsPage.getHeaderValue();
	Assert.assertEquals(text, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Description("verify AccountsPage sections count Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void verifyMyAccountSectionsCountTest() {
		Assert.assertTrue(accountsPage.getAccountSectionsCount() == Constants.ACCOUNT_SECTIONS);
	}
	
	@Description("verify AccountsPage sections list Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void verifyMyAccountSectionsListTest() {
		List<String> list = accountsPage.getAccountSectionsList();
		Assert.assertEquals(list, Constants.getAccountsSectionList());
	}
	
	@Description("verify AccountsPage do search Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void searchTest() {
		Assert.assertTrue(accountsPage.doSearch("iMac"));
	}
}
