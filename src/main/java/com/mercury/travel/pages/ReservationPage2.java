package com.mercury.travel.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.mercury.travel.utils.BaseTest;

public class ReservationPage2 extends LoadableComponent<ReservationPage2> {
	public WebDriver driver;
	
	@FindBy(how = How.NAME, using = "reserveFlights")
	public WebElement btnContinue;
	
	// TODO
	//figure out how to work with table
	
	public ReservationPage2(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
	    assertTrue("Not on the issue entry page: " + url, url.endsWith("mercuryreservation2.php"));
	}

	@Override
	protected void load() {
		BaseTest.getLogger().info("== Loading Reservation page 2 ==");
		driver.get("http://newtours.demoaut.com/mercuryreservation2.php");		
	}
	
	public void selectFlight() throws Exception {		
		try {
			BaseTest.getLogger().info("== Clicking Continue btn ==");
			btnContinue.click();
		} catch (Exception e) {
			throw new Exception("Unable to click on Select Flight button: " + e.getMessage(), e);
		}
	}
}

