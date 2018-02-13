package automation.framework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Account 
{
	private static String bookingstatus = "//table/tbody/tr[1]/td/div/b";
	private static String totalamount ="//table/tbody/tr[4]/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[3]";
	private static String booking_tbl_selector = "//*[@id='bookings']";
	private static WebElement element = null;
	
	public static void BookedFlights(WebDriver driver)
	{
		element = driver.findElement(By.xpath(booking_tbl_selector));
		List<WebElement> rows = element.findElements(By.className("row"));
		int bookingcount = rows.size();
		int xpathcounter = 0;
		
		for (int i=0; i<bookingcount; i++)
		{
			System.out.println(rows.get(i).getText());
			System.out.println(i);
			String actual;
			String expected;
			actual = rows.get(i).getText();
			expected = "Booking Code 1248";
			xpathcounter=xpathcounter+2;
				
				if (actual.contains(expected))
				{
					element = driver.findElement(By.xpath("//div[8]//div[2]/div/div[1]/div["+xpathcounter+"]/div[4]/a"));
					element.click();
					break;
				}
		}
		
	}
	
	public static void InvoiceDetails(WebDriver driver, String sParentWindow)
	{
		
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		element = driver.findElement(By.xpath(bookingstatus));
		String invoiceHeader = element.getText();
		System.out.println("Your Booking Status is " + invoiceHeader);
		element = driver.findElement(By.xpath(totalamount));
		//String amountLbl = totalAmt.getAttribute("innerHTML");
		System.out.println(element.getText());
		driver.close();
		driver.switchTo().window(sParentWindow);
	}
}
