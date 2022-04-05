package com.journey;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.RenderingHints.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetAllWindowHandles;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JourneyBooking {
	
	private void fromCity(WebDriver driver, WebDriverWait wait) throws Exception{
				
		driver.findElement(By.xpath("//div[@id='SW']/div/div/ul/li[3]/div[2]")).click();
		driver.findElement(By.id("fromCity")).click();	
		driver.findElement(By.xpath("//input[@value='']")).sendKeys("chennai");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(.,'Chennai International Airport')]"))).click();
					
	}
	
	private void toCity(WebDriver driver, WebDriverWait wait) throws Exception {
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open']")))).sendKeys("london heathrow");
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"To\"]"))).sendKeys("london heathrow");
		}
		
		Thread.sleep(3000);
		List<WebElement> dynamicdropdownlist = driver.findElements(By.xpath("//p[@class=\"font14 appendBottom5 blackText\"]"));
		
		for (int i = 0; i < dynamicdropdownlist.size(); i++) {
			String text = dynamicdropdownlist.get(i).getText();
			
			System.out.println("to city : " +text );
		
			if(text.contains("London - Heathrow Apt, United Kingdom")) {
				dynamicdropdownlist.get(i).click();
				break;
			}
			
		}
	}
	
	private void datePicker(WebDriver driver, WebDriverWait wait) throws Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 90);		
		String date = dateFormat.format(calendar.getTime());		
		System.out.println("Current date is : " + dateFormat.format(new Date()));
		System.out.println("Date after 90 days is : " + date );
		
		driver.findElement(By.xpath("//span[contains(text(),'DEPARTURE')]")).click();
		Thread.sleep(1000);
		boolean flag = false;
		while (!flag) {			
			List<WebElement> datepicker = driver.findElements(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'" + date + "')]"));
			if (datepicker.size() > 0) {
				driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'" + date + "')]"))
						.click();
				flag = true;
				}
			else {
				
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			}
		}
	}
	
	public void flightBooking(WebDriver driver) throws InterruptedException {
		// Search
		driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
		
		Thread.sleep(1000);

		List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class=\"appendBottom15\"]"));
		boolean flag = false;
		for (WebElement  searchResult : searchResults) {
			if(searchResult.findElement(By.xpath("//*[contains(@id,'bookbutton-RKEY')]")).isDisplayed()) {				
				searchResult.findElement(By.xpath("//*[contains(@id,'bookbutton-RKEY')]")).click();
				flag = true;
				break;
			}				
		}
		if (flag) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[text() = 'Continue']")).click();
		} else {	
	 
			System.out.println("Entered in smart value fare");
			List<WebElement> results = driver.findElements(By.xpath("//div[@class=\"smartValueFares\"]"));
			for (WebElement  searchResult : results) {
				if(searchResult.findElement(By.xpath("//*[contains(@id,'bookbutton-RKEY')]")).isDisplayed()) {				
					searchResult.findElement(By.xpath("//*[contains(@id,'bookbutton-RKEY')]")).click();
					
					break;
				}				
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[text() = 'Book Now']")).click();
		}
	
	
		
	}
		public void passengerDetails(WebDriver driver, WebDriverWait wait) throws InterruptedException {
		String parenthandle = driver.getWindowHandle();
		Set<String> multiplewindow = driver.getWindowHandles();
		System.out.println("Number of windows: " + multiplewindow.size());
		
		for (String childwindow : multiplewindow) {			
			if(!parenthandle.equals(multiplewindow)) {
				driver.switchTo().window(childwindow);			
			}
		}
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"rtaImpinfo-consent\"]/div/span[1]/span/span")).click();
			
		} catch (Exception e) {
			System.out.println("There is no consent checkbox");
		}
		WebElement insuranceoptbutton = driver.findElement(By.xpath("//*[@id=\"INSURANCE\"]/div/div[4]/div[1]/label/span[1]"));
		insuranceoptbutton.click();
		Thread.sleep(2000);
		WebElement adultcheckbox = driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[3]/button"));
		adultcheckbox.click();
		Thread.sleep(2000);
		WebElement firstname = driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[1]/div[1]/div/input"));
		firstname.click();
		firstname.sendKeys("Sathish");
		WebElement lastname = driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div/input"));
		lastname.click();
		lastname.sendKeys("Kumar");
		WebElement gender = driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[1]/div[3]/div/div/label[1]"));
		gender.click();
		WebElement mobilenumber = driver.findElement(By.xpath("//*[@id=\"Mobile No\"]/div/input"));
		mobilenumber.click();
		mobilenumber.sendKeys("1234567890");
		WebElement mailid = driver.findElement(By.xpath("//*[@id=\"Email\"]/div/input"));
		mailid.click();
		mailid.sendKeys("welcome@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"Email\"]/div/div/div")).click();
		
		 WebElement nationality = driver.findElement(By.xpath("//div[text() = 'Nationality']"));
		 nationality.click();
		Thread.sleep(3000);
		driver.findElement(By.id("react-select-3-option-0")).click();
		Thread.sleep(3000);
		 
		
		WebElement dateOfBirth = driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/div/div/div[1]/div[1]"));
		dateOfBirth.click();
		 Thread.sleep(3000);
		 driver.findElement(By.id("react-select-4-option-0")).click();
		//driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[2]/div[4]/div/div/div[1]/div/div/div/div[1]/div[1]")).click();
				
		
		WebElement monthOfBirth = driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[1]/div[1]"));
		monthOfBirth.click();
		 Thread.sleep(3000);
		 driver.findElement(By.id("react-select-5-option-0")).click();
		//driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[2]/div[4]/div/div/div[2]/div/div/div/div[1]/div[1]")).click();
		
		
		WebElement yearOfBirth = driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[1]/div[1]"));
		yearOfBirth.click();
		Thread.sleep(2000);
		driver.findElement(By.id("react-select-6-option-0")).click();
		
		WebElement passPortNum = driver.findElement(By.xpath("//input[@placeholder=\"Passport No\"]"));
		passPortNum.click();
		passPortNum.sendKeys("A112233446");
		
		driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div/div/div[1]/div[1]")).click();
		Thread.sleep(2000);
		WebElement passportIssuingCountry = driver.findElement(By.id("react-select-7-option-0"));
		passportIssuingCountry.click();
		

			
		driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[3]/div[3]/div/div/div[1]/div/div/div/div[1]/div[1]")).click();
		WebElement passPortExpirtDate = driver.findElement(By.id("react-select-8-option-3"));
		passPortExpirtDate.click();
		
		driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[3]/div[3]/div/div/div[2]/div/div/div/div[1]/div[1]")).click();
		Thread.sleep(3000);
		WebElement passPortExpirtMonth = driver.findElement(By.id("react-select-9-option-4"));
		passPortExpirtMonth.click();
		
		driver.findElement(By.xpath("//*[@id=\"wrapper_ADULT\"]/div[2]/div[2]/div/div[2]/div/div[3]/div[3]/div/div/div[3]/div/div/div/div[1]/div[1]")).click();
		Thread.sleep(3000);
		WebElement passPortExpiryYear = driver.findElement(By.id("react-select-10-option-3"));
		passPortExpiryYear.click();
		
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]"))).click();
		try {
		driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
		} catch (Exception e) {
			System.out.println("Confirm button not appeared");
		}
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//button[contains(text(), 'Continue')]")).click();
		try {
			 new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Yes, Please')]")));
			 driver.findElement(By.xpath("//button[contains(text(), 'Yes, Please')]")).click();
		} catch (Exception e) {
			System.out.println("Element not present");
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'Continue')]")).click();
		Thread.sleep(3000);
		try {
			 new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Yes, Please')]")));
			 driver.findElement(By.xpath("//button[contains(text(), 'Yes, Please')]")).click();
		} catch (Exception e) {
			System.out.println("Element not present");
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'Continue')]")).click();
		Thread.sleep(2000);
		try {
			 new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'CONTINUE ANYWAY')]")));

			driver.findElement(By.xpath("//button[contains(text(), 'CONTINUE ANYWAY')]")).click();
		}catch (Exception e) {
				System.out.println("Element not present");
			}
	}
	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\DELL\\eclipse-workspace\\MakeMyTripWithoutLogin\\driver\\chromedriver.exe");

		for (int i = 0; i < 1; i++) {

			WebDriver driver = new ChromeDriver();
			driver.get("https://www.makemytrip.com/");
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 20);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			try {
				driver.findElement(By.xpath("//span[@class='langCardClose']")).click();
			}
			catch (NoSuchElementException e) {
				System.out.println("Language change not needed");
			}
			
			
			JourneyBooking journeyBooking = new JourneyBooking();
			journeyBooking.fromCity(driver, wait);
			journeyBooking.toCity(driver, wait);
			journeyBooking.datePicker(driver, wait);
			journeyBooking.flightBooking(driver);
			journeyBooking.passengerDetails(driver, wait);

		}

	}

	

}
