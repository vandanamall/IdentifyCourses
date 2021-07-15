package com.test;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.genericlib.BaseUI;

public class LearningLanguage extends BaseUI {
	private WebDriver driver;

	Properties config = new Properties();

	public LearningLanguage(WebDriver d, Properties prop) throws IOException {
		// initializing the driver to global driver in this class
		driver = d;
		config = prop;
	}

	public void learningLanguage() throws Exception {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement categories = getElement("categories_Xpath", driver);
		Actions actions = new Actions(driver);

		WebDriverWait wait = new WebDriverWait(driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", categories);
		Thread.sleep(5000);

		actions.moveToElement(wait.until(ExpectedConditions.visibilityOf(categories))).build().perform();

		actions.moveToElement(categories).build().perform();

		wait.until(ExpectedConditions.visibilityOf(getElement("Teaching&Academics_Xpath", driver)));

		WebElement teaching = getElement("Teaching&Academics_Xpath", driver);
		actions.moveToElement(teaching).build().perform();

		wait.until(ExpectedConditions.visibilityOf(getElement("LanguageLearning_Xpath", driver)));

		WebElement Learning = getElement("LanguageLearning_Xpath", driver);
		actions.moveToElement(Learning).build().perform();

		// Total count of language
		java.util.List<WebElement> allLanguagesText = driver
				.findElements(By.xpath(config.getProperty("TotalLanguage_Xpath")));
		System.out.println("Total Number of languages are " + allLanguagesText.size());

		// Prints the language
		for (WebElement topLanguage : allLanguagesText) {
			String topLanguageText = topLanguage.getText();
			if (!topLanguageText.isEmpty()) {
				System.out.println(topLanguageText);
			}
		}
	}

	public void Levels() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions actions = new Actions(driver);

		WebElement Learning = getElement("LanguageLearning_Xpath", driver);
		actions.moveToElement(Learning).build().perform();
		Learning.click();

		// getting page title
		String pageTitle = driver.getTitle();
		// check point to validate the browser title
		verify(pageTitle, prop.getProperty("learntitle"), driver);

		// Total number of levels
		click("Level_Xpath", driver);
		WebElement allLevels = getElement("AllLevels_Xpath", driver);
		java.util.List<WebElement> allLevelsText = allLevels.findElements(By.tagName("label"));
		System.out.println("Total levels are " + allLevelsText.size());

		// Prints the levels
		for (WebElement topLevel : allLevelsText) {
			String topLevelText = topLevel.getText();
			if (!topLevelText.isEmpty()) {
				System.out.println(topLevelText);

			}
		}
	}
}
