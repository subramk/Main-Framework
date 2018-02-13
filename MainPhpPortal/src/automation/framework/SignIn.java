package automation.framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import automation.framework.LoginPage;

public class SignIn {
	String sUsername = "user@phptravels.com";
	String sPassword = "demouser";
	
	public static void Execute(WebDriver driver, String sUsername, String sPassword)
	{
		LoginPage.txtbx_UserName(driver).clear();
		LoginPage.txtbx_UserName(driver).sendKeys(sUsername);
		LoginPage.txtbx_Password(driver).clear();
		LoginPage.txtbx_Password(driver).sendKeys(sPassword);
		LoginPage.btn_Login(driver).click();
	}

}
