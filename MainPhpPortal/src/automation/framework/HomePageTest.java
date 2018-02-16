package automation.framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.junit.Test;
//import org.junit.internal.MethodSorter;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import automation.framework.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomePageTest {
	private static String home_page = "html/body/div[6]/div/div/div[2]/a/img";
	private String sExpected = "Search Results";
	private static WebDriver driver = null;
	private static String search_btn_selector = "button.btn-danger.btn.btn-lg.btn-block";
	private static String sHomepageHandle;
	private static String sNewHompageHandle;
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
	public void a_locationSearch_Without_DatePicker()
	{
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		HomePage.txtLocation(driver).click();
		HomePage.txtLocation(driver).sendKeys("London");
		HomePage.hotelSearch(driver);
		HomePage.txtCheckinDate(driver).clear();
		//HomePage.CheckinDatePicker(driver, "2018-05-15");
		HomePage.txtCheckinDate(driver).sendKeys("15/08/2018");
		HomePage.txtCheckoutDate(driver).clear();
		//HomePage.CheckoutDatePicker(driver, "2018-07-21");
		HomePage.txtCheckoutDate(driver).sendKeys("22/08/2018");
		//Make a selection from the adult drop down box
		HomePage.clk_AdultDropdownBox(element, driver).selectByValue("2");
		//Make a selection from the child drop down box
		HomePage.clk_childDropdownBox(element, driver).selectByValue("3");
		HomePage.btnlocation(driver).click();
		sHomepageHandle = driver.getWindowHandle();
		System.out.println(sHomepageHandle);
		Assert.assertEquals(sExpected, driver.getTitle());
		//System.out.println(driver.getTitle());
	}
	
	@Test
	
	public void b_locationSearch_With_DatePicker()
	{
		WebElement element = driver.findElement(By.xpath(home_page));
		JavascriptExecutor jse = ((JavascriptExecutor)driver);
		jse.executeScript("arguments[0].click();", element);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		//driver.navigate().refresh();
		
		sNewHompageHandle = driver.getWindowHandle();
		System.out.println(sNewHompageHandle);
		driver.switchTo().window(sHomepageHandle);
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(search_btn_selector)));
		HomePage.txtLocation(driver).click();
		HomePage.txtLocation(driver).sendKeys("London");
		HomePage.hotelSearch(driver);
		HomePage.txtCheckinDate(driver).click();
		HomePage.CheckinDatePicker(driver, "2018-05-15");
		HomePage.txtCheckoutDate(driver).clear();
		HomePage.CheckoutDatePicker(driver, "2018-07-21");
		//Make a selection from the adult drop down box
		HomePage.clk_AdultDropdownBox(element, driver).selectByValue("2");
		//Make a selection from the child drop down box
		HomePage.clk_childDropdownBox(element, driver).selectByValue("3");
		HomePage.btnlocation(driver).click();
		//Assert.assertEquals(sExpected, driver.getTitle());
		//System.out.println(driver.getTitle());
	}
	
	@AfterClass
	public static void close_Browser()
	{
		driver.quit();
	}
}