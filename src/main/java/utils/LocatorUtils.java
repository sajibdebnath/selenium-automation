package utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocatorUtils {
    /**
     * Get locator from web element
     *
     * @param element
     * @return
     */
    public static By getLocator(WebElement element) {
        String[] type;
        String str = element.toString();
        type = element.toString().contains(" -> ") ?
                StringUtils.removeEnd(str.split(" -> ")[1], "]").split(": ") :
                StringUtils.removeEnd(str.split(" 'By")[1], "'").split(": ");
        switch (type[0]) {
            case "id":
            case "By.id":
                return By.id(type[1]);
            case "name":
            case "By.name":
                return By.name(type[1]);
            case "css selector":
            case "By.cssSelector":
                return By.cssSelector(type[1]);
            case "className":
            case "By.className":
                return By.className(type[1]);
            case "tagName":
            case "By.tagName":
                return By.tagName(type[1]);
            case "link text":
            case "By.linkText":
                return By.linkText(type[1]);
            case "partial link text":
            case "By.partialLinkText":
                return By.partialLinkText(type[1]);
            case "xpath":
            case "By.xpath":
                return By.xpath(type[1]);
            default:
                return null;
        }
    }

    public static List<WebElement> getElements(WebDriver driver, WebElement element) {
        return getElements(driver, LocatorUtils.getLocator(element));
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
