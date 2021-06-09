package com.test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Question2 {
	WebDriver driver;

	@BeforeMethod
	public void open() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://advantageonlineshopping.com/#/");

	}

	@Test
	public void checkout() throws IOException {
		String title = driver.getTitle();
		System.out.println("Page Title: " + title);
		Assert.assertEquals(" Advantage Shopping", title);
		driver.findElement(By.id("menuUser")).click();
		WebElement registerBtn = driver.findElement(By.linkText("CREATE NEW ACCOUNT"));
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(registerBtn));
		registerBtn.click();
		WebElement inputName = driver.findElement(By.name("usernameRegisterPage"));
		inputName.clear();
		Random random = new Random();
		inputName.sendKeys("George" + random.nextInt(100));
		WebElement inputEmail = driver.findElement(By.name("emailRegisterPage"));
		inputEmail.clear();
		inputEmail.sendKeys("george@gmail.com");
		WebElement inputPass = driver.findElement(By.name("passwordRegisterPage"));
		inputPass.clear();
		inputPass.sendKeys("George123");
		WebElement inputCPass = driver.findElement(By.name("confirm_passwordRegisterPage"));
		inputCPass.clear();
		inputCPass.sendKeys("George123");
		WebElement inputFName = driver.findElement(By.name("first_nameRegisterPage"));
		inputFName.clear();
		inputFName.sendKeys("George");
		WebElement inputLName = driver.findElement(By.name("last_nameRegisterPage"));
		inputLName.clear();
		inputLName.sendKeys("David");
		WebElement inputPhone = driver.findElement(By.name("phone_numberRegisterPage"));
		inputPhone.clear();
		inputPhone.sendKeys("9876543211");
		new Select(driver.findElement(By.name("countryListboxRegisterPage"))).selectByVisibleText("United States");
		WebElement inputCity = driver.findElement(By.name("cityRegisterPage"));
		inputCity.clear();
		inputCity.sendKeys("Florida");
		WebElement inputAddress = driver.findElement(By.name("addressRegisterPage"));
		inputAddress.clear();
		inputAddress.sendKeys("123 Church St");
		WebElement inputState = driver.findElement(By.name("state_/_province_/_regionRegisterPage"));
		inputState.clear();
		inputState.sendKeys("New York");
		WebElement inputCode = driver.findElement(By.name("postal_codeRegisterPage"));
		inputCode.clear();
		inputCode.sendKeys("234543");
		driver.findElement(By.name("i_agree")).click();
		driver.findElement(By.id("register_btnundefined")).click();
		driver.findElement(By.id("tabletsImg")).click();
		driver.findElement(By.id("17")).click();
		driver.findElement(By.name("save_to_cart")).click();
		driver.findElement(By.id("checkOutPopUp")).click();
		driver.findElement(By.id("next_btn")).click();
		WebElement userName = driver.findElement(By.name("safepay_username"));
		userName.clear();
		userName.sendKeys("George");
		WebElement pass = driver.findElement(By.name("safepay_password"));
		pass.clear();
		pass.sendKeys("George123");
		driver.findElement(By.id("pay_now_btn_SAFEPAY")).click();

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
