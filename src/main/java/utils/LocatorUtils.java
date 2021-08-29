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
        String locator[] = element.toString().split(": ");
        if (locator[locator.length - 2].contains("xpath"))
            return By.xpath(StringUtils.removeEnd(locator[locator.length - 1], "]"));
        else {
            switch (locator[locator.length - 2].split(" '")[1]) {
                case "By.id":
                    return By.id(StringUtils.removeEnd(locator[locator.length - 1], "'"));
                case "By.name":
                    return By.name(StringUtils.removeEnd(locator[locator.length - 1], "'"));
                case "By.cssSelector":
                    return By.cssSelector(StringUtils.removeEnd(locator[locator.length - 1], "'"));
                case "By.className":
                    return By.className(StringUtils.removeEnd(locator[locator.length - 1], "'"));
                case "By.tagName":
                    return By.tagName(StringUtils.removeEnd(locator[locator.length - 1], "'"));
                case "By.linkText":
                    return By.linkText(StringUtils.removeEnd(locator[locator.length - 1], "'"));
                case "By.partialLinkText":
                    return By.partialLinkText(StringUtils.removeEnd(locator[locator.length - 1], "'"));
                default:
                    return null;
            }
        }
    }
}
