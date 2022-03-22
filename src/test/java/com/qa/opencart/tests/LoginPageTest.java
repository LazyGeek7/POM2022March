package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {
	
	
	@Test
	public void loginTitleTest() {
		String actualTitle=loginpage.loginPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginUrlTest() {
		String actualUrl=loginpage.loginPageUrl();
		Assert.assertTrue(actualUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
		}
	
	@Test
	public void loginTest() {
		loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

}
