package com.phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface Page {
    WebDriver driver = null;

    void waitForDisplayed(WebElement element, int seconds);

    void waitForDisplayed(WebElement element);

    void waitForDisappeared(By locator, int seconds);

    void waitForDisappeared(By locator);

    void scrollDown(int pixel);

    void setValue(WebElement element, int value);

    void setValue(WebElement element, String text);
}