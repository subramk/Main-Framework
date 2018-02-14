package automation.framework;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
	public static WebDriver driver;
	private static String location_field_selector = "//*[@id='s2id_autogen10']";
	private static String location_option_selector = "/html/body/div[20]/ul";
	private static String checkin_field_selector = "checkin";
	private static String checkout_field_selector = "checkout";
	private static String adults_field_selector = "//*[@id='adults']";
	private static String child_field_selector = "//*[@id='child']";
	private static String search_btn_selector = "button.btn-danger.btn.btn-lg.btn-block";
	
	public static void hotelSearch(String mLocation)
	{
		String actual = "London, United Kingdom";
		String Expected;
		
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		WebElement location = driver.findElement(By.xpath(location_field_selector));
		location.click();
		location.sendKeys(mLocation);
		
		WebElement locationOption = driver.findElement(By.xpath(location_option_selector));
		List<WebElement> rows = locationOption.findElements(By.tagName("DIV"));
		int locationcount = rows.size();
		
		for (int i=0; i<locationcount;++i)
				{
					Expected = rows.get(i).getText();
					System.out.println(rows.get(i).getText());
					System.out.println(i);
					
						if (Expected.equals(actual))
						{
						WebElement locationOptionSelected = driver.findElement(By.xpath("//body/div[20]/ul/li/ul/li["+i+"]/div"));
						locationOptionSelected.click();
						break;
						}
				}
			
	}
	public static void checkinDate()
	{
		WebElement checkinTxt = driver.findElement(By.name(checkin_field_selector));
		checkinTxt.clear();
		checkinTxt.sendKeys("15/08/2018");
	}
	
	public static void checkoutDate()
	{
		WebElement checkoutTxt = driver.findElement(By.name(checkout_field_selector));
		checkoutTxt.clear();
		checkoutTxt.sendKeys("22/08/2018");
	}
	public static void adultSelection()
	{
		Select selectAdult = new Select (driver.findElement(By.xpath(adults_field_selector)));
		selectAdult.selectByValue("3");
	}
	
	public static void childSelection()
	{
		Select selectChild = new Select (driver.findElement(By.xpath(child_field_selector)));
		selectChild.selectByValue("2");
	}

	public static void locationBtn()
	{
		WebElement searchBtn = driver.findElement(By.cssSelector(search_btn_selector));
		searchBtn.click();
	}

	
}
