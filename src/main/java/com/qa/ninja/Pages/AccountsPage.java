package com.qa.ninja.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ninja.base.BasePage;
import com.qa.ninja.utils.Constants;
import com.qa.ninja.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By header = By.cssSelector("div#logo a");
	private By accountSectionHeaders = By.cssSelector("div#content h2");
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By searchButton = By.cssSelector("div#search button[type='button']");
	private By searchItemResult = By.cssSelector(".product-layout .product-thumb");
	private By resultItems = By.cssSelector(".product-thumb .caption h4 a");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	@Step("get accounts page title....")
	public String getAccountsPageTitle() {
		// return driver.getTitle();
		return elementUtil.waitForTitlePresent(Constants.ACCOUNT_PAGE_TITLE, 10);
	}

	@Step("getting the header value.....")
	public String getHeaderValue() {
//		String headerValue = driver.findElement(header).getText();
//		return headerValue;
		if (elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
	}

	@Step("getting total number of account sections.....")
	public int getAccountSectionsCount() {
		// return driver.findElements(accountSectionHeaders).size();
		return elementUtil.getElements(accountSectionHeaders).size();
	}

	@Step("get accounts section list.....")
	public List<String> getAccountSectionsList() {
		List<String> accountsList = new ArrayList<String>();
		// List<WebElement> accSectionList = driver.findElements(accountSectionHeaders);
		List<WebElement> accSectionList = elementUtil.getElements(accountSectionHeaders);
		for (WebElement e : accSectionList) {
			System.out.println(e.getText());
			accountsList.add(e.getText());
		}
		return accountsList;
	}

	@Step("searching a product with name: {0}.....")
	public boolean doSearch(String productName) {
		// driver.findElement(searchText).sendKeys(productName);
		elementUtil.doSendKeys(searchText, productName);
		// driver.findElement(searchButton).click();
		elementUtil.doClick(searchButton);
		// if(driver.findElements(searchItemResult).size() > 0 ) {
		if (elementUtil.getElements(searchItemResult).size() > 0) {
			return true;
		}
		return false;
	}

	@Step("searching a product with name: {0} from the results section.....")

	public ProductInfoPage selectProductFromSearchResults(String productName) {
		List<WebElement> resultItemList = elementUtil.getElements(resultItems);
		System.out.println("total number of items displayed : " + resultItemList.size());
		for (WebElement e : resultItemList) {
			if (e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
