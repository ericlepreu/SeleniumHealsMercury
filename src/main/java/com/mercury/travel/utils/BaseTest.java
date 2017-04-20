package com.mercury.travel.utils;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public abstract class BaseTest {
	//protected WebDriver driver = new ChromeDriver();
	protected WebDriver driver = new InternetExplorerDriver();
	//protected WebDriver driver = new FirefoxDriver();
	
	
	
	protected static String sharedLocation = "c:/Eclipse/AutomatedTests";
	
	protected static Logger logger;
	protected static Properties prop = new Properties();
	protected static String filePath; 
	protected static String envir;
	protected static String appDomain;
	
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\SeleniumGRID\\chromedriver.exe");
		
		//load properties file
		prop.load(new FileInputStream("config.properties"));
		
		//set the local properties
		setLocalProperties();
		
		//set the file path
		setFilePath();
		
		//initialize the logger
		setupLogger();
		//logger.info("Logger is setup ------------------------- ");
		
		//Determine App Domain and Selenium Grid instance
		setupAppDomain();
		
		//Initialize the Selenium Grid
		//System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\SeleniumGRID\\chromedriver.exe");
		System.setProperty("webdriver.internetexplorer.driver", "C:\\Eclipse\\SeleniumGRID\\IEDriverServer.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Eclipse\\SeleniumGRID\\geckodriver.exe");
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
			setLocalProperty("appDomain");
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
		filePath = sharedLocation + prop.getProperty("local_filePath");
	}
	
	/**
	 * Initialize the log4j logger. If the system property is empty (not provided 
	 * write to the local logs directory; otherwise log to a particular environment folder (QA, SRT, etc)
	 */
	private static void setupLogger() {
		String testMethodName = "TestLog_";
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new java.util.Date());
		System.setProperty("logfilename", testMethodName + timeStamp);		
		
		DOMConfigurator.configure("log4j.xml");
		
		//initialize logger
		logger = Logger.getLogger(filePath + "/logs/" + System.getProperty("logfilename"));
				
		logger.info("log file created: " + filePath + "/logs/" + System.getProperty("logfilename"));
	}

	/**
	 * Setup the AppDomain using the System.Property 'environment'
	 */
	private static void setupAppDomain() throws Exception {
		Properties properties = System.getProperties();
		Enumeration<?> e = properties.propertyNames();
		
//		//Only use code block below for debugging
//		logger.info("System property keys =====================");
//		while(e.hasMoreElements()) {
//			String key = (String) e.nextElement();
//			logger.info("System Property: " + key + " has value: " + properties.getProperty(key));
//		}
		
		String systemPropEnv = System.getProperty("environment");
		
		logger.info("System env var 'environment' is: " + systemPropEnv);
		//if System.getProperty("environment") is null, use the domain based on local_environment specified in config.properties
		if(systemPropEnv == null || "".equals(systemPropEnv.trim())) {
			envir = "LOCAL";
			logger.info("Using settings specified by 'local_environment' in config.properties, since System 'environment' variable is null");
			logger.info("Local Envir settings are set to: " + prop.getProperty("local_environment"));				
			
			appDomain = prop.getProperty("local_appDomain");
			logger.info("App Domain set to: " + appDomain + " ====================");
		} else if("QA".equals(systemPropEnv) || "SRT".equals(systemPropEnv)) {
			envir = systemPropEnv;
			logger.info("Using settings for " + envir + ", since System Property 'environment' is set to QA");
			logger.info("Domain set to: " + prop.getProperty(envir + "_appDomain"));	
		} else {
			throw new Exception("Error: System was not set to one of the legit values: null, '', QA or SRT");
		}
	}
}