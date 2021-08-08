package com.qa.ninja.Pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ninja.base.BasePage;
import com.qa.ninja.utils.ElementUtil;

public class ProductInfoPage extends BasePage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productNameHeader = By.cssSelector("div#content h1");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By productImages = By.cssSelector(".thumbnails li img");
	private By addToCartMsg = By.cssSelector(".alert-success");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public Map<String, String> getProductInformation() {
		
		Map<String, String> productInfoMap = new HashMap<>();
		
		productInfoMap.put("name", elementUtil.doGetText(productNameHeader).trim());
		
		List<WebElement> productInfoList = elementUtil.getElements(productMetaData);
		for(WebElement e : productInfoList) {
			productInfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		
		List<WebElement> productPriceList = elementUtil.getElements(productPrice);
			productInfoMap.put("price", productPriceList.get(0).getText().trim());
			productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		
		return productInfoMap;
	}
	
	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(quantity, qty);
	}
	
	public void addToCart() {
		elementUtil.doClick(addToCart);
// 		System.out.println("add To Cart Msg : " + elementUtil.doGetText(addToCartMsg));

//		if(elementUtil.doGetText(addToCartMsg).contains("Success")) {
//			return true;
//		}
//		return false;
	}
	
	public int totalProductImages() {
		int productImagesCount = elementUtil.getElements(productImages).size();
		System.out.println("total number of product images :" + productImagesCount );
		return productImagesCount;
	}

	public String getProductInfoPageTitle(String productName) {
		return elementUtil.waitForTitlePresent(productName, 10);
	}

}
