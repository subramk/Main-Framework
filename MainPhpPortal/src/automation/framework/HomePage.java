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
import java.util.Date;
import org.joda.time.Months;
import org.joda.time.Days;
import org.joda.time.LocalDate;


public class HomePage {
	public static WebDriver driver;
	private static String location_field_selector = "//*[@id='s2id_autogen10']";
	private static String location_option_selector = "/html/body/div[20]/ul";
	private static String checkin_field_selector = "checkin";
	private static String checkout_field_selector = "checkout";
	private static String adults_field_selector = "//*[@id='adults']";
	private static String child_field_selector = "//*[@id='child']";
	private static String search_btn_selector = "button.btn-danger.btn.btn-lg.btn-block";
	private static String home_page = "/html/body/div[6]/div/div/div[2]/a";
	private static WebElement element = null;
	private static int inMonths;
	
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
	
	public static void CheckinDatePicker(WebDriver driver, String sMyDateSearch)
	{	
		String sCalendarDay;
		String sMyDate;
		inMonths=0;
		
		//Used the joda.time library to identify todays date
		LocalDate start = new LocalDate(new Date());
		LocalDate end = new LocalDate(sMyDateSearch);
		
		//Then got the difference in months between todays date and my parameter date
		inMonths = Months.monthsBetween(start, end).getMonths();
		System.out.println("This is my starting date " + start.toString("yyyy-MM-dd") + " and this is the difference in months " + inMonths);
			
			//I used the difference in months to click on the next button
			for (int i=0;i<inMonths;++i)
			{
			element = driver.findElement(By.xpath("html/body/div[14]/div[1]/table/thead/tr[1]/th[3]"));
			element.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			
			//Extracted the day from the sting
			sCalendarDay = end.toString("yyyy-MM-dd").substring(8, 10);
			System.out.println(sCalendarDay);
			
			//Located my day from the date picker table
			element = driver.findElement(By.xpath("html/body/div[14]/div[1]/table/tbody"));
			List<WebElement> rows = element.findElements(By.tagName("TR"));
			element = driver.findElement(By.xpath("html/body/div[14]/div[1]/table/tbody/tr"));
			List<WebElement> cols = element.findElements(By.tagName("TD"));
 			
			for (int j=1; j<rows.size();++j)
			{
				for (int i=1;i<cols.size();++i)
				{
					element = driver.findElement(By.xpath("html/body/div[14]/div[1]/table/tbody/tr["+j+"]/td["+i+"]"));	
					sMyDate = element.getText();
					System.out.println(sMyDate);
					if (sMyDate.equals(sCalendarDay))
					{
						//Clicked on the day from the date picker calendar
						element = driver.findElement(By.xpath("html/body/div[14]/div[1]/table/tbody/tr["+j+"]/td["+i+"]"));
						element.click();
						break;
						
					}
				}
			}
	}
	public static void CheckoutDatePicker(WebDriver driver, String sMyDateSearch)
	{
		String sCalendarDay;
		String sMyDate;
		int outMonths =0;
		int diffMonths =0;
		int rCounter = 0;
		int cCounter =0;
		
		//Used the joda.time library to identify todays date
		LocalDate start = new LocalDate(new Date());
		//start = start.plusDays(1);
		
		//Then got the difference in months between todays date and my parameter date
		LocalDate end = new LocalDate(sMyDateSearch);
		
		outMonths = Months.monthsBetween(start, end).getMonths();
		System.out.println("This is my starting date " + start.toString("yyyy-MM-dd") + " and this is the difference in months " + outMonths);
		diffMonths = outMonths-inMonths;
		
			//I used the difference in months to click on the next button
			for (int i=0;i<diffMonths;++i)
			{
			element = driver.findElement(By.xpath("html/body/div[15]/div[1]/table/thead/tr[1]/th[3]"));
			element.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			
			//Extracted the day from the sting
			sCalendarDay = end.toString("yyyy-MM-dd").substring(8, 10);
			System.out.println(sCalendarDay);
			
			//Located my day from the date picker table
			element = driver.findElement(By.xpath("html/body/div[15]/div[1]/table/tbody"));
			List<WebElement> rows = element.findElements(By.tagName("TR"));
			element = driver.findElement(By.xpath("html/body/div[15]/div[1]/table/tbody/tr"));
			List<WebElement> cols = element.findElements(By.tagName("TD"));
 			
			for (int j=0; j<rows.size();++j)
			{
				rCounter= j+1;
				for (int i=0;i<cols.size();++i)
				{
					cCounter = i+1;
					element = driver.findElement(By.xpath("html/body/div[15]/div[1]/table/tbody/tr["+rCounter+"]/td["+cCounter+"]"));	
					sMyDate = element.getText();
					System.out.println(sMyDate);
					if (sMyDate.equals(sCalendarDay))
					{
						//Clicked on the day from the date picker calendar
						element = driver.findElement(By.xpath("html/body/div[15]/div[1]/table/tbody/tr["+rCounter+"]/td["+cCounter+"]"));
						element.click();
						break;
					}
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
	public static Select clk_AdultDropdownBox(WebElement element, WebDriver driver)
	{
		element = driver.findElement(By.xpath(adults_field_selector));
		Select selectAdult = new Select(element);
		return selectAdult;
	}
	public static Select clk_childDropdownBox(WebElement element, WebDriver driver)
	{
		element = driver.findElement(By.xpath(child_field_selector));
		Select selectAdult = new Select(element);
		return selectAdult;
	}
	
	public static void locationBtn()
	{
		WebElement searchBtn = driver.findElement(By.cssSelector(search_btn_selector));
		searchBtn.click();
	}

	public static void lnk_Back_to_Homepage()
	{
			WebElement element = driver.findElement(By.xpath(home_page));
			JavascriptExecutor jse = ((JavascriptExecutor)driver);
			jse.executeScript("arguments[0].click();", element);
		
	}
}
