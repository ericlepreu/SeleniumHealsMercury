package com.mercury.travel.utils;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {
	protected WebDriver driver = new ChromeDriver();
	
	protected static Logger logger;
	protected static Properties prop = new Properties();
	protected static String filePath; 
	
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\Selenium\\ChromeDriver2.29\\chromedriver.exe");
		
//		//load properties file
//		prop.load(new FileInputStream("config.properties"));
//		
//		//set the local properties
//		setLocalProperties();
//		
//		//set the file path
//		setFilePath();
//		
//		//initialize the logger
//		setupLogger();
//		logger.info("Logger is setup ------------------------- ");
		
		
	}
	
	@Before
	public void setupTest() throws Exception {
	}
	
	@After
	public void tearDownTest() throws Exception {
		driver.quit();
	}
	
	/**
	 * Set all the local properties, in case the script is to be run locally (from Eclipse), 
	 * based on local_environment specified in config.properties 
	 */
	private static void setLocalProperties() {
		if(prop.getProperty("local_environment") != null) {
			setLocalProperty("applicationDomain");
			//setLocalProperty("local_filePath");
		}
	}
	
	/**
	 * Set a single local property (local_***) using the property for the environment specified in local_environment in config file
	 * @param propertyName
	 */
	private static void setLocalProperty(String propertyName) {
		String localEnvir = prop.getProperty("local_environment");
		String propertyValue = prop.getProperty(localEnvir + "_" + propertyName);
		if(propertyValue == null) {
			propertyValue = "";
		}
		prop.setProperty("local_" + propertyName, propertyValue);
	}
	
	/**
	 * for now just setup the file path for logging results for just running local
	 */
	private static void setFilePath() {
		//for now just running local
		filePath = prop.getProperty("local_filePath");
	}
	
	/**
	 * Initialize the log4j loggger. If the system property is empty (not provided 
	 * write to the local logs directory; otherwise log to a particular environment folder (QA, SRT, etc)
	 */
	public static void setupLogger() {
		//use the test method name + time stamp
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String testMethodName = ste[ste.length - 1].getMethodName();  
		//  see http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd.HH_mm_ss").format(new java.util.Date());
		System.setProperty("logfilename", testMethodName + timeStamp);		
	}

}