package com.phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface Page {
    WebDriver driver = null;

    void waitForVisibility(WebElement element, int seconds);

    void waitForVisibility(WebElement element);

    void waitForInvisibility(By locator, int seconds);

    void waitForInvisibility(By locator);

    void scrollDown(int pixel);

    void setValue(WebElement element, int value);

    void setValue(WebElement element, String text);
}