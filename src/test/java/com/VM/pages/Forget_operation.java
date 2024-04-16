package com.VM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.VM.utils.Se_Wrappers;

public class Forget_operation extends Se_Wrappers{

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
	
	@FindBy(xpath = "//span[@id='Forgot-Password']")
	public WebElement Forgot;
	@FindBy(xpath = "//div[@id='snackbars']")
	public WebElement snackbar;
	@FindBy(xpath = "//div[text()='User not found']")
	public WebElement UserNotFound;
	public void clickForgot() {
		clickElement(Forgot);
		waitforelementExplicitwait(snackbar, 2000);
		getTextFromElement(snackbar);
		waitforelementExplicitwait(UserNotFound, 2000);
		getTextFromElement(UserNotFound);
	}
}
