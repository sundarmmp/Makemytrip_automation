package com.journey;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.util.concurrent.TimeUnit;
import java.awt.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {
	
	public static void screenShot(WebDriver driver) throws IOException {
		TakesScreenshot scrshot = ((TakesScreenshot)driver);
		
		File sourceFile = scrshot.getScreenshotAs(OutputType.FILE);
		
		File targetFile = new File("C:\\Users\\DELL\\eclipse-workspace\\MakeMyTripWithoutLogin\\Screenshot\\screenshot.png");
		
		FileUtils.copyFile(sourceFile, targetFile);		
		
	}
	
public static void main(String[] args) throws InterruptedException, IOException {
	for (int i = 0; i < 1; i++) {
		
	System.setProperty("webdriver.chrome.driver", 
			"C:\\Users\\DELL\\eclipse-workspace\\MakeMyTripWithoutLogin\\driver\\chromedriver.exe");
	
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
		
	
	
	driver.get("http://greenstech.in/selenium-course-content.html");
	driver.findElement(By.xpath("//h2[@class = \"title mb-0 center\"]")).click();
	driver.findElement(By.xpath("//a[text() = 'CTS Interview Question ']")).click();
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	try {
//		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();
//	} catch (Exception e) {
//		System.out.println("Exception handled");
//	}
	

//	driver.findElement(By.xpath("//div[@id='SW']/div/div/ul/li[3]/div[2]")).click();
//	driver.findElement(By.id("fromCity")).click();	
//	driver.findElement(By.xpath("//input[@value='']")).sendKeys("chennai");
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(.,'Chennai International Airport')]"))).click();
//	
//	
//
//
//	wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open']")))).sendKeys("london heathrow");
//		
//	List<WebElement> dynamicdropdownlist = driver.findElements(By.xpath("//p[@class=\"font14 appendBottom5 blackText\"]"));
//	
//	for (int j = 0; j < dynamicdropdownlist.size(); j++) {
//		String text = dynamicdropdownlist.get(i).getText();
////		System.out.println("dynamicdropdownlist " + text);
//		if(text.contains("London - Heathrow Apt, United Kingdom")) {
//			dynamicdropdownlist.get(i).click();
//			break;
//		}
//		
//	}
//	}
				
}
}}


