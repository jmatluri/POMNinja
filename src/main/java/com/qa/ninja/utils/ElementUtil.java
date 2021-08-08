package com.qa.ninja.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.ninja.base.BasePage;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		
		jsUtil = new JavaScriptUtil(driver);
	}

	// findElement
//	public WebElement getElement(By locator) {
//		return driver.findElement(locator);
//	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(BasePage.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		return element;
	}
	

	// findElements
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	// sendKeys
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	// click
	public void doClick(By locator) {
		getElement(locator).click();
	}

	// SendKeys with Action Class
	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).perform();
	}

	// Do Click with Action Class
	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
	}

	// Do SendKeys with MoveToElement Using Action Class
	public void doSendKeysWithMoveToElement(By locator, String value) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).sendKeys(value).build().perform();
	}

	// Do Click with MoveToElement Using Action Class
	public void doClickWithMoveToElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).click().build().perform();
	}

	public By getLocator(String value) {
		return By.id(value);
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	// get count of images or any elements
	public int getElementsCount(String tagName) {
		return driver.findElements(By.tagName(tagName)).size();
	}

	// get collection of attributes --- all the src's in this case
	public List<String> getAttributesList(String tagName, String attributeName) {

		List<String> attributeList = new ArrayList<String>();

		List<WebElement> elementList = driver.findElements(By.tagName(tagName));
		for (WebElement e : elementList) {
			String text = e.getAttribute(attributeName);
			attributeList.add(text);
		}
		return attributeList;
	}

	// Element click from List of links
	public void doClickFromList(By locator, String linkText) {
		List<WebElement> footerList = getElements(locator);
		for (int i = 0; i < footerList.size(); i++) {
			String text = footerList.get(i).getText();
			if (text.equals(linkText)) {
				footerList.get(i).click();
				break;
			}
		}
	}

	// ***************************************DropDown
	// Utils*****************************************

	public void doSelectDropDownByVisibleText(By locator, String text) {
		WebElement dropdown_ele = getElement(locator);
		Select select = new Select(dropdown_ele);
		select.selectByVisibleText(text);
	}

	public void doSelectDropDownByIndex(By locator, int index) {
		WebElement dropdown_ele = getElement(locator);
		Select select = new Select(dropdown_ele);
		select.selectByIndex(index);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		WebElement dropdown_ele = getElement(locator);
		Select select = new Select(dropdown_ele);
		select.selectByValue(value);
	}

	public void selectDropDownValueWithoutSelectClass(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	// *****************************Wait Utils******************************************

	public String waitForTitlePresent(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}

	public void waitForTitlePresent(String titleValue, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut, intervalTime);
		wait.until(ExpectedConditions.titleIs(titleValue));
	}

	//*************visibilityOfAllElementsLocatedBy(locator)*******************
	public  List<WebElement> visibilityOfAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void getPageLinkTexts(By locator, int timeOut) {
		visibilityOfAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}

	public void getPageLinksCount(By locator, int timeOut) {
		visibilityOfAllElements(locator, timeOut).size();
	}

	// ***************
	// presenceOfElementLocated(locator)******************************
	public WebElement waitForElementToBeLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// **************************waitForAlertToBePresent******************************
	public Alert waitForAlertToBePresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	// ********************urlContains*******************
	public boolean waitForUrl(String urlValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(urlValue));
	}

	// *************** visibilityOf(element)****************************************
	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	// ************elementToBeClickable(locator)**********************************
	public void waitForClickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	//********* CustomWait ********************************************
	
	public WebElement retryingElement(By locator) {
		WebElement element = null;
		int attempts = 0;

		// while loop - we don't know total number of iterations. we don't know what
		// time the element will be visible,
		// so we have to keep iterating until we find the element
		/// for loop - we know total number of iterations

		while (attempts < 30) {
			try {
				element = driver.findElement(locator);
				break;
			} catch (NoSuchElementException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
				}
				System.out.println("element is not found in atempt: " + (attempts + 1));
			} catch (StaleElementReferenceException e1) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
				System.out.println("element is not found in atempt: " + (attempts + 1));

			}
			attempts++;
		}
		return element;

	}

}
