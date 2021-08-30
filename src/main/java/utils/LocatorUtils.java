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

    /**
     * All element locators
     */
    public void printAllLocators() {
        int i = 1;
        for (By by : EventListener.locators) {
            System.out.println(i++ + ". " + by);
        }
        System.out.println();
    }

    /**
     * All navigator links
     */
    public void printAllNavigatorsLink() {
        int i = 1;
        for (String link : EventListener.navigatesLink) {
            System.out.println(i++ + ". " + link);
        }
        System.out.println();
    }

    /**
     * All pages title
     */
    public void printAllPagesTitle() {
        int i = 1;
        for (String title : EventListener.pagesTitle) {
            System.out.println(i++ + ". " + title);
        }
        System.out.println();
    }

    /**
     * All windows name that are switching
     */
    public void printAllwindowsName() {
        int i = 1;
        for (String window : EventListener.switchWindowsName) {
            System.out.println(i++ + ". " + window);
        }
        System.out.println();
    }

    /**
     * All elements visible text
     */
    public void printAllElementsText() {
        int i = 1;
        for (String text : EventListener.elementsText) {
            System.out.println(i++ + ". " + text);
        }
        System.out.println();
    }

    /**
     * All screenshots name
     */
    public void printAllScreenShotsName() {
        int i = 1;
        for (String screenshot : EventListener.screenshotsName) {
            System.out.println(i++ + ". " + screenshot);
        }
        System.out.println();
    }
}
