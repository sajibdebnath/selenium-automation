package utils;

import java.util.ResourceBundle;

public class Helpers {
    private static final String USER_DIR = System.getProperty("user.dir");
    public static final String SCREENSHOT_FOLDER = USER_DIR + "/screenshot/";
    private static final boolean OS_WINDOWS = System.getProperty("os.name").contains("Windows");
    public static final String CHROME_DRIVER_PATH = USER_DIR + "/driver/"
            + (OS_WINDOWS ? "chromedriver.exe" : "chromedriver");
    public static final String FIREFOX_DRIVER_PATH = USER_DIR + "/driver/"
            + (OS_WINDOWS ? "geckodriver.exe" : "geckodriver");
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");

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
