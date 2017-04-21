package com.mercury.travel.pages;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.mercury.travel.utils.BaseTest;

public class ConfirmationPage extends LoadableComponent {
	public WebDriver driver;
	Logger logger = BaseTest.getLogger();
	
	@FindBy(how = How.CSS, using = ".frame_header_info table td b > font")
	public WebElement lblFlightConfirmation;
	
	@FindBy(how = How.CSS, using = "a[href='mercurysignoff.php'")
	public WebElement btnLogOut;
	
	// TODO other fields
	
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
	    assertTrue("Not on the Flight Confirmation page: " + url, url.endsWith("mercurypurchase2.php"));
	}

	@Override
	protected void load() {
		logger.info("== Loading Confirmation Page ==");
		driver.get("http://newtours.demoaut.com/mercurypurchase.php");		
	}	

	public boolean isFlightConfirmationNoDisplayed() throws Exception {
		try {
			String flightConfirmationText = lblFlightConfirmation.getText();
			boolean isLabelStartingWithFlightConfirmationNo = flightConfirmationText.trim().startsWith("Flight Confirmation #");
			return isLabelStartingWithFlightConfirmationNo;
		} catch (Exception e) {
			throw new Exception("Exception while verifying if Confirmation is displayed: " + e.getMessage(), e);
		}
	}
	
	public void logout() {
		BaseTest.getLogger().info("========= Logging out ===============");
		btnLogOut.click();
	}
}
