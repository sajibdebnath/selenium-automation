package com.phptravels;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ResourceBundle;

public class BasePage implements Page {
    WebDriver driver;
    JavascriptExecutor executor;
    ResourceBundle resource = ResourceBundle.getBundle("config");
    /**
     * Instantiate fluent wait with Explicit time in seconds
     */
    FluentWait<String> wait = new FluentWait<>("")
            .withTimeout(Duration.ofSeconds(Integer.parseInt(resource.getString("EXPLICIT_WAIT"))))
            .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class, NullPointerException.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Waiting for element to be displayed
     *
     * @param element
     * @param seconds
     */
    @Override
    public void waitForDisplayed(WebElement element, int seconds) {
        wait.withTimeout(Duration.ofSeconds(seconds)).until(a -> element.isDisplayed());
    }

    /**
     * Waiting for element to be displayed
     *
     * @param element
     */
    @Override
    public void waitForDisplayed(WebElement element) {
        wait.until(a -> element.isDisplayed());
    }

    /**
     * Waiting for element to be disappear
     *
     * @param locator
     * @param seconds
     */
    public void waitForDisappeared(By locator, int seconds) {
        wait.withTimeout(Duration.ofSeconds(seconds)).until(a -> {
            try {
                driver.findElement(locator);
                return false;
            } catch (org.openqa.selenium.NoSuchElementException e) {
                return true;
            }
        });
    }

    /**
     * Waiting for element to be disappear
     *
     * @param locator
     */
    @Override
    public void waitForDisappeared(By locator) {
        wait.until(a -> {
            try {
                driver.findElement(locator);
                return false;
            } catch (org.openqa.selenium.NoSuchElementException e) {
                return true;
            }
        });
    }


    public boolean isElementPresent(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Waiting some millis for not executing process
     *
     * @param milliseconds
     */
    public void sleepInMillis(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waiting some millis for not executing process
     *
     * @param seconds
     */
    public void sleep(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scrolling to the element position
     *
     * @param element
     */
    @Override
    public void scrollTo(WebElement element) {
        sleepInMillis(500);
        executor.executeScript("arguments[0].scrollIntoView(false);", element);
        sleepInMillis(500);
    }

    /**
     * Scrolling page down by pixel
     *
     * @param pixel
     * @param count
     */
    @Override
    public void scrollToDown(int pixel, int count) {
        for (int i = 0; i < count; i++) {
            executor.executeScript("window.scrollBy(0, " + pixel + ")");
            sleepInMillis(100);
        }
        sleep(1);
    }

    /**
     * Go to bottom of the page
     */
    @Override
    public void scrollToBottom() {
        sleepInMillis(500);
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Go to top of the page
     */
    @Override
    public void scrollToTop() {
        sleepInMillis(500);
        executor.executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Go to next page using keyboard space
     */
    @Override
    public void scrollToNextPage() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.chord(Keys.SPACE)).build().perform();
    }

    /**
     * Go to next page using shift keyboard space
     */
    @Override
    public void scrollToPreviousPage() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.chord(Keys.SHIFT, Keys.SPACE)).build().perform();
    }

    /**
     * Clear input filed and insert value
     *
     * @param element
     * @param value
     */
    @Override
    public void setValue(WebElement element, int value) {
        element.clear();
        element.sendKeys(Integer.toString(value));
    }

    /**
     * Clear input filed and insert text
     *
     * @param element
     * @param text
     */
    @Override
    public void setValue(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Select text value from dropdown options
     *
     * @param element
     * @param text
     */
    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /**
     * Select text value from dropdown options
     *
     * @param element
     * @param text
     */
    public void selectByValue(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByValue(text);
    }

    /**
     * Web element click by javascript executor
     *
     * @param element
     */
    public void clickUsingJsExecutor(WebElement element) {
        sleepInMillis(500);
        executor.executeScript("arguments[0].click();", element);
    }
}