package com.genericlib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utilities.DateUtils;
import com.utilities.ExtentReportManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUI {

	public static Properties prop;

	public ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;

	public WebDriver invokeBrowser(String browser) {
		WebDriver driver = null;
		try {
			if (browser.equalsIgnoreCase("chrome")) {

				// Setting property for chrome driver
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();

				// disable the info bar
				options.addArguments("--disable-infobars");

				// disable the notifications
				options.addArguments("--disable-notifications");

				// create object of chrome driver, register options class
				driver = new ChromeDriver(options);

			} else if ((browser).equalsIgnoreCase("IE")) {
				// setting property for edge driver
				WebDriverManager.edgedriver().setup();

				driver = new EdgeDriver();

			} else {
				driver = new ChromeDriver();
			}

			// Adding Implicit time
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			/* Maximize window */
			driver.manage().window().maximize();
			// Adding Page load timeout
			driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	public Properties readProperties() throws IOException {
		/* File Path */
		File f = new File("src/test/resources/com/objectrepository/Data.properties");

		/* FileInputStream for reading the data */
		FileInputStream fis = new FileInputStream(f);

		/* Properties class instance */
		prop = new Properties();

		/* Load the properties */
		prop.load(fis);
		return prop;
	}

//Opening url
	public void openURL(String websiteURL, WebDriver d) {

		d.navigate().to(websiteURL);
		// Adding Page load timeout
		d.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
	}

//Close browser
	public void tearDown(WebDriver d) {

		d.close();
		report.flush();
	}

	// Quit Browser
	public void quitBrowser(WebDriver d) {

		d.quit();
		report.flush();
	}

	public void waitImplicit(WebDriver d) {

		// Method to return boolean value as page is loaded completely by using
		// JavascriptExecutor
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// Explicit Wait with 30 seconds timeout
		WebDriverWait wait = new WebDriverWait(d, 180);

		// Wait until expected condition is true
		wait.until(pageLoadCondition);
	}

	// Identify Page Element
	public WebElement getElement(String locatorKey, WebDriver driver) throws Exception {
		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_ClassName")) {
				element = driver.findElement(By.className(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Css")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();
		}

		return element;
	}

	public void sendKeysName(String name, String data, WebDriver d) {

		d.findElement(By.name(name)).sendKeys(data);
	}

	public void sendKeys(String xpath, String data, WebDriver d) {

		d.findElement(By.xpath(xpath)).sendKeys(data);
	}

	public void click(String locatorKey, WebDriver driver) throws Exception {

		try {
			if (locatorKey.endsWith("_Id")) {
				driver.findElement(By.id(prop.getProperty(locatorKey))).click();
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				driver.findElement(By.xpath(prop.getProperty(locatorKey))).click();
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_ClassName")) {
				driver.findElement(By.className(prop.getProperty(locatorKey))).click();
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Css")) {
				driver.findElement(By.cssSelector(prop.getProperty(locatorKey))).click();
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				driver.findElement(By.linkText(prop.getProperty(locatorKey))).click();
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				driver.findElement(By.partialLinkText(prop.getProperty(locatorKey))).click();
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				driver.findElement(By.name(prop.getProperty(locatorKey))).click();
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();
		}

	}

	// Verify the element
	public void verify(String title, String data, WebDriver driver) {
		try {
			Assert.assertEquals(title, data);
			System.out.println("Browser Title validation successful\n");
		} catch (AssertionError e) {
			// Calling take screenshot method
			try {
				takeSnapShot(driver);
			} catch (Exception exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			}
		}
	}

	// Take Screenshot
	public static void takeSnapShot(WebDriver webdriver) throws Exception {

		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtils.getTimeStamp() + ".png");

		try {
			FileUtils.copyFile(SrcFile, DestFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/" + DateUtils.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot taken and saved in screenshot folder");

	}

	/******************
	 * Reporting Functions
	 * 
	 * @throws Exception
	 ***********************/
	public void reportFail(String reportString) throws Exception {

		logger.log(Status.FAIL, reportString);
		// takeSnapShot(driver);
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	

}
