package automation.framework;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Test;
//import org.junit.internal.MethodSorter;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest {
	private static WebDriver driver = null;
	WebElement element;
	
	@BeforeClass
	public static void openBrowser()
	{
		driver = new org.openqa.selenium.firefox.FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	//Test that when a user enters an invalid password an error message is displayed
	@Test
	public void a_invalid_Password()
	{
		String notificationMsg = "Invalid Email or Password";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.get("http://www.phptravels.net");
		WebElement element = driver.findElement(By.xpath("//body//div[2]/ul/li[1]/a"));
		JavascriptExecutor jse = ((JavascriptExecutor)driver);
		jse.executeScript("arguments[0].click();", element);
		WebElement loginpage = driver.findElement(By.xpath("//body//div[2]/ul/li[1]/ul/li[1]/a"));
		loginpage.click();
		LoginPage.txtbx_UserName(driver).clear();
		LoginPage.txtbx_UserName(driver).sendKeys("user@phptravels.com");
		LoginPage.txtbx_Password(driver).clear();
		LoginPage.txtbx_Password(driver).sendKeys("demousera");
		LoginPage.btn_Login(driver).click();
		try
		{
			element = driver.findElement(By.xpath("/html/body/div[8]/div[1]/div[1]/form/div[1]/div[2]/div"));
		}
		catch (Exception e)
		{
			
		}
		Assert.assertEquals(notificationMsg, element.getText());
	}
	
	@Test
	public void b_invalid_Username()
	{
		String notificationMsg = "Invalid Email or Password";
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());

		LoginPage.txtbx_UserName(driver).clear();
		LoginPage.txtbx_UserName(driver).sendKeys("user@phptravels1.com");
		LoginPage.txtbx_Password(driver).clear();
		LoginPage.txtbx_Password(driver).sendKeys("demouser");
		LoginPage.btn_Login(driver).click();
		try
		{
			element = driver.findElement(By.xpath("/html/body/div[8]/div[1]/div[1]/form/div[1]/div[2]/div"));
		}
		catch (Exception e)
		{
			
		}
		Assert.assertEquals(notificationMsg, element.getText());	
	}
	
	@Test
	public void c_valid_UserCredentials()
	{
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage.txtbx_UserName(driver).clear();
		LoginPage.txtbx_UserName(driver).sendKeys("user@phptravels.com");
		LoginPage.txtbx_Password(driver).clear();
		LoginPage.txtbx_Password(driver).sendKeys("demouser");
		LoginPage.btn_Login(driver).click();
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
	public void d_UserLogout()
	{
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String expected = "MY ACCOUNT";
		WebElement element = driver.findElement(By.xpath("//body//div[2]/ul/li[1]/a"));
		JavascriptExecutor jse = ((JavascriptExecutor)driver);
		jse.executeScript("arguments[0].click();", element);
		WebElement logoutpage = driver.findElement(By.xpath("//div[4]/div/div/div[2]/ul/li[1]/ul/li[2]/a"));
		logoutpage.click();
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			element = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/ul/li[1]/a"));
			System.out.println(element.getText().trim());
			
		}
		catch (Exception e)
		{
			
		}
		
		Assert.assertEquals(expected, element.getText().trim());
	}
	
	@AfterClass
	public static void close_Browser()
	{
		driver.quit();
	}

}
