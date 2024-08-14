package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_06_Button_Checkbox_RadioButton {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//li[contains(@class,'popup-login-tab-login')]")).click();
		sleepInSeconds(1);

		WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));

		Assert.assertFalse(loginButton.isEnabled());

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("dongafc@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("SeJava3@");
		sleepInSeconds(1);

		Assert.assertTrue(loginButton.isEnabled());

		String loginBtnColor = Color.fromString(loginButton.getCssValue("background")).asHex().toUpperCase();
		Assert.assertEquals(loginBtnColor, "#C92127");
	}

	@Test
	public void TC_02_Default_Checkbox_RadioButton() {
		// Checkbox
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		checkDefaultCheckboxOrRadioButton("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']");

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']")).isSelected());

		uncheckDefaultCheckbox("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']");

		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']")).isSelected());

		// RadioButton
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input[@type='radio']")).isSelected());

		checkDefaultCheckboxOrRadioButton("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input[@type='radio']");

		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input[@type='radio']")).isSelected());

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input[@type='radio']")).isSelected());
	}

	@Test
	public void TC_03_Custom_Checkbox_RadioButton() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		// RadioButton
		WebElement hanoiRadioBtn = driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @role='radio']"));

		Assert.assertEquals(hanoiRadioBtn.getAttribute("aria-checked"), "false");

		jsExecutor.executeScript("arguments[0].click();", hanoiRadioBtn);
		sleepInSeconds(1);

		Assert.assertEquals(hanoiRadioBtn.getAttribute("aria-checked"), "true");

		// Checkbox
		List<WebElement> quangCheckboxes = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng') and @role='checkbox']"));

		for (WebElement checkbox : quangCheckboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "false");
		}

		for (WebElement checkbox : quangCheckboxes) {
			if (!checkbox.isSelected() && checkbox.isEnabled()) {
				jsExecutor.executeScript("arguments[0].click();", checkbox);
				sleepInSeconds(1);
			}
		}

		for (WebElement checkbox : quangCheckboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
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

	public void checkDefaultCheckboxOrRadioButton(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (!element.isSelected() && element.isEnabled()) {
			element.click();
			sleepInSeconds(1);
		}
	}

	public void uncheckDefaultCheckbox(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.isSelected() && element.isEnabled()) {
			element.click();
			sleepInSeconds(1);
		}
	}

}
