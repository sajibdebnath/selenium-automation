package utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocatorUtils {
    /**
     * Converting WebElement to By locator
     *
     * @param webElement
     * @return By
     */
    public static By getByLocator(WebElement webElement) {
        String element = webElement.toString().split(
                "(?=\\sid:\\s|\\sname:\\s|\\sselector:\\s|\\slink text:|\\spartial link text:\\s|\\sxpath:\\s|" +
                        "By.id:\\s|By.name:\\s|By.tagName:\\s|By.className:\\s|By.cssSelector:\\s|" +
                        "By.linkText:\\s|By.partialLinkText:\\s|By.xpath:\\s)")[1];

        String[] locator = StringUtils.removeEnd(element, "]").split(":\\s");
        String method = locator[0].trim();
        if (method.equals("xpath"))
            return By.xpath(locator[1]);

        String selector = StringUtils.removeEnd(locator[1], "'");
        switch (method) {
            case "id":
            case "By.id":
                return By.id(selector);
            case "name":
            case "By.name":
                return By.name(selector);
            case "By.tagName":
                return By.tagName(selector);
            case "By.className":
                return By.className(selector);
            case "selector":
            case "By.cssSelector":
                return By.cssSelector(selector);
            case "link text":
            case "By.linkText":
                return By.linkText(selector);
            case "partial link text":
            case "By.partialLinkText":
                return By.partialLinkText(selector);
            case "By.xpath":
                return By.name(selector);
            default:
                System.out.println("Error! [" + method + "]");
                return null;
        }
    }

    public static List<WebElement> getElements(WebDriver driver, WebElement element) {
        return getElements(driver, LocatorUtils.getByLocator(element));
    }

    public static List<WebElement> getElements(WebDriver driver, By by) {
        return driver.findElements(by);
    }

    public static WebElement getElementByText(WebDriver driver, String text) {
        return getElementByText(driver, text, 0);
    }

    public static WebElement getElementByText(WebDriver driver, String text, int index) {
        return driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]")).get(index);
    }

    public static WebElement getElementByExactText(WebDriver driver, String text) {
        return getElementByExactText(driver, text, 0);
    }

    public static WebElement getElementByExactText(WebDriver driver, String text, int index) {
        return driver.findElements(By.xpath("//*[text()='" + text + "']")).get(index);
    }

    /**
     * All element locators
     */
    public static void printAvailableLocators() {
        int i = 1;
        System.out.println(EventListener.locators.size() > 0 ? "Available locators:" : "No locators");
        for (String locator : EventListener.locators)
            System.out.println(String.format("%d. %s", i++, locator));
        EventListener.locators.clear();
    }
}
