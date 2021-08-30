package com.phptravels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorUtils;
import utils.Utils;

import java.time.Duration;

public class BasePage implements Page {
    protected WebDriver driver;
    private JavascriptExecutor executor;

    /**
     * Instantiate fluent wait with wait in seconds
     */
    FluentWait<String> wait = new FluentWait<>("")
            .withTimeout(Duration.ofSeconds(Utils.getInteger("FLUENT_WAIT")))
            .pollingEvery(Duration.ofMillis(Utils.getInteger("POLLING_DELAY")));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void waitAndClick(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void waitAndClick(WebElement element, int seconds) {
        waitForVisibility(element, seconds);
        element.click();
    }

    protected void waitForVisibility(WebElement element) {
        waitForVisibility(element, Utils.getInteger("FLUENT_WAIT"));
    }

    protected void waitForVisibility(WebElement element, int seconds) {
        wait.withTimeout(Duration.ofSeconds(seconds))
                .ignoring(NoSuchElementException.class, NullPointerException.class)
                .until(a -> element.isDisplayed());
    }

    protected void waitForInvisibility(WebElement element) {
        waitForInvisibility(element, Utils.getInteger("FLUENT_WAIT"));
    }

    protected void waitForInvisibility(WebElement element, int seconds) {
        wait.withTimeout(Duration.ofSeconds(seconds))
                .until(a -> {
                    try {
                        return !element.isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        return true;
                    }
                });
    }

    protected boolean isPresent(WebElement element) {
        return isPresent(element, Utils.getInteger("IMPLICITLY_WAIT"));
    }

    protected boolean isPresent(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        try {
            return wait.until(a -> driver.findElements(LocatorUtils.getLocator(element)).size() > 0);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    protected void setValue(WebElement element, int value) {
        setValue(element, Integer.toString(value));
    }

    protected void setValue(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected void selectByValue(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByValue(text);
    }


    protected void scrollDown(int pixel) {
        executor.executeScript("window.scrollBy(0, " + pixel + ")");
        sleepInMillis(Utils.getInteger("SCROLL_INTERVAL_DELAY"));
    }


    protected void scrollAndClick(WebElement element) {
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

    protected void scrollToElement(WebElement element) {
        for (int i = 0; i < Utils.getInteger("SCROLL_COUNT_MAX"); i++) {
            try {
                if (element.isDisplayed())
                    break;
            } catch (org.openqa.selenium.NoSuchElementException ignore) {
                scrollDown(Utils.getInteger("SCROLL_BY_PIXEL"));
            }
        }
    }

    protected void sleep(int seconds) {
        sleepInMillis(1000 * seconds);
    }

    protected void sleepInMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}