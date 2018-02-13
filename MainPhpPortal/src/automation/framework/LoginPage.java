package automation.framework;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private static WebElement element = null;
	
public static WebElement txtbx_UserName(WebDriver driver)
{
	element = driver.findElement(By.name("username"));
	return element;
}
	
public static WebElement txtbx_Password(WebDriver driver)
{
	element = driver.findElement(By.name("password"));
	return element;
}

public static WebElement btn_Login(WebDriver driver)
{
	element = driver.findElement(By.cssSelector("button.btn.btn-action.btn-lg.btn-block.loginbtn"));
	return element;
}
public static WebElement clk_MyAccount_btn(WebDriver driver)
{
	element = driver.findElement(By.xpath("//body//div[2]/ul/li[1]/a"));
	JavascriptExecutor jse = ((JavascriptExecutor)driver);
	jse.executeScript("arguments[0].click();", element);
	return element;
}

public static WebElement clk_Login_btn(WebDriver driver)
{
	element = driver.findElement(By.xpath("//body//div[2]/ul/li[1]/ul/li[1]/a"));
	return element;	
}
}
