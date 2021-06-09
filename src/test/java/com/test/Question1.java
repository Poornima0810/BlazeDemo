package com.test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Question1 {
	WebDriver driver;

	@BeforeMethod
	public void open() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://blazedemo.com/");
	}

	@Test
	public void order() {
		String title = driver.getTitle();
		System.out.println("Page Title: " + title);
		Assert.assertEquals("BlazeDemo", title);
		new Select(driver.findElement(By.name("fromPort"))).selectByIndex(1);
		new Select(driver.findElement(By.name("toPort"))).selectByIndex(1);
		driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
		driver.findElement(By.xpath("(//input[@class='btn btn-small'])[1]")).click();
		WebElement inputName = driver.findElement(By.id("inputName"));
		inputName.clear();
		inputName.sendKeys("George");
		WebElement inputAddress = driver.findElement(By.id("address"));
		inputAddress.clear();
		inputAddress.sendKeys("123 Church St");
		WebElement inputCity = driver.findElement(By.id("city"));
		inputCity.clear();
		inputCity.sendKeys("Florida");
		WebElement inputState = driver.findElement(By.id("state"));
		inputState.clear();
		inputState.sendKeys("New York");
		WebElement inputZip = driver.findElement(By.id("zipCode"));
		inputZip.clear();
		inputZip.sendKeys("123456");
		new Select(driver.findElement(By.id("cardType"))).selectByIndex(1);
		WebElement inputCard = driver.findElement(By.id("creditCardNumber"));
		inputCard.clear();
		inputCard.sendKeys("4567543245675432");
		WebElement inputMonth = driver.findElement(By.id("creditCardMonth"));
		inputMonth.clear();
		inputMonth.sendKeys("7");
		WebElement inputYear = driver.findElement(By.id("creditCardYear"));
		inputYear.clear();
		inputYear.sendKeys("2023");
		WebElement inputHolder = driver.findElement(By.id("nameOnCard"));
		inputHolder.clear();
		inputHolder.sendKeys("George", Keys.ENTER);
		String text = driver.findElement(By.tagName("h1")).getText();
		System.out.println(text);
		Assert.assertEquals("Thank you for your purchase today!", text);
		WebElement id = driver.findElement(By.xpath("//td[text()='Id']//following-sibling::td"));
		System.out.println("Order Id: " + id.getText());
	}

	@AfterMethod
	public void exit() throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(driver.getTitle() + "_" + time + ".jpg");
		FileHandler.copy(source, dest);
		driver.quit();
	}

}
