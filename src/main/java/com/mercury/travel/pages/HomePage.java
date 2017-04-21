package com.mercury.travel.pages;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.mercury.travel.utils.BaseTest;

public class HomePage extends LoadableComponent<HomePage> {
	public WebDriver driver;
	
	@FindBy(how = How.NAME, using = "userName")
	public WebElement txtUserName;
	
	@FindBy(how = How.NAME, using = "password")
	public WebElement txtPwd;
	
	@FindBy(how = How.NAME, using = "login")
	public WebElement btnSignIn;
	
	@FindBy(how = How.CSS, using = "img[src*='flightfinder']")
	public WebElement imgFeaturedDestination;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;	
		//driver.get("http://newtours.demoaut.com/mercurywelcome.php");		
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
	    assertTrue("Not on the MercuryWelcome Home page: " + url, url.endsWith("mercurywelcome.php"));
	}

	@Override
	protected void load() {
		driver.get("http://newtours.demoaut.com/mercurywelcome.php");		
	}
	
	public void login() throws Exception {
		
		try {
			BaseTest.getLogger().info("Logging in...");
			txtUserName.sendKeys("mercury");
			txtPwd.sendKeys("mercury");
			btnSignIn.click();			
		} catch (Exception e) {
			throw new Exception("Unable to login: " + e.getMessage(), e);
		}
	}
}
