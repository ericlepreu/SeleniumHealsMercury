package com.mercury.travel.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.mercury.travel.builders.FlightDetails;
import com.mercury.travel.controls.ListControl;
import com.mercury.travel.enums.ServiceClass;

public class ReservationPage extends LoadableComponent<ReservationPage> {
	public WebDriver driver;
	
	@FindBy(how = How.CSS, using = "input[name='tripType'][value='roundtrip']")
	public WebElement rdoRoundTrip;
	
	@FindBy(how = How.CSS, using = "input[name='tripType'][value='oneway']")
	public WebElement rdoOneWay;
	
	@FindBy(how = How.NAME, using = "passCount")
	public WebElement lstPassengersNo;
	
	@FindBy(how = How.NAME, using = "fromPort")
	public WebElement lstDepartingFrom;
	
	@FindBy(how = How.NAME, using = "fromMonth")
	public WebElement lstFromMonth;
	
	@FindBy(how = How.NAME, using = "fromDay")
	public WebElement lstFromDay;
	
	@FindBy(how = How.NAME, using = "toPort")
	public WebElement lstArrivingIn;
	
	@FindBy(how = How.NAME, using = "toMonth")
	public WebElement lstToMonth;
	
	@FindBy(how = How.NAME, using = "toDay")
	public WebElement lstToDay;
	
	@FindBy(how = How.CSS, using = "input[name='servClass'][value='Coach']")
	public WebElement rdoEconomy;
	
	@FindBy(how = How.CSS, using = "input[name='servClass'][value='Business']")
	public WebElement rdoBusiness;
	
	@FindBy(how = How.CSS, using = "input[name='servClass'][value='First']")
	public WebElement rdoFirstClass;
	
	@FindBy(how = How.NAME, using = "airline")
	public WebElement lstAirline;	
	
	@FindBy(how = How.NAME, using = "findFlights")
	public WebElement btnContinue;
	
	
	public ReservationPage(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
	    assertTrue("Not on the issue entry page: " + url, url.endsWith("mercuryreservation.php"));
	}

	@Override
	protected void load() {
		driver.get("http://newtours.demoaut.com/mercuryreservation.php");		
	}
	
	//edits the flight details section using the FlightDetails object, built using the Builder patter
	// see https://jlordiales.me/2012/12/13/the-builder-pattern-in-practice/ 
	// for details on Builder pattern
	public void editFlightDetails(FlightDetails flightDetails) throws Exception {
		try {
			boolean isRoundTrip = flightDetails.getIsRoundTrip();
			String noOfPassengers = flightDetails.getNoOfPassengers();
			String departingFrom = flightDetails.getDepartFrom();
			String fromMonth = flightDetails.getFromMonth();
			String fromDay = flightDetails.getFromDay();
			String arrivingIn = flightDetails.getArriveIn();
			String toMonth = flightDetails.getToMonth();
			String toDay = flightDetails.getToDay();
			
			if(isRoundTrip && rdoOneWay.isSelected()) {
				rdoRoundTrip.click();
			}
			else if(!isRoundTrip && rdoRoundTrip.isSelected()) {
				rdoOneWay.click();
			}		
			
			ListControl.selectByVisibleText(lstPassengersNo, noOfPassengers);		
			ListControl.selectByVisibleText(lstDepartingFrom, departingFrom);		
			ListControl.selectByVisibleText(lstFromMonth, fromMonth);
			ListControl.selectByVisibleText(lstFromDay, fromDay);
			ListControl.selectByVisibleText(lstArrivingIn, arrivingIn);
			ListControl.selectByVisibleText(lstToMonth, toMonth);
			ListControl.selectByVisibleText(lstToDay, toDay);
		} catch (Exception e) {
			throw new Exception("Unable to enter the Flight Details data: " + e.getMessage(), e);
		}
	}
	
	public void editPreferences(ServiceClass serviceClass, String airline) throws Exception {
		try {
			switch(serviceClass) {
				case Coach: rdoEconomy.click();
				case Business: rdoBusiness.click();
				case First: rdoFirstClass.click();
				case KeepCurrentSelection: ; //do nothing
			}
			
			Select dropdownAirline = new Select(lstAirline); 
			dropdownAirline.selectByVisibleText(airline);
		} catch (Exception e) {
			throw new Exception("Unable to edit the Preferences Section: " + e.getMessage(), e);
		}
	}
	
	public void clickContinue() throws Exception {		
		try {
			btnContinue.click();
		} catch (Exception e) {
			throw new Exception("Unable to click on the Contineu button: " + e.getMessage(), e);
		}
	}
}
