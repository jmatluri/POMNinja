package com.qa.ninja.Pages;

import org.openqa.selenium.By;

public class SignUpPage {
	
	private By signUp = By.id("SignUp");
	
	public SignUpPage() {
		System.out.println("signup page constructor");
	}
	
	public void clickSignUp() {
		System.out.println("clicked on signup");
	}

}
