package com.VM.utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class Reports {
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	
	@BeforeSuite(alwaysRun=true)
	public static void startReport(){
		try{	
			sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/VM WebAutomationReport.html");
			sparkReporter.config().setReportName("VM Accounts MapsWeb Automation");
			sparkReporter.config().setDocumentTitle("Web Auto Report");
			sparkReporter.config().setTheme(Theme.DARK);
			
			extentReports= new ExtentReports();
			extentReports.attachReporter(sparkReporter);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void setTCDesc(String testcaseName){
		try{
			extentTest=extentReports.createTest(testcaseName);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void reportStep(String status,String desc){
		try{
			
			if(status.equalsIgnoreCase("PASS")){
				extentTest.log(Status.PASS, desc);
			}
			else if(status.equalsIgnoreCase("FAIL")){
				extentTest.log(Status.FAIL, desc);
			}
			else if (status.equalsIgnoreCase("SKIP")){
				extentTest.log(Status.SKIP, desc);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@AfterSuite(alwaysRun=true)
	public void endReport(){
		try{
			extentReports.flush();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}	
}

