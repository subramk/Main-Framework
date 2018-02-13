package automation.framework;
import org.openqa.selenium.By;
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


}
