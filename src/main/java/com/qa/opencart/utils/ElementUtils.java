package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	// Ctrl+O To Search the methods in any class
	// ctrl+shif+R Search anything

	private WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement doGetElement(By locator) {
		return driver.findElement(locator);
	}

	public void doSendkeys(String value, By locator) {
		doGetElement(locator).sendKeys(value);
	}

	public WebElement doGetElementThroughGetBy(String domElementValue, String locatorType) {
		return driver.findElement(getBy(domElementValue, locatorType));
	}

	public void doSendkeysThroughGetBy(String domElementValue, String locatorType, String value) {
		doGetElementThroughGetBy(domElementValue, locatorType).sendKeys(value);
	}

	public By getBy(String domElementValue, String locatorType) {
		By by = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			by = By.id(domElementValue);
			break;
		case "name":
			by = By.name(domElementValue);
			break;
		case "xpath":
			by = By.xpath(domElementValue);
			break;
		case "css":
			by = By.cssSelector(domElementValue);
			break;
		case "tagname":
			by = By.tagName(domElementValue);
			break;
		case "classname":
			by = By.className(domElementValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(domElementValue);
			break;
		case "linktext":
			by = By.linkText(domElementValue);
			break;
		default:
			System.out.println("Please provide the correct locator type");
		}
		return by;
	}

	public void doClick(By locator) {
		doGetElement(locator).click();
	}

	public void doClick2(String domElementValue, String locatorType) {
		doGetElementThroughGetBy(domElementValue, locatorType).click();
	}

	public String doGetText(By locator) {
		return doGetElement(locator).getText();
	}

	public void isDisplayed(By locator) {
		doGetElement(locator).isDisplayed();
	}

	public List<WebElement> doGetElementsList(By locator) {
		List<WebElement> list = driver.findElements(locator);
		return list;
	}

	public int doGetListSize(By locator) {
		return doGetElementsList(locator).size();
	}

	public List<String> doGetElementTextList(By locator) {
		List<WebElement> elementsList = doGetElementsList(locator);
		List<String> ar = new ArrayList<String>();
		for (WebElement e : elementsList) {
			String text = e.getText();
			ar.add(text);
		}

		return ar;

	}

	public ArrayList<String> doGetArrtributeList(By locator, String attrName) {
		List<WebElement> elementsList = doGetElementsList(locator);
		ArrayList<String> attrList = new ArrayList<String>();
		for (WebElement e : elementsList) {
			String attrText = e.getAttribute(attrName);
			attrList.add(attrText);
		}
		return attrList;
	}

	public void printElementValues(List<String> eleList) {
		for (String e : eleList) {
			System.out.println(e);
		}
	}

	public String getAttribute(By locator, String attributeName) {

		String attrValue = doGetElement(locator).getAttribute(attributeName);
		return attrValue;

	}

	public boolean isWebElementPresent(By locator) {
		if (doGetElementsList(locator).size() >= 1) {
			System.out.println("WebElement is present on the page");
			return true;
		} else {
			System.out.println("WebElement is not present");
			return false;
		}

	}

	public void doSelectByIndex(By locator, int index) {

		WebElement ele = doGetElement(locator);

		Select select = new Select(ele);
		select.deselectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		WebElement ele = doGetElement(locator);
		Select select = new Select(ele);
		select.selectByValue(value);
	}

	public void doSelectByVisibleText(By locator, String text) {
		WebElement ele = doGetElement(locator);
		Select select = new Select(ele);
		select.selectByVisibleText(text);
	}

	public ArrayList<String> doGetOptions(By locator) {
		ArrayList<String> list = new ArrayList<String>();
		WebElement ele = doGetElement(locator);
		Select select = new Select(ele);
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			list.add(text);

		}
		return list;
	}

	public void doSelectWithGetOptions(By locator, String value) {
		WebElement ele = doGetElement(locator);
		Select select = new Select(ele);
		List<WebElement> list = select.getOptions();

		for (WebElement e : list) {

			if (e.getText().equals(value)) {
				e.click();
				break;
			}

		}
	}

	public void doSelectWithFindElements(By locator, String value) {
		List<WebElement> list = driver.findElements(locator);
		for (WebElement ele : list) {
			if (ele.getText().equals(value)) {
				ele.click();
			}
		}
	}

	// ---------------------*******************Links------------------//

	public List<String> getLinkListOptions(By locator) {
		List<WebElement> list = driver.findElements(locator);
		List<String> linkTexts = new ArrayList<String>();
		for (WebElement e : list) {
			String text = e.getText();
			linkTexts.add(text);

		}
		return linkTexts;
	}

	public void linkListOptionsChoose(By locator, String value) {
		List<WebElement> list = driver.findElements(locator);

		for (WebElement e : list) {
			String text = e.getText();
			if (text.contains(value)) {
				e.click();
				break;
			}

		}
	}

	// -------------------------------Action
	// Utils****************-------------------------------------//

	public void doActionMoveToElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
	}

	public void doActionSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(locator), value).build().perform();
	}

	public void doContextClick(By locator) {
		Actions action = new Actions(driver);

		action.contextClick(driver.findElement(locator)).build().perform();
	}

	public void doMoveToElementClick(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).click().build().perform();

	}

	public void doDoubleClick(By locator) {
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(locator)).build().perform();
	}

	// ---------------------------------Waits-----------------------------------------------------------------
	// overloaded
	public void doSendKeys(By locator, int timeout, String Value) {
		WaitTillPresenceOfElement(locator, timeout).sendKeys(Value);
		;
	}

	// overloaded
	public void doSendKeys(By locator, int timeout, int sleepBetweenPolls, String value) {
		WaitTillPresenceOfElementWithPolling(locator, timeout, sleepBetweenPolls).sendKeys(value);
	}

	// overloaded
	public void doClick(By locator, int timeout) {
		WaitTillPresenceOfElement(locator, timeout).click();
	}

	public void implicitlyWait(int timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	public WebElement WaitTillPresenceOfElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void WaitTillAlertIsPresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void WaitTillElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void WaitTillTitleContains(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void WaitTillUrlContains(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(url));
	}

	public void WaitTillVisibilityOfElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement WaitTillPresenceOfElementWithPolling(By locator, int timeout, int sleepBetweenPolls) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout),
				Duration.ofSeconds(sleepBetweenPolls));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement waitForElementPresentUsingFluentWait(By locator, int timeout, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("Element is not found");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement waitForElementPresentUsingWebDriverWait(By locator, int timeout, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("Element is not found");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

}
