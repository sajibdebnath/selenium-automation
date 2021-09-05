package utils;

import java.util.ResourceBundle;

public class BundleUtils {
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");
    private static final String USER_DIR = System.getProperty("user.dir");
    static final String SCREENSHOT_FOLDER = USER_DIR + "/screenshots/";
    private static final boolean OS_WINDOWS = System.getProperty("os.name").contains("Windows");
    static final String CHROME_DRIVER_PATH = USER_DIR + "/driver/" + (OS_WINDOWS ? "chromedriver.exe" : "chromedriver");
    static final String FIREFOX_DRIVER_PATH = USER_DIR + "/driver/" + (OS_WINDOWS ? "geckodriver.exe" : "geckodriver");

    public static String getString(String key) {
        return bundle.getString(key);
    }

    public static int getInteger(String key) {
        return Integer.parseInt(bundle.getString(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(bundle.getString(key));
    }
}
