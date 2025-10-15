package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;
import java.time.Duration;
import java.util.List;

/**
 * Base page class containing common methods for all page objects
 * All page classes should extend this class
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor - gets driver from DriverFactory automatically
     */
    public BasePage() {
        this.driver = Driver.getDriver();
        int explicitWait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    }


    /**
     * Wait for element to be visible
     * @param locator element locator
     * @return visible WebElement
     */
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     * @param locator element locator
     * @return clickable WebElement
     */
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for all elements to be visible
     * @param locator elements locator
     * @return list of visible WebElements
     */
    protected List<WebElement> waitForElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait for element to be invisible
     * @param locator element locator
     */
    protected void waitForInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Type text into input field
     * @param locator element locator
     * @param text text to type
     */
    protected void type(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Click on element
     * @param locator element locator
     */
    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    /**
     * Get text from element
     * @param locator element locator
     * @return element text
     */
    protected String getText(By locator) {
        return waitForElement(locator).getText();
    }

    /**
     * Check if element is displayed
     * @param locator element locator
     * @return true if element is displayed, false otherwise
     */
    protected boolean isDisplayed(By locator) {
        try {
            return waitForElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get element attribute value
     * @param locator element locator
     * @param attribute attribute name
     * @return attribute value
     */
    protected String getAttribute(By locator, String attribute) {
        return waitForElement(locator).getAttribute(attribute);
    }
    /**
     * Navigate to URL
     * @param url URL to navigate to
     */
    protected void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Get current URL
     * @return current page URL
     */
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title
     * @return page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}