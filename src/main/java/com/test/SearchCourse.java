package com.test;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.genericlib.BaseUI;

public class SearchCourse extends BaseUI {
	private static WebDriver driver;
	public static Properties prop;

	public SearchCourse(WebDriver d, Properties p) {
		// TODO Auto-generated constructor stub
		driver = d;
		prop = p;
	}

	public void search() throws Exception {

		// searching webdevelopment courses
		sendKeys(prop.getProperty("searchtextbox_Xpath"), prop.getProperty("text"), driver);
		click("searchbtn_Xpath", driver);

	}

	public void addFilter() throws Exception {

		waitImplicit(driver);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		// Adding filter level as beginner and language as english
		wait.until(ExpectedConditions.visibilityOf(getElement("level_Xpath", driver)));
		click("level_Xpath", driver);
		wait.until(ExpectedConditions.visibilityOf(getElement("begineer_Xpath", driver)));
		click("begineer_Xpath", driver);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(getElement("language_Xpath", driver)));
		click("language_Xpath", driver);
		wait.until(ExpectedConditions.visibilityOf(getElement("english_Xpath", driver)));
		click("english_Xpath", driver);
	}

	public void getCourseDetail() throws Exception {

		waitImplicit(driver);
		Thread.sleep(5000);

		// Fetching first two course details including name,learning hours and rating
		System.out.println("Name: " + getElement("course1name_Xpath", driver).getText());
		System.out.println("Total learning hours: " + getElement("course1hour_Xpath", driver).getText());
		System.out.println("Rating:" + getElement("course1rate_Xpath", driver).getText() + "\n");
		System.out.println("Name: " + getElement("course2name_Xpath", driver).getText());
		System.out.println("Total learning hours: " + getElement("course2hour_Xpath", driver).getText());
		System.out.println("Rating:" + getElement("course2rate_Xpath", driver).getText() + "\n");

	}
}
