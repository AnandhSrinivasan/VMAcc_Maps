package com.VM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.VM.utils.Se_Wrappers;

public class SignUp_page extends Se_Wrappers{

	@FindBy(xpath = "(//span[@class='Sign-Up-Pop'])[2]")
	public WebElement Signupbutton;
	public void clicksignup() {
		clickElement(Signupbutton);
	}
	@FindBy(xpath = "//input[@id='signup-username']")
	public WebElement FName;
	public void enterFName(String myFName) {
		typeText(FName, myFName);
	}
	@FindBy(xpath = "//input[@id='signup-email']")
	public WebElement emailID;
	public void enteremail(String myemail) {
		typeText(emailID, myemail);
	}
	@FindBy(xpath = "//input[@id='signup-mobile']")
	public WebElement phnNum;
	public void enterPhonenum(String mynum) {
		typeText(phnNum, mynum);
	}
	@FindBy(xpath = "//input[@id='signup-password']")
	public WebElement pwd;
	public void enterPWD(String mypwd) {
		typeText(pwd, mypwd);
	}
	@FindBy(xpath = "//input[@id='signup-confirmPassword']")
	public WebElement Cpwd;
	public void enterCPWD(String myCpwd) {
		typeText(Cpwd, myCpwd);
	}
	@FindBy(xpath = "//input[@id='terms-vms-policy']")
	public WebElement AgreeToogle;
	public void clickAgreeToogle() {
		clickElement(AgreeToogle);
	}
	@FindBy(xpath = "//input[@id='newsLetter']")
	public WebElement Optional;
	public void clickOptional() {
		clickElement(Optional);
	}
	@FindBy(xpath = "//span[@id='signup-submit-text']")
	public WebElement submit;
	public void clickSubmit() {
		clickElement(submit);
	}
}
