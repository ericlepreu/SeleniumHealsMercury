package com.mercury.travel.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mercury.travel.builders.FlightDetails;
import com.mercury.travel.enums.ServiceClass;
import com.mercury.travel.pages.ConfirmationPage;
import com.mercury.travel.pages.HomePage;
import com.mercury.travel.pages.PurchasePage;
import com.mercury.travel.pages.ReservationPage;
import com.mercury.travel.pages.ReservationPage2;
import com.mercury.travel.utils.BaseTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

public class CreateReservationTests extends BaseTest {
	private HomePage pgHome;
	private ReservationPage pgReservation;
	private ReservationPage2 pg2Reservation;
	private PurchasePage pgPurchase;
	private ConfirmationPage pgConfirmation;
	
	/**
	 * Logs in http://newtours.demoaut.com/ site from Mercury, as mercury user, and validates FlightFinder page displays
	 * @throws Exception
	 */
	@Test
	public void loginTest() throws Exception {		
		try {
			pgHome = new HomePage(driver).get();
			pgHome.login();
			
			WebElement lblFlightFinder = driver.findElement(By.cssSelector("img[src*='flightfinder']"));
			assertTrue("Checking FlightFinder page is displayed", lblFlightFinder.isDisplayed());
		} catch (Exception e) {
			fail("Failed with error: " + e.getMessage());
		}
	}	  
	
	@Test
	public void PurchaseWithJustRequiredFieldsTest() throws Exception {
		try {
			pgHome = new HomePage(driver);
			pgReservation = new ReservationPage(driver);	
			pg2Reservation = new ReservationPage2(driver);
			pgPurchase = new PurchasePage(driver);
			pgConfirmation = new ConfirmationPage(driver);
			
			boolean isRoundTrip = false;
			String noOfPassengers = "3";
			String departingFrom = "London";
			String fromMonth = "February";
			String fromDay = "5";
			String arrivingIn = "Paris";
			String toMonth = "March";
			String toDay = "20";
					
			FlightDetails flightDetails = new FlightDetails.FlightDetailsBuilder(departingFrom, fromMonth, fromDay, arrivingIn, toMonth, toDay)
					.isRoundTrip(false)  //optional field
					.noOfPassengers("3")  //optional field
					.build();
			
			String passengerFirstName = "Eric";
			String passengerLastName = "Preudhomme";
			String ccNo = "1234123412341234";
			
			pgHome.get();
			pgHome.login();
			
			pgReservation.editFlightDetails(flightDetails);
			pgReservation.editPreferences(ServiceClass.First, "Unified Airlines");
			pgReservation.clickContinue();
			
			pg2Reservation.selectFlight();
			
			pgPurchase.enterPassengerInfo(passengerFirstName, passengerLastName);
			pgPurchase.enterCCInfo(ccNo);
			pgPurchase.securePurchase();
			
			Assert.assertTrue("FlightC Confirmation # label NOT displayed", pgConfirmation.isFlightConfirmationNoDisplayed());
			pgConfirmation.logout();
		} catch (Exception e) {
			throw new Exception("Failed with error: " + e.getMessage(), e);
		}
	}
	
}