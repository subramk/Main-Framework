package automation.framework;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Test;
//import org.junit.internal.MethodSorter;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import automation.framework.HomePage;

public class HomePageTest {
	private String sExpected = "Search Results";
	private static String adults_field_selector = "//*[@id='adults']";
	private static String child_field_selector = "//*[@id='child']";
	private static WebDriver driver = null;
	WebElement element;
	
	@BeforeClass
	public static void openBrowser()
	{
		driver = new org.openqa.selenium.firefox.FirefoxDriver();
		driver.get("http://www.phptravels.net");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void locationSearch()
	{
		HomePage.txtLocation(driver).click();
		HomePage.txtLocation(driver).sendKeys("London");
		HomePage.hotelSearch(driver);
		HomePage.txtCheckinDate(driver).clear();
		HomePage.txtCheckinDate(driver).sendKeys("15/08/2018");
		HomePage.txtCheckoutDate(driver).clear();
		HomePage.txtCheckoutDate(driver).sendKeys("22/08/2018");
		//Make a selection from the adult drop down box
		Select selectAdult = new Select (driver.findElement(By.xpath(adults_field_selector)));
		selectAdult.selectByValue("2");
		//Make a selection from the child drop down box
		Select selectChild = new Select (driver.findElement(By.xpath(child_field_selector)));
		selectChild.selectByValue("3");
		HomePage.btnlocation(driver).click();
		Assert.assertEquals(sExpected, driver.getTitle());
		//System.out.println(driver.getTitle());
	}
	
	@AfterClass
	public static void close_Browser()
	{
		driver.quit();
	}
}