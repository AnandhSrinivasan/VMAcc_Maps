package com.VM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.VM.utils.Se_Wrappers;

public class Login_page extends Se_Wrappers{

	@FindBy(xpath = "(//span[@class='Sign-Up-Pop'])[1]")
	public WebElement signIn;
	public void clicksignin() {
		clickElement(signIn);
	}
	@FindBy(xpath = "//input[@id='login-email']")
	public WebElement email;
	public void enteremailid(String mymail) {
		typeText(email, mymail);
	}
	@FindBy(xpath = "//input[@id='login-password']")
	public WebElement pwd;
	public void enterpwd(String mypwd) {
		typeText(pwd, mypwd);
	}
	@FindBy(xpath = "//span[@id='login-submit-text']")
	public WebElement submit;
	public void clicksumit() {
		clickElement(submit);
	}
}
