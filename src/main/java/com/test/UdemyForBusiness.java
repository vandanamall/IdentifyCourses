package com.test;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.genericlib.BaseUI;
import com.utilities.InputFromExcel;

public class UdemyForBusiness extends BaseUI {
	private WebDriver driver;

	Properties config = new Properties();

	public UdemyForBusiness(WebDriver d, Properties prop) throws IOException {
		// initializing the driver to global driver in this class
		driver = d;
		config = prop;

	}

	public void clickLogo() throws Exception {
		waitImplicit(driver);

		String parent = driver.getWindowHandle();

		waitImplicit(driver);

		// Clicking Buisness header
		click("businessButton_Xpath", driver);

		waitImplicit(driver);

		// Handling multiple window as it redirects to new web page

		Set<String> wins = driver.getWindowHandles();
		for (String windows : wins) {
			if (windows.equals(parent)) {
			} else {
				driver.switchTo().window(windows);
			}
		}

		waitImplicit(driver);

		// clicking on Udemy for Business at the top in header
		click("businessLogo_Xpath", driver);

		waitImplicit(driver);

		// Hovering on "Plans" by using action class
		Actions act = new Actions(driver);
		act.moveToElement(getElement("plans_LinkText", driver)).perform();

		// wait.waitImplicit(driver);

		// clicking on "Teams" by locating from xpath
		click("teams_Xpath", driver);

		click("requestdemo_Xpath", driver);
	}

	// Set Required FormValues

	public void setFormValues() throws Exception {

		String[] feedData = InputFromExcel.businessInput();

		waitImplicit(driver);
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.visibilityOf(getElement("fname_Id", driver)));
		getElement("fname_Id", driver).sendKeys(feedData[0]);
		wait.until(ExpectedConditions.visibilityOf(getElement("lname_Xpath", driver)));
		getElement("lname_Xpath", driver).sendKeys(feedData[1]);
		wait.until(ExpectedConditions.visibilityOf(getElement("email_Css", driver)));
		getElement("email_Css", driver).sendKeys(feedData[2]);
		wait.until(ExpectedConditions.visibilityOf(getElement("phone_Css", driver)));
		getElement("phone_Css", driver).sendKeys(feedData[3]);
		wait.until(ExpectedConditions.visibilityOf(getElement("company_Css", driver)));
		getElement("company_Css", driver).sendKeys(feedData[4]);
		wait.until(ExpectedConditions.visibilityOf(getElement("title_Css", driver)));
		getElement("title_Css", driver).sendKeys(feedData[5]);

		// Scroll Down the page 500 pixel vertically
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("scroll(0,250)");
		/*
		 * wait.until(ExpectedConditions.visibilityOf(getElement("csize_Xpath",
		 * driver))); // Select Company Size from dropdown as
		 * "I'm a contractor / freelancer" WebElement ele = getElement("csize_Xpath",
		 * driver); click("csize_Xpath", driver);
		 * 
		 * waitImplicit(driver);
		 * 
		 * Select select1 = new Select(ele);
		 * select1.selectByValue("I'm a contractor / freelancer");
		 * wait.until(ExpectedConditions.visibilityOf(getElement("myself_Css",
		 * driver))); // Select "Just myself" from the dropdown WebElement ele2 =
		 * getElement("myself_Css", driver); click("myself_Css", driver);
		 * 
		 * waitImplicit(driver);
		 * 
		 * select1 = new Select(ele2); select1.selectByValue("Just myself");
		 */

		// Add Training Requirements (If Any)
		getElement("trainingReq_Css", driver).sendKeys(feedData[6]);

		waitImplicit(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,500)");
		// Click on "Get In Touch" Button

		click("getinTouchButton_Xpath", driver);

		// getting page title
		String pageTitle = driver.getTitle();
		// check point to validate the browser title
		verify(pageTitle, prop.getProperty("succestitle"), driver);

		waitImplicit(driver);

	}

	public void printMessage() throws Exception {

		waitImplicit(driver);
		click("email_Css", driver);
		// Print The error Message on the Console
		String errMessage = getElement("errorMsg_Css", driver).getText();

		// displaying values on console
		System.out.println("Error Message:\n" + errMessage);

	}

}
