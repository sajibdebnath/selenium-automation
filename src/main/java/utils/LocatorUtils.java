package utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LocatorUtils {
    /**
     * Get locator from web element
     *
     * @param element
     * @return
     */
    public static By getLocator(WebElement element) {
        String[] locator;
        String str = element.toString();
        locator = element.toString().contains(" -> ") ?
                str.split(" -> ")[1].replaceFirst("]", "").split(": ") :
                StringUtils.removeEnd(str.split(" '")[1], "'").split(": ");
        switch (locator[0]) {
            case "id":
            case "By.id":
                return By.id(locator[1]);
            case "name":
            case "By.name":
                return By.name(locator[1]);
            case "css selector":
            case "By.cssSelector":
                return By.cssSelector(locator[1]);
            case "className":
            case "By.className":
                return By.className(locator[1]);
            case "tagName":
            case "By.tagName":
                return By.tagName(locator[1]);
            case "partial link":
            case "By.linkText":
                return By.linkText(locator[1]);
            case "partial link text":
            case "By.partialLinkText":
                return By.partialLinkText(locator[1]);
            case "xpath":
            case "By.xpath":
                return By.xpath(locator[1]);
            default:
                return By.linkText("!!!!!!! " + "No suitable locator found." + " !!!!!!!");
        }
    }
}
