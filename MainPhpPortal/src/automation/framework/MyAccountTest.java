package automation.framework;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
//import org.junit.internal.MethodSorter;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import automation.framework.SignIn;
import automation.framework.LoginPage;
import automation.framework.Account;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyAccountTest {
	
	public static String invoicePageTitle;
	public static String myPageTitle;
	public static String mainPage;
	private static WebDriver driver = null;
	private static WebElement element;
	private static String sChildPageName = "Invoice";
	private static String sParentPageName = "My Account";
	
	@BeforeClass
	public static void openBrowser()
	{
		driver = new org.openqa.selenium.firefox.FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	
	public void a_myAccountLogin()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.get("http://www.phptravels.net");
		driver.manage().window().maximize();
		LoginPage.clk_MyAccount_btn(driver);
		LoginPage.clk_Login_btn(driver).click();
		SignIn.Execute(driver, "user@phptravels.com", "demouser");
		try
		{
			element = driver.findElement(By.className("RTL"));
		}
		catch (Exception e)
		{
			
		}
		Assert.assertNotNull(element);
		
	}
	
	@Test
	public void b_locateBookingCode()
	{
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		Account.BookedFlights(driver);
		mainPage = driver.getWindowHandle();
		System.out.println(mainPage);
		for (String handles : driver.getWindowHandles())
		{
			System.out.println(handles);
			driver.switchTo().window(handles);
		}
		invoicePageTitle = driver.getTitle();
		Assert.assertEquals(sChildPageName, invoicePageTitle);
		//System.out.println(invoicePageTitle);
	}
	
	@Test
	public void c_viewInvoiceDetails()
	{
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		Account.InvoiceDetails(driver, mainPage);
		myPageTitle = driver.getTitle();
		Assert.assertEquals(sParentPageName, myPageTitle);
	}

	@AfterClass
	public static void close_Browser()
	{
		driver.quit();
	}
}
