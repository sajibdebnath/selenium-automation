package com.phptravels;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import utils.PropertiesUtils;

import java.time.Duration;

public class BasePage implements Page {
    WebDriver driver;
    JavascriptExecutor executor;

    /**
     * Instantiate fluent wait with Explicit time in seconds
     */
    FluentWait<String> wait = new FluentWait<>("")
            .withTimeout(Duration.ofSeconds(Integer.parseInt(PropertiesUtils.getString("EXPLICIT_WAIT"))))
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


    public void scrollDown(int pixel) {
        executor.executeScript("window.scrollBy(0, " + pixel + ")");
        sleepInMillis(PropertiesUtils.getInteger("SCROLL_INTERVAL"));
    }

    public void scrollAndClick(WebElement element) {
        try {
            element.click();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementClickInterceptedException e) {
            scrollToElement(element);
            try {
                element.click();
            } catch (org.openqa.selenium.ElementClickInterceptedException ignore) {
                executor.executeScript("arguments[0].click();", element);
            }
        }
    }

    public void scrollToElement(WebElement element) {
        for (int i = 0; i < PropertiesUtils.getInteger("SCROLL_COUNT_MAX"); i++) {
            try {
                if (element.isDisplayed())
                    break;
            } catch (org.openqa.selenium.NoSuchElementException ignore) {
                scrollDown(PropertiesUtils.getInteger("SCROLL_BY_PIXEL"));
            }
        }
    }
}