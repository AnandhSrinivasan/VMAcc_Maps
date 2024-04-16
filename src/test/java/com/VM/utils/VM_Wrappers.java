package com.VM.utils;

import org.openqa.selenium.support.PageFactory;

import com.VM.pages.Forget_operation;
import com.VM.pages.Login_page;
import com.VM.pages.SignUp_page;

public class VM_Wrappers extends Se_Wrappers {

	public void signupwrapper(String myFName, String myemail, String mynum, String mypwd, String myCpwd) {
		try {
			SignUp_page signup = PageFactory.initElements(driver, SignUp_page.class);
			signup.clicksignup();
			signup.enterFName(myFName);
			signup.enteremail(myemail);
			signup.enterPhonenum(mynum);
			signup.enterPWD(mypwd);
			signup.enterCPWD(myCpwd);
			signup.clickAgreeToogle();
			signup.clickOptional();
			signup.clickSubmit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void loginwrapper(String mailid, String password) {
		try {
			Login_page lp = PageFactory.initElements(driver, Login_page.class);
			lp.clicksignin();
			lp.enteremailid(mailid);
			lp.enterpwd(password);
			lp.clicksumit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void forgotwrapper(String mailid, String password) {
		try {
			Login_page lp = PageFactory.initElements(driver, Login_page.class);
			lp.clicksignin();
			lp.enteremailid(mailid);
			lp.enterpwd(password);
			Forget_operation fo = PageFactory.initElements(driver, Forget_operation.class);
			fo.clickForgot();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
