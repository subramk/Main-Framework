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
	private static WebElement element = null;
	
	public static void hotelSearch(WebDriver driver)
	{
		String actual = "London, United Kingdom";
		String Expected;
		
		element = driver.findElement(By.xpath(location_option_selector));
		List<WebElement> rows = element.findElements(By.tagName("DIV"));
		int locationcount = rows.size();
		
		for (int i=0; i<locationcount;++i)
				{
					Expected = rows.get(i).getText();
					System.out.println(rows.get(i).getText());
					System.out.println(i);
					
						if (Expected.equals(actual))
						{
							element = driver.findElement(By.xpath("//body/div[20]/ul/li/ul/li["+i+"]/div"));
							element.click();
						break;
						}
				}
	}
	public static WebElement txtLocation(WebDriver driver)
	{
		element = driver.findElement(By.xpath(location_field_selector));
		return element;
	}
	public static WebElement txtCheckinDate(WebDriver driver)
	{
		element = driver.findElement(By.name(checkin_field_selector));
		return element;
	}
	public static WebElement txtCheckoutDate(WebDriver driver)
	{
		element = driver.findElement(By.name(checkout_field_selector));
		return element;
	}
	public static WebElement btnlocation(WebDriver driver)
	{
		element = driver.findElement(By.cssSelector(search_btn_selector));
		return element;
	}
	
	public static void  adultSelection(String adultNum)
	{
		Select selectAdult = new Select (driver.findElement(By.xpath(adults_field_selector)));
		selectAdult.selectByValue(adultNum);
	}
	
	public static WebElement childSelection(WebDriver driver, String childNum, WebElement Select)
	{
		element = driver.findElement(By.xpath(child_field_selector));
		Select dropdown = new Select (element);
		return Select;
		//selectChild.selectByValue(childNum);
	}

	public static void locationBtn()
	{
		WebElement searchBtn = driver.findElement(By.cssSelector(search_btn_selector));
		searchBtn.click();
	}

	
}
