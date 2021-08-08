package com.qa.ninja.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.ninja.base.BaseTest;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(enabled = false)
	public void productInforPageTitleTest_iMac() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromSearchResults("iMac");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"),"iMac");
	}
	
	@Test(enabled = false)
	public void verifyProductInfoTest_MacBook() {
		String productName = "MacBook";
		Assert.assertTrue(accountsPage.doSearch(productName));
		productInfoPage = accountsPage.selectProductFromSearchResults("MacBook Pro");
		
		Assert.assertTrue(productInfoPage.totalProductImages() == 4);
		
		Map<String, String> productInforMap = productInfoPage.getProductInformation();
		System.out.println(productInforMap);
		
		//{Brand=Apple, Availability=Out Of Stock, price=$2,000.00, name=MacBook Pro, Product Code=Product 18, Reward Points=800, exTaxPrice=$2,000.00}
		
		Assert.assertEquals(productInforMap.get("name"), "MacBook Pro");
		Assert.assertEquals(productInforMap.get("Brand"), "Apple");
		Assert.assertEquals(productInforMap.get("price"), "$2,000.00");
		Assert.assertEquals(productInforMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productInforMap.get("Reward Points"), "800");

		
	}
	
	@Test(priority = 1)
	public void verifyProductInfoTest_iMac() {
		String productName = "iMac";
		Assert.assertTrue(accountsPage.doSearch(productName));
		productInfoPage = accountsPage.selectProductFromSearchResults("iMac");
		
		Assert.assertTrue(productInfoPage.totalProductImages() == 3);
		
		Map<String, String> productInforMap = productInfoPage.getProductInformation();
		System.out.println(productInforMap);
		
		//{Brand=Apple, Availability=Out Of Stock, price=$100.00, name=iMac, Product Code=Product 14, exTaxPrice=$100.00}
		
		Assert.assertEquals(productInforMap.get("name"), "iMac");
		Assert.assertEquals(productInforMap.get("Brand"), "Apple");
		Assert.assertEquals(productInforMap.get("Availability"), "Out Of Stock");
		Assert.assertEquals(productInforMap.get("price"), "$100.00");
		Assert.assertEquals(productInforMap.get("exTaxPrice"), "$100.00");
	}
	
	@Test(priority = 3)
	public void addToCartTest() {
		productInfoPage.selectQuantity("2");
		productInfoPage.addToCart();
	}
}
