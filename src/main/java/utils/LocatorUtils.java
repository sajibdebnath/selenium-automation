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
        String element = webElement.toString().split("(?=id:\\s|name:\\s|selector:\\s|link text:\\s|xpath:\\s|" +
                "By.tagName:\\s|By.className:\\s|By.cssSelector:\\s|By.linkText:\\s|By.partialLinkText:\\s)")[1];

        String[] locator = StringUtils.removeEnd(element, "]").split(":\\s");
        String method = locator[0];
        if (method.equals("xpath"))
            return By.xpath(locator[1]);

        String selector = StringUtils.removeEnd(locator[1], "'");
        switch (method) {
            case "id":
                return By.id(selector);
            case "name":
                return By.name(selector);
            case "tagName":
                return By.tagName(selector);
            case "className":
                return By.className(selector);
            case "selector":
            case "cssSelector":
                return By.cssSelector(selector);
            case "linkText":
            case "link text":
                return By.partialLinkText(selector);
            default:
                return null;
        }
    }

    public static List<WebElement> getElements(WebDriver driver, WebElement element) {
        return getElements(driver, LocatorUtils.getByLocator(element));
    }

    public static List<WebElement> getElements(WebDriver driver, By by) {
        return driver.findElements(by);
    }

    public static List<WebElement> getElementsWithText(WebDriver driver, String text) {
        return driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
    }

    public static List<WebElement> getElementsWithExactText(WebDriver driver, String text) {
        return driver.findElements(By.xpath("//*[text()='" + text + "']"));
    }
}
