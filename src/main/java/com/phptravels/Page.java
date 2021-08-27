package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface Page {
    WebDriver driver = null;

    void waitForDisplayed(WebElement element, int seconds);

    void waitForDisplayed(WebElement element);

    void waitForNotDisplayed(WebElement element, int seconds);

    void waitForNotDisplayed(WebElement element);

    void scrollTo(WebElement element);

    void scrollToDown(int pixel, int count);

    void scrollToBottom();

    void scrollToTop();

    void scrollToNextPage();
    
    void scrollToPreviousPage();

    void setValue(WebElement element, int value);

    void setValue(WebElement element, String text);
}