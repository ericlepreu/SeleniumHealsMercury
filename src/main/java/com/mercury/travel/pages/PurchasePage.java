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

public class PurchasePage extends LoadableComponent<PurchasePage> {
	public WebDriver driver;
	Logger logger = BaseTest.getLogger();
	
	// TODO Validate table
	
	@FindBy(how = How.NAME, using = "passFirst0")
	public WebElement txtFirstName;
	
	@FindBy(how = How.NAME, using = "passLast0")
	public WebElement txtLastName;
	
	@FindBy(how = How.NAME, using = "pass.0.meal")
	public WebElement lstMeal;
	
	@FindBy(how = How.NAME, using = "creditnumber")
	public WebElement txtCCNo;
	
	@FindBy(how = How.NAME, using = "buyFlights")
	public WebElement btnSecurePurchase;
	
	// TODO other fields
	
	public PurchasePage(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
	    assertTrue("Not on the issue entry page: " + url, url.endsWith("mercurypurchase.php"));
	}

	@Override
	protected void load() {
		logger.info("== Loading Purchase page ==");
		driver.get("http://newtours.demoaut.com/mercurypurchase.php");		
	}
	
	public void enterPassengerInfo(String firstName, String lastName) throws Exception {
		try {
			logger.info("== Entering Passenger info ==");
			txtFirstName.sendKeys(firstName);
			txtLastName.sendKeys(lastName);
		} catch (Exception e) {
			throw new Exception("Unable to enter Passenger Info: " + e.getMessage(), e);
		}
	}
	
	public void enterCCInfo(String ccNo) throws Exception {
		try {
			logger.info("== Entering CC info ==");
			txtCCNo.sendKeys(ccNo);
		} catch (Exception e) {
			throw new Exception("Unable to enter CC No: " + e.getMessage(), e);
		}
	}
	
	public void securePurchase() throws Exception {
		try {
			logger.info("== Clicking on Secure Purchase button ==");
			btnSecurePurchase.click();
		} catch (Exception e) {
			throw new Exception("Unable to enter click on Secure Purchase btn " + e.getMessage(), e);
		}
	}		
}

