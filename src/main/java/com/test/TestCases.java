package com.test;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.genericlib.BaseUI;

public class TestCases extends BaseUI {
	private WebDriver driver;
	public static Properties prop;

	@Parameters({ "browser" })
	@Test(groups = { "Smoke Testing", "Regression Testing" }, priority = 0)
	public void testOne(String browser) throws Exception {
		logger = report.createTest("testOne");
		prop = readProperties();
		driver = invokeBrowser(browser);
		openURL(prop.getProperty("url"), driver);

	}

	@Parameters({ "browser" })
	@Test(groups = { "Smoke Testing" }, priority = 1)
	public void testTwo(String browser) throws Exception {
		logger = report.createTest("testTwo");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		getElement("search_Css", driver);

	}

	@Parameters({ "browser" })
	@Test(groups = { "Smoke Testing", "Regression Testing" }, priority = 2)
	public void testThree(String browser) throws Exception {
		logger = report.createTest("testThree");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		SearchCourse s = new SearchCourse(driver, prop);

		s.search();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Testing" }, priority = 3)
	public void testFour(String browser) throws Exception {
		logger = report.createTest("testFour");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		waitImplicit(driver);
		sendKeys(prop.getProperty("searchtextbox_Xpath"), prop.getProperty("text"), driver);
		click("suggestion_Xpath", driver);

	}

	@Parameters({ "browser" })
	@Test(groups = { "Regression Testing" }, priority = 4)
	public void testFive(String browser) throws Exception {
		logger = report.createTest("testFive");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		SearchCourse s = new SearchCourse(driver, prop);

		try {
			s.search();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// getting page title
		String pageTitle = driver.getTitle();
		// check point to validate the browser title
		verify(pageTitle, prop.getProperty("pagevalidationsearch"), driver);

	}

	@Parameters({ "browser" })
	@Test(groups = { "Smoke Testing" }, priority = 5)
	public void testSix(String browser) throws Exception {
		logger = report.createTest("testSix");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		SearchCourse s = new SearchCourse(driver, prop);
		s.search();
		Thread.sleep(4000);
		s.addFilter();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Regression Testing" }, priority = 6)
	public void testSeven(String browser) throws Exception {
		logger = report.createTest("testSeven");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		SearchCourse s = new SearchCourse(driver, prop);

		s.search();
		s.addFilter();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Regression Testing" }, priority = 7)
	public void testEight(String browser) throws IOException, Exception {
		logger = report.createTest("testEight");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		SearchCourse s = new SearchCourse(driver, prop);
		s.search();
		Thread.sleep(5000);
		s.addFilter();
		waitImplicit(driver);
		s.getCourseDetail();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Smoke Testing", "Regression Testing" }, priority = 8)
	public void testNine(String browser) throws Exception {
		logger = report.createTest("testNine");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		LearningLanguage l = new LearningLanguage(driver, prop);
		l.learningLanguage();

	}

	@Parameters({ "browser" })
	@Test(groups = { "Regression Testing" }, priority = 9)
	public void testTen(String browser) throws Exception {
		logger = report.createTest("testTen");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		LearningLanguage l = new LearningLanguage(driver, prop);
		l.learningLanguage();
		// Total count of language
		java.util.List<WebElement> allLanguagesText = driver
				.findElements(By.xpath(prop.getProperty("TotalLanguage_Xpath")));
		System.out.println("Total Number of languages are " + allLanguagesText.size());

	}

	@Parameters({ "browser" })
	@Test(groups = { "Smoke Testing", "Regression Testing" }, priority = 10)
	public void testEleven(String browser) throws Exception {
		logger = report.createTest("testEleven");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		LearningLanguage l = new LearningLanguage(driver, prop);
		l.learningLanguage();
		l.count();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Testing" }, priority = 11)
	public void testTwelve(String browser) throws Exception {
		logger = report.createTest("testTwelve");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		LearningLanguage l = new LearningLanguage(driver, prop);
		l.learningLanguage();
		l.count();
		Actions actions = new Actions(driver);

		WebElement Learning = getElement("LanguageLearning_Xpath", driver);
		actions.moveToElement(Learning).build().perform();
		Learning.click();

	}

	@Parameters({ "browser" })
	@Test(groups = { "Smoke Testing" }, priority = 12)
	public void testThirteen(String browser) throws Exception {
		logger = report.createTest("testThirteen");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		LearningLanguage l = new LearningLanguage(driver, prop);
		l.learningLanguage();
		l.count();
		Actions actions = new Actions(driver);

		WebElement Learning = getElement("LanguageLearning_Xpath", driver);
		actions.moveToElement(Learning).build().perform();
		Learning.click();
		// Total number of levels
		click("Level_Xpath", driver);
		WebElement allLevels = getElement("AllLevels_Xpath", driver);
		java.util.List<WebElement> allLevelsText = allLevels.findElements(By.tagName("label"));
		System.out.println("Total levels are " + allLevelsText.size());

	}

	@Parameters({ "browser" })
	@Test(groups = { "Testing" }, priority = 13)
	public void testFourteen(String browser) throws Exception {
		logger = report.createTest("testFourteen");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		LearningLanguage l = new LearningLanguage(driver, prop);
		l.learningLanguage();
		l.Levels();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Smoke Testing" }, priority = 14)
	public void testFiveteen(String browser) throws Exception {
		logger = report.createTest("testFiveteen");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		// Clicking Buisness header
		click("businessButton_Xpath", driver);

	}

	@Parameters({ "browser" })
	@Test(groups = { "Testing" }, priority = 15)
	public void testSixteen(String browser) throws Exception {
		logger = report.createTest("testSixteen");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
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
		String pageTitle = driver.getTitle();
		// check point to validate the browser title
		verify(pageTitle, prop.getProperty("buisnesspagetitle"), driver);

	}

	@Parameters({ "browser" })
	@Test(groups = { "Regression Testing" }, priority = 16)
	public void testSeventeen(String browser) throws IOException, Exception {
		logger = report.createTest("testSeventeen");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		UdemyForBusiness b = new UdemyForBusiness(driver, prop);

		b.clickLogo();

		// Method to Provide FormValues to Udemy Demo Form
		b.setFormValues();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Regression Testing" }, priority = 17)
	public void testEighteen(String browser) throws Exception {
		logger = report.createTest("testEighteen");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		UdemyForBusiness b = new UdemyForBusiness(driver, prop);

		b.clickLogo();

		// Method to Provide FormValues to Udemy Demo Form
		b.setFormValues();

		// Method to print message On Console
		b.printMessage();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Testing" }, priority = 18)
	public void testNineteen(String browser) throws Exception {
		logger = report.createTest("testNineteen");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		UdemyForBusiness b = new UdemyForBusiness(driver, prop);

		b.clickLogo();

		// Method to Provide FormValues to Udemy Demo Form
		b.setFormValues();

		// Method to print message On Console
		b.printMessage();
	}

	@Parameters({ "browser" })
	@Test(groups = { "Regression Testing" }, priority = 19)
	public void testTwenty(String browser) throws Exception {
		logger = report.createTest("testTwenty");
		prop = readProperties();
		openURL(prop.getProperty("url"), driver);
		UdemyForBusiness b = new UdemyForBusiness(driver, prop);
		b.clickLogo();

		// Method to Provide FormValues to Udemy Demo Form
		b.setFormValues();

		// Print The error Message on the Console
		String errMessage = getElement("errorMsg_Css", driver).getText();

		// displaying values on console
		System.out.println("Error Message:\n" + errMessage);
		quitBrowser(driver);
	}

}
