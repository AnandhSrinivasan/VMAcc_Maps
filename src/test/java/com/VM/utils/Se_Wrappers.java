package com.VM.utils;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.VM.utils.Reports;
public class Se_Wrappers {

	public static WebDriver driver ;
	//static String browsername;

	// 1.Launching Browser 
	@BeforeMethod
	@Parameters("browser")

//	public void setUp(String browser){
//		this.browsername = browser;
//	}

	public void launchBrowser(String browsername)
	{
		try
		{
			if(browsername.equalsIgnoreCase("chrome")) {

				ChromeOptions opt=new ChromeOptions();
				opt.addArguments("--disable-notifications");
				driver=new ChromeDriver(opt);
			}
			else{
				EdgeOptions opt=new EdgeOptions();
				opt.addArguments("--disable-notifications");
				driver=new EdgeDriver(opt);
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.get("https://accounts.vmmaps.com/");
			Reports.reportStep("PASS", "Chrome browser launched successfully with the given url ("+browsername+")");
		}
		catch(Exception ex) {
			System.out.println("Problem in launching the browser");
			Reports.reportStep("FAIL", "Problem while launching the chrome browser with the given url ("+browsername+")");
			ex.printStackTrace();
		}
	}

	//2.Close the Browser
	public void closecurrentBrowser() {
		try {
			driver.close();
			System.out.println("Current Browser Closed Successfully");
			Reports.reportStep("PASS", "Browser Launched Successfully");
		} 
		catch (Exception e) {
			System.out.println("Problem arise while closing the current browser");
			Reports.reportStep("FAIL", "Probelm arise while close the browser");
			e.printStackTrace();
		}
	}

	//3.Close the All the Webpage
	public void quitAllBrowser() {
		try {
			driver.quit();
			System.out.println("All Webpage Closed Successfully");
			Reports.reportStep("PASS", "Browser Launched Successfully");
		} 
		catch (Exception e) {
			System.out.println("Problem arise while quit all the Webpage");
			Reports.reportStep("FAIL", "Probelm arise due to Quit the browser");
			e.printStackTrace();
		}
	}

	//4.Method to type text in webpage
	public void typeText(WebElement element, String text) {
		try {
			element.sendKeys(text);
			Reports.reportStep("PASS", "Typed text" +text+ "Successfully");
		} 
		catch (Exception e) {
			Reports.reportStep("FAIL", "Typed text" +text+ "UnSuccessfull");
			System.out.println("Probelm arise due to typing the text");
			e.printStackTrace();
		}
	}

	//5.Method to click on the webelement in the webpage 
	public void clickElement(WebElement element) {
		try {
			element.click();
			Reports.reportStep("PASS", "WebElement" +element+ "Clicked Successfully");
		} 
		catch (Exception e) {
			Reports.reportStep("FAIL", "WebElement" +element+ "UnSuccessfull");
			System.out.println("Probelm arise because of unable to click the webelement");
			e.printStackTrace();
		}
	}

	//6.Methods to Select DropDowns by index 
	public void selectByindexDropDown(WebElement element, int index) {
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			Reports.reportStep("PASS", "SelectByIndex for Dropdown is Succesfull");
		}
		catch (Exception e) {
			System.out.println("Probelm arise because of unable to select the index");
			Reports.reportStep("FAIL", "Problem in selectBy index for Dropdown");
			e.printStackTrace();
		}
	}

