package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage extends DriverFactory {
	
	private WebDriver driver;
	private  ElementUtils elimentUtil;
	

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elimentUtil=new ElementUtils(driver);
		
	}
	
	
	
	
	private By registerLink=By.linkText("Register");
	private By searchText=By.id("search");
	private By emailText=By.id("input-email");
	private By passwordText=By.id("input-password");
	private By loginButton=By.xpath("//input[@value='Login']");
	
	
	
	public String loginPageTitle() {
		return driver.getTitle();
		}
	
	public String loginPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public void doLogin(String username,String password) {
		
		elimentUtil.doSendkeys(username, emailText);
		//driver.findElement(emailText).sendKeys(username);
		elimentUtil.doSendkeys(password, passwordText);
		//driver.findElement(passwordText).sendKeys(password);
		
		elimentUtil.doClick(loginButton);
		
		//driver.findElement(loginButton).click();
		
	}

}
