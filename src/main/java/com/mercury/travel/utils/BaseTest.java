package com.mercury.travel.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {
	protected WebDriver driver = new ChromeDriver();
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\Selenium\\ChromeDriver2.29\\chromedriver.exe");
	}
	
	@Before
	public void setupTest() throws Exception {
	}
	
	@After
	public void tearDownTest() throws Exception {
		driver.quit();
	}

}