	//7.Methods to Select DropDowns by value 
	public void selectByValueDropDown(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
		}
		catch (Exception e) {
			System.out.println("Probelm arise because of unable to select the value");
			e.printStackTrace();
		}
	}

	//8.Methods to Select DropDowns by visibletext
	public void selectByVisibletextDropDown(WebElement element, String Visibletext) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(Visibletext);
			Reports.reportStep("PASS", "SelectByValue for Dropdown is Succesfull");
		} 
		catch (Exception e) {
			System.out.println("Probelm arise because of unable to select the visisbletext");
			Reports.reportStep("FAIL", "Problem  in selectByValue for Dropdown");
			e.printStackTrace();
		}
	}

	//9.Method for ExplicitWait	
	public void waitforelementExplicitwait(WebElement element, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element)); 
			Reports.reportStep("PASS", "Explicit wait is successfull ");
		} 
		catch (Exception e) {
			System.out.println("(Expleicitwait)Probelm arise because of unable to wait for visibilty of element");
			Reports.reportStep("FAIL", "problem in Explicit wait");
			e.printStackTrace();
		}
	}
	//10.Method for FluentWait  	
	public void waitforelementFluentwait(WebElement element, int pollingfrequency, int timeout ) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.pollingEvery(Duration.ofSeconds(pollingfrequency))
					.withTimeout(Duration.ofSeconds(timeout));
			Reports.reportStep("PASS", "Fluent wait is Succesfull");

		} 
		catch (Exception e) {
			Reports.reportStep("FAIL", "problem in Fluent wait");
			System.out.println("(Fluentwait)Probelm arise because of unable to wait for element");
			e.printStackTrace();
		}
	}

	//11. Method for Action Doubleclick
	public void actionsDoubleclickElement(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.doubleClick(element).build().perform();
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to Doubleclick");
			e.printStackTrace();
		}
	}
	//12. Method for Action RightClick
	public void actionsRightclickElement(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.contextClick(element).build().perform();
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to Rightclick");
			e.printStackTrace();
		}
	}	

	//13. Method for Action Move to Element
	public void actionsMovetoElement(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to MovetoElement");
			e.printStackTrace();
		}
	}

	//14.Method for Action Drag and Drop
	public void actionsDragandDropElement(WebElement source, WebElement target) {
		try {
			Actions actions = new Actions(driver);
			actions.dragAndDrop(source,target).build().perform();
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to DragandDrop");
			e.printStackTrace();
		}
	}

	//15.Method for Action Click
	public void actionsClickElement(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.click(element).build().perform();
			actions.clickAndHold(element).build().perform();
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to Click");
			e.printStackTrace();
		}
	}

	//16.Method for Action Clickandhold
	public void actionsClickandholdElement(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.clickAndHold(element).build().perform();
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to ClickandHold");
			e.printStackTrace();
		}
	}	

	//17.Method for FramebyIndex
	public void frameByindex(int index) {
		try {
			driver.switchTo().frame(index);
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to switch to frame index");
			e.printStackTrace();
		}
	}

	//18.Method for Frame by NameorID
	public void frameByNameorID(int nameid) {
		try {
			driver.switchTo().frame(nameid);
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to switch to frame Name or ID");
			e.printStackTrace();
		}
	}

	//19.Method for Frame by WebElement
	public void frameByWebElement(WebElement frameElement) {
		try {
			driver.switchTo().frame(frameElement);
			Reports.reportStep("PASS", "Switch to Frame by Webelement is Succesfull");
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to switch to frame WebElement");
			Reports.reportStep("FAIL", "Problem  in Switch frames By WebElement");
			e.printStackTrace();
		}
	}

	//20.Method for Window Handle
	public void windowhandleParentWindow() {
		try {

			Set<String> allwindow = driver.getWindowHandles();
			for (String eachwindows : allwindow) {
				driver.switchTo().window(eachwindows);
			}
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to switch to window");
			e.printStackTrace();
		}
	}

	//21.Method for SwitchParentWindow
	public void switchparentwindow() {
		try {
			String parentwindow = driver.getWindowHandle();
			driver.switchTo().window(parentwindow);
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to switch to Parentwindow");
			e.printStackTrace();
		}
	}

	//21.Method for SwitchDefaultWindow
	public void DefaultcontentWindow(String DefaultWindow) {
		try {
			driver.switchTo().defaultContent();
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to switch to DefaultWindow");
			e.printStackTrace();
		}
	}

	//22. Method for JavaScriptExecutor	Scrolldown
	public void javascriptexecScrollVertical(int scrollDownValue) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,"+ scrollDownValue +");");
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to (JavaScriptexec) scrollVertical");
			e.printStackTrace();
		}
	}

	//22. Method for JavaScriptExecutor	scroll up
	public void javascriptexecScrollHorizontal(int scrollUpValue) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy("+ scrollUpValue +", 0);"); 
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to (JavaScriptexec) ScrollHorizontal");
			e.printStackTrace();
		}
	}

	//23.Method for JavaScriptExecutor Endcontent
	public void javascriptexecEndContent() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to (JavaScriptexec) EndContent");
			e.printStackTrace();
		}
	}

	//24.Method for JavaScriptExecutor Startingcontent
	public void javascriptexecStartingContent() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,-document.body.scrollHeight);");
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to (JavaScriptexec) Startingcontent");
			e.printStackTrace();
		}
	}

	//25.Method for JavaScriptExecutor ScrollRight
	public void javascriptexecScrollRight() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(document.body.scrollWidth, 0);");
			System.out.println(js.executeScript("return document.title;"));
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to (JavaScriptexec) ScrollRight");
			e.printStackTrace();
		}
	}	

	//26.Method for JavaScriptExecutor click particularWebElement
	public void javascriptexecScrollWebElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",element);
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to (JavaScriptexec) click Particular WebElement");
			e.printStackTrace();
		}
	}

	//27.Method for JavaScriptExecutor TypeText
	public void javascriptexecTypeText(WebElement element, String text) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].value='"+ text +"';",element);
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to (JavaScriptexec) typetext");
			e.printStackTrace();
		}
	}


	//28.Method for JavaScriptExecutor DocumentTitle
	public void javascriptexecDocumentTitle() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			System.out.println(js.executeScript("return document.title;"));
		} 
		catch (Exception e) {
			System.out.println("(JavaScriptexec) Problem arise because of unable to Get the Document title");
			e.printStackTrace();
		}
	}	

	//29.Method for Accept alert
	public void Acceptalert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Reports.reportStep("PASS", "Accept Alert is Succesfull");
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to accept the alert");
			Reports.reportStep("FAIL", "Problem  in accept alert");
			e.printStackTrace();
		}
	}

	//30.Method for Dismiss alert
	public void Dismissalert() {
		try {
			Alert alert = driver.switchTo().alert();
			Reports.reportStep("PASS", "Dismiss Alert is Succesfull");
			alert.dismiss();
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to Dismiss the alert");
			Reports.reportStep("FAIL", "Problem  in Dismiss alert");
			e.printStackTrace();
		}
	}

	//31.Method for TypeTextinalert
	public void TypeTextinalert(String text) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
			alert.accept();
			Reports.reportStep("PASS", "Send Text to Alert is Succesfull");
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to TypeText in alert");
			Reports.reportStep("FAIL", "Problem  in TypeText in alert from alert");
			e.printStackTrace();
		}
	}

	//32.Method for verifySelect
	public boolean verifySelect(WebElement element) {

		boolean returnvalue = false;
		try {
			if (element.isSelected()) {
				returnvalue = true;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return returnvalue;
	}

	//33.Method for verifyDisplayed
	public boolean verifyDisplayed(WebElement element) {

		boolean returnvalue = false;
		try {
			if (element.isDisplayed()) {
				returnvalue = true;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return returnvalue;
	}

	//34.Method for verifyEnabled
	public boolean verifyEnabled(WebElement element) {

		boolean returnvalue = false;
		try {
			if (element.isEnabled()) {
				returnvalue = true;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return returnvalue;
	}

	//35.Method For Taking Screenshot
	public void takeScreenshot(String screenshotName) {
		try {
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File Destination = new File(System.getProperty("user.dir")+"/screenshots/"+screenshotName+".png");
			FileUtils.copyFile(source, Destination);
		} 
		catch (Exception e) {
			System.out.println("Problem arise because of unable to Take the Screenshot");
			e.printStackTrace();
		}
	}

	//36.Method for Implicit wait
	// Implicit wait

	public void setImplicitWait(int timeout)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			Reports.reportStep("PASS", "Implicit wait set to " + timeout + " seconds");

		}
		catch (Exception ex)
		{
			Reports.reportStep("FAIL", "Problem while setting implicit wait");

			ex.printStackTrace();
		}
	}

	//37. Method for GetText from Webpage

	public String getTextFromElement(WebElement element) {
		try {
			String text = element.getText();
			Reports.reportStep("PASS", "Got text '" + text + "' successfully");
			return text;
		} catch (Exception e) {
			Reports.reportStep("FAIL", "Failed to get text from the element");
			System.out.println("Problem arose while getting text from the element");
			e.printStackTrace();
			return null; // or you can throw an exception here
		}
	}

	//38. Method foe Navigate forward
	public void navigateforward(){
		try{
			driver.navigate().forward();
			Reports.reportStep("PASS", "Forward navigation is successfull ");
		}
		catch(Exception ex){
			System.out.println("problem in navigate forward");
			Reports.reportStep("FAIL",  "problem in navigate forward");
			ex.printStackTrace();
		}
	}
	//38. Method for Navigate back
	public void navigateBack()	{
		try{
			driver.navigate().back();
			Reports.reportStep("PASS", "Navigation back is successfull ");
		}
		catch(Exception ex){
			System.out.println("problem in navigate back");
			Reports.reportStep("FAIL", " navigat Back is Fail ");
			ex.printStackTrace();
		}
	}
	//39.Method for Navigate refresh
	public void navigateRefresh(){
		try{
			driver.navigate().refresh();
			Reports.reportStep("PASS", "Refresh is successfull ");
		}
		catch(Exception ex){
			System.out.println("problem in navigate refresh");
			Reports.reportStep("FAIL", "problem in navigate refresh");
			ex.printStackTrace();
		}
	}
	//40.Method for Select the visible text
	public void selectByVisibleText(WebElement ele, String visibleText){
		try{
			Select sel = new Select(ele);
			sel.selectByVisibleText(visibleText);
			Reports.reportStep("PASS", "selectByVisibleText for Dropdown is Succesfull");
		}
		catch(Exception ex)	{
			System.out.println("problem in selectByVisible text");
			Reports.reportStep("FAIL", "Problem in selectByVisible text for Dropdown");
			ex.printStackTrace();
		}

	}

	//41.Method for Get Text From Alert
	public void getTextmethod()	{
		try{
			Alert alert= driver.switchTo().alert();
			String alertText=alert.getText();
			Reports.reportStep("PASS", "Get Text From Alert is Succesfull");
			System.out.println(alertText);
		}
		catch(Exception ex)	{
			System.out.println("problem in getText method");
			Reports.reportStep("FAIL", "Problem  in Getting Text from alert");
			ex.printStackTrace();
		}
	}

	//42.Method for  Keyboard actions
	public void keyboardActions(WebElement ele,String text){
		try	{
			Actions act=new Actions(driver);
			act.keyDown(ele,Keys.SHIFT).sendKeys(text).perform();
		}
		catch(Exception ex){
			System.out.println("problem in keyBoard actions");
			ex.printStackTrace();
		}
	}

	//43.Method for window handling

	public void switchWindows(){
		try{
			String parentWindow= driver.getWindowHandle();
			Set<String> allWindows= driver.getWindowHandles();

			for(String eachWindow:allWindows){

				if(! parentWindow.equals(eachWindow)){
					driver.switchTo().window(eachWindow);
				}
			}
		}
		catch(Exception ex){
			System.out.println("problem in window handling");
			ex.printStackTrace();
		}
	}
	//44.Method for get Title
	public String getTitle(){
		String title="";
		try{
			title=driver.getTitle();
			System.out.println(title);
		}
		catch(Exception ex){
			System.out.println("problem in getTitle method");
			ex.printStackTrace();
		}
		return title;
	}

	//45.Method for getCurrentUrl
	public void getCurrentUrl(){
		try{
			String currentUrl=driver.getCurrentUrl();
			System.out.println(currentUrl);
		}
		catch(Exception ex){
			System.out.println("problem in getCurrent Url method");
			ex.printStackTrace();
		}
	}

	//46.Method for getScreenshot name for different capture
	public void screenshot(String screenshotName){
		try{
			File source= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File dest= new File(System.getProperty("user.dir")+"/ScreenShots/"+screenshotName+".png");
			FileUtils.copyFile(source, dest);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	//47.Method for switch to parent winodw
	public void parent(){
		try{
			driver.switchTo().defaultContent();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	//48.Method for calendar
	public void calender(String month1,String year1,String date1){
		try{
			WebElement ele=driver.findElement(By.xpath(" //div[contains(@data-testid,'month-"+month1+"-"+year1+"')]//div[@data-testid='undefined-calendar-day-"+date1+"']/div"));
			ele.click();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	//49. Method for calendar SignUp
	public void calenderSignUp() {
		List<WebElement> dates = driver.findElements(By.xpath("//div[@class='react-datepicker__month-container']//div[@class='react-datepicker__month']/div/div"));
		int count=dates.size();
		for(int i=0;i<count;i++){
			String text=driver.findElements(By.xpath("//div[@tabindex='-1']")).get(i).getText();
			if(text.equalsIgnoreCase("5")){
				driver.findElements(By.xpath("//*[@class='react-datepicker__month-container']//*[@role='button']")).get(i).click();
				break;
			}
		} 
	}

	//50. Method for click radiobutton
	public void clickRadioButton(WebElement radioButton) {
		try {
			if (!radioButton.isSelected()) {
				radioButton.click();
				Reports.reportStep("PASS", "Clicking radio button is successful");
			} else {
				Reports.reportStep("INFO", "Radio button is already selected");
			}
		} catch (Exception e) {
			System.out.println("Problem arose because of unable to click the radio button");
			Reports.reportStep("FAIL", "Problem in clicking radio button");
			e.printStackTrace();
		}
	}

	//51. Method for Thread.sleep 
	public void clicksleepElement(WebElement element) {
		try {
			element.click();
			Reports.reportStep("PASS", "WebElement " + element + " Clicked Successfully");
			Thread.sleep(5000); // Introduce a 5-second delay after clicking the element
		} catch (Exception e) {
			Reports.reportStep("FAIL", "WebElement " + element + " Unsuccessful");
			System.out.println("Problem arises because of unable to click the webelement");
			e.printStackTrace();
		}
	}


	//52.Method to check if a URL is reachable (not broken)
	public boolean isURLReachable(String urlString) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD");
			int responseCode = connection.getResponseCode();
			return (responseCode == HttpURLConnection.HTTP_OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Method to clear the selected text in a WebElement
    public void clearSelectedText(WebElement element) {
        try {
            // Create an instance of the Actions class
            Actions actions = new Actions(driver);
            // Click and hold on the element to select its text
            actions.clickAndHold(element).perform();
            // Release the mouse to clear the selected text
            actions.release().perform();
            // Report the success
            Reports.reportStep("PASS", "Cleared selected text from WebElement " + element + " successfully");
        } catch (Exception e) {
            // Report the failure
            Reports.reportStep("FAIL", "Unable to clear selected text from WebElement " + element);
            System.out.println("Problem arose because of unable to clear selected text from the WebElement");
            e.printStackTrace();
        }
    }

	//54. Method for selecting text in a WebElement
	public void selectText(WebElement element) {
	    try {       
	        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	        Reports.reportStep("PASS", "Selected text in WebElement " + element + " successfully");
	    } catch (Exception e) {
	        Reports.reportStep("FAIL", "Unable to select text in WebElement " + element);
	        System.out.println("Problem arose because of unable to select text in the WebElement");
	        e.printStackTrace();
	    }
	}

	//55. Method for Javascript scroll down
		public void jsscrollDownBottom(){
			try{
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

			}
			catch(Exception e){
				System.out.println("problem in  scroll down to the bottom of the page");
				e.printStackTrace();
			}
		}
}
