package com.qa.ninja.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";
	public static final int ACCOUNT_SECTIONS = 4;
	
	public static final String ACCOUNT_CREATED_SUCCESS_MSG = "Your Account Has Been Created";
	
	public static final String REGISTER_SHEET_NAME = "registration";
	
	public static List<String> getAccountsSectionList() {
		List<String> accountSectionList = new ArrayList<String>();
		accountSectionList.add("My Account");
		accountSectionList.add("My Orders");
		accountSectionList.add("My Affiliate Account");
		accountSectionList.add("Newsletter");
		
		return accountSectionList;
	}
	

}
