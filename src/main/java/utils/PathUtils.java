package utils;

public class PathUtils {
    private static final String USER_DIR = System.getProperty("user.dir");
    public static final String SCREENSHOT_FOLDER = USER_DIR + "/screenshots/";
    private static final boolean OS_WINDOWS = System.getProperty("os.name").contains("Windows");
    public static final String CHROME_DRIVER_PATH = USER_DIR + "/driver/"
            + (OS_WINDOWS ? "chromedriver.exe" : "chromedriver");
    public static final String FIREFOX_DRIVER_PATH = USER_DIR + "/driver/"
            + (OS_WINDOWS ? "geckodriver.exe" : "geckodriver");
}
