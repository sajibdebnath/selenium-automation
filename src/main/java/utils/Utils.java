package utils;

import org.openqa.selenium.By;

public class Utils {
    /**
     * All element locators
     */
    public void printAllLocators() {
        System.out.println("Those are the element locators:");
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
        System.out.println("Those are the navigating page urls:");
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
        System.out.println("Those are the page titles:");
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
        System.out.println("Those are the window names:");
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
        System.out.println("Those are the elements visible text:");
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
        System.out.println("Those are the screenshots of failure test:");
        int i = 1;
        for (String screenshot : EventListener.screenshotsName) {
            System.out.println(i++ + ". " + screenshot);
        }
        System.out.println();
    }
}
