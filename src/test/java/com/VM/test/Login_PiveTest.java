package com.VM.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.VM.utils.Reports;
import com.VM.utils.Se_Wrappers;
import com.VM.utils.VM_Wrappers;

public class Login_PiveTest extends Se_Wrappers {
	Se_Wrappers se = new Se_Wrappers();
	VM_Wrappers vm = new VM_Wrappers();
	Reports repo = new Reports();

	@DataProvider(name ="XLData")
	public Object[][] readExcel() throws IOException {
		Object[][] data = null;
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream("D:\\EclipseIDE\\VM_Maps\\VMXL.xlsx"));
			XSSFSheet sheet = workbook.getSheet("LoginValid");
			XSSFRow row = sheet.getRow(0);
			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfCells = row.getPhysicalNumberOfCells();
			data = new Object[noOfRows - 1][noOfCells];
			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCells; j++) {
					String cellValue = sheet.getRow(i).getCell(j).getStringCellValue();
					data[i - 1][j] = cellValue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
		return data;
	}
	
	@Test(dataProvider ="XLData", priority = 3)
	public void LogInValid(String mailid, String pwd) {
		try {
			Reports.setTCDesc(" Login of VM Acc Maps with valid credentials");
			vm.loginwrapper(mailid, pwd);
			screenshot("Login_Valid");
			Assert.assertFalse(false);
		} catch (Exception e) {
			System.out.println("Fail to Login with Valid Credentials");
			Reports.reportStep("FAIL", "Problem while Login with valid Credentials");
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void closeBrowser() {
		try {
			driver.close();
			Reports.reportStep("PASS", "Browser closed successfully");
		} catch (Exception e) {
			System.out.println("Problem in closing the browser");
			Reports.reportStep("FAIL", "Problem in closing the browser");
			e.printStackTrace();
		}
	}
}