package utils;

import java.util.ResourceBundle;

public class Utils {
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");
    private static final String USER_DIR = System.getProperty("user.dir");
    static final String SCREENSHOT_FOLDER = USER_DIR + "/screenshots/";
    private static final boolean OS_WINDOWS = System.getProperty("os.name").contains("Windows");
    static final String CHROME_DRIVER_PATH = USER_DIR + "/driver/" + (OS_WINDOWS ? "chromedriver.exe" : "chromedriver");
    static final String FIREFOX_DRIVER_PATH = USER_DIR + "/driver/" + (OS_WINDOWS ? "geckodriver.exe" : "geckodriver");

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
