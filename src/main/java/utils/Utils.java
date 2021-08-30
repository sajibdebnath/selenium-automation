package utils;

import org.openqa.selenium.By;

import java.util.ResourceBundle;

public class Utils {
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");

    /**
     * Read text from config.properties
     *
     * @param key String
     * @return String
     */
    public static String getString(String key) {
        return bundle.getString(key);
    }

    /**
     * Read number from config.properties
     *
     * @param key String
     * @return int
     */
    public static int getInteger(String key) {
        return Integer.parseInt(bundle.getString(key));
    }

    /**
     * Read boolean value from config.properties
     *
     * @param key String
     * @return boolean
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(bundle.getString(key));
    }

    /**
     * Print all event logs
     */
    public void printEventLog() {
        if (EventListener.eventListener.size() > 0) {
            int i = 1;
            System.out.println("Event Logs:");
            for (String event : EventListener.eventListener)
                System.out.println(String.format("%d. %s", i++, event));
            System.out.println();
            EventListener.eventListener.clear();
        }
    }

    /**
     * All element locators
     */
    public void printLocators() {
        if (EventListener.locators.size() > 0) {
            int i = 1;
            System.out.println("Element locators:");
            for (By by : EventListener.locators)
                System.out.println(String.format("%d. %s", i++, by));
            System.out.println();
        }
    }

    /**
     * All navigator links
     */
    public void printNavigatorsLink() {
        if (EventListener.navigatesLink.size() > 0) {
            int i = 1;
            System.out.println("Navigating page urls:");
            for (String link : EventListener.navigatesLink)
                System.out.println(String.format("%d. %s", i++, link));
            System.out.println();
        }
    }

    /**
     * All pages title
     */
    public void printPagesTitle() {
        if (EventListener.pagesTitle.size() > 0) {
            int i = 1;
            System.out.println("Page titles:");
            for (String title : EventListener.pagesTitle)
                System.out.println(String.format("%d. %s", i++, title));
            System.out.println();
        }
    }

    /**
     * All windows name that are switching
     */
    public void printWindowsName() {
        if (EventListener.switchWindowsName.size() > 0) {
            int i = 1;
            System.out.println("Window names:");
            for (String window : EventListener.switchWindowsName)
                System.out.println(String.format("%d. %s", i++, window));
            System.out.println();
        }
    }

    /**
     * All elements visible text
     */
    public void printElementsText() {
        if (EventListener.elementsText.size() > 0) {
            int i = 1;
            System.out.println("Elements visible text:");
            for (String text : EventListener.elementsText)
                System.out.println(String.format("%d. %s", i++, text));
            System.out.println();
        }
    }

    /**
     * All screenshots name
     */
    public void printScreenShots() {
        if (EventListener.screenshotsName.size() > 0) {
            int i = 1;
            System.out.println("Screenshots of failure test:");
            for (String screenshot : EventListener.screenshotsName)
                System.out.println(String.format("%d. %s", i++, screenshot));
            System.out.println();
        }
    }
}
