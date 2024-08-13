package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_05_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("https://rode.com/en/support/where-to-buy");

		select = new Select(driver.findElement(By.xpath("//select[@id='country']")));

		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Vietnam");

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
	}

	@Test
	public void TC_02() {
		driver.get("https://egov.danang.gov.vn/reg");

		select = new Select(driver.findElement(By.xpath("//select[@id='thuongtru_tinhthanh']")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("thành phố Đà Nẵng");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Đà Nẵng");

		select = new Select(driver.findElement(By.xpath("//select[@id='thuongtru_quanhuyen']")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("quận Hải Châu");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "quận Hải Châu");

		select = new Select(driver.findElement(By.xpath("//select[@id='thuongtru_phuongxa']")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("phường Bình Hiên");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "phường Bình Hiên");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
