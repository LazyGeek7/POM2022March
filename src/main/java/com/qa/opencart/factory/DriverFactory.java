package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Swarsha
 * 
 * This method is used to initialise driver
 *
 */

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(Properties prop) {
		
		String browser=prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		else {
			System.out.println("Please provide the correct browser name .,Thank you!");
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		
		return driver;
		
	}
	
	
	public Properties init_Prop() {
		
		 prop=new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream("C:\\Swathi-QA\\Selenium2022March\\POM2022March\\src\\test\\resources\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
		
		
		
		
		
		
	}
	
	

}
