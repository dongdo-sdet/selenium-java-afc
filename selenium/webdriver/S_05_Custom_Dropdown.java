package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_05_Custom_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectOptionInCustomDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li", "Medium");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Medium");

		selectOptionInCustomDropdown("//span[@id='files-button']", "//ul[@id='files-menu']/li", "ui.jQuery.js");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']/span[@class='ui-selectmenu-text']")).getText(), "ui.jQuery.js");

		selectOptionInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "13");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "13");

		selectOptionInCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li", "Prof.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Prof.");
	}

	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectOptionInCustomDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']/div", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectOptionInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
	}

	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectOptionInCustomDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div", "Australia");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Australia");

		selectOptionInCustomDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div", "Argentina");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Argentina");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSeconds(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectOptionInCustomDropdown(String dropdownXPath, String allOptionsXPath, String expectedOption) {
		driver.findElement(By.xpath(dropdownXPath)).click();
		sleepInSeconds(1);
		List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
		for (WebElement option : allOptions) {
			if (option.getText().trim().equals(expectedOption)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", option);
				sleepInSeconds(1);
				option.click();
				sleepInSeconds(1);
				break;
			}
		}
	}

	public void selectOptionInEditableDropdown(String dropdownXPath, String allOptionsXPath, String expectedOption) {
		WebElement editableDropdown = driver.findElement(By.xpath(dropdownXPath));
		editableDropdown.clear();
		editableDropdown.sendKeys(expectedOption);
		sleepInSeconds(1);
		List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
		for (WebElement option : allOptions) {
			if (option.getText().trim().equals(expectedOption)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", option);
				sleepInSeconds(1);
				option.click();
				sleepInSeconds(1);
				break;
			}
		}
	}

}
