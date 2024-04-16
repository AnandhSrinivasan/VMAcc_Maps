package com.VM.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.VM.utils.Reports;
import com.VM.utils.Se_Wrappers;
import com.VM.utils.VM_Wrappers;

public class SignUp_PiveTest extends Se_Wrappers{
	Se_Wrappers se = new Se_Wrappers();
	VM_Wrappers vm = new VM_Wrappers();
	Reports repo = new Reports();

	@Test(priority = 1)
	public void signuptest_valid() {
		try {
			Reports.setTCDesc("Validating SignUp of VMAcc Maps functionality with valid credentials");
			vm.signupwrapper("Muruganandh", "muruganandhsrini@gmail.com", "9566259258", "Anandh1212", "Anandh1212");
			screenshot("SignUp_Valid");
			Assert.assertFalse(false);
		} catch (Exception e) {
			
		}
	}
	@AfterMethod
	public void closeBrowser() {
		try{
			driver.close();
			Reports.reportStep("PASS", "Browser closed successfully");
		}
		catch(Exception e) {
			System.out.println("Problem in closing the browser");
			Reports.reportStep("FAIL", "Problem in closing the browser");
			e.printStackTrace();
		}
	}
}
