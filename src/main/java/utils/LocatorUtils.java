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
    public static By getBy(WebElement webElement) {
        String[] locator = webElement.toString().split("(?=id:\\s|name:\\s|tagName:\\s|xpath:\\s|" +
                "className:\\s|selector:\\s|cssSelector:\\s|link text:\\s|linkText:\\s)");
        String[] type = StringUtils.removeEnd(locator[1], "]").split(":\\s");

        if (!type[0].equals("xpath"))
            StringUtils.removeEnd(type[1], "'");

        switch (type[0]) {
            case "id":
                return By.id(type[1]);
            case "name":
                return By.name(type[1]);
            case "tagName":
                return By.tagName(type[1]);
            case "className":
                return By.className(type[1]);
            case "selector":
                System.out.println(type[0]);
            case "cssSelector":
                return By.cssSelector(type[1]);
            case "linkText":
            case "link text":
                return By.partialLinkText(type[1]);
            case "xpath":
                return By.xpath(type[1]);
            default:
                return null;
        }
    }

    public static List<WebElement> getElements(WebDriver driver, WebElement element) {
        return getElements(driver, LocatorUtils.getBy(element));
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
