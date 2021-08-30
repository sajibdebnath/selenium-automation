package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BrowserUtils {
    private EventFiringWebDriver driver;

    /**
     * Start webdriver
     */
    public EventFiringWebDriver startDriver() {
        WebDriver webDriver = getWebDriver(Utils.getString("BROWSER"));
        driver = getEventFiringWebDriver(webDriver);
        driver.manage().timeouts().implicitlyWait(Utils.getInteger("IMPLICITLY_WAIT"), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Close webdriver
     */
    public void stopDriver() {
        driver.quit();
    }

    /**
     * Instantiate webdriver
     */
    private WebDriver getWebDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                setChromeDriverProperty();
                return new ChromeDriver(getChromeOptions());

            case "firefox":
                setFirefoxDriverProperty();
                return new FirefoxDriver(getFirefoxOptions());

            default:
                System.out.println("[" + browser + "] is not a correct browser name.");
        }
        return null;
    }

    /**
     * get event firing webdriver
     */
    private EventFiringWebDriver getEventFiringWebDriver(WebDriver webDriver) {
        driver = new EventFiringWebDriver(webDriver);
        return driver.register(new EventListener());
    }

    /**
     * set driver property
     */
    private void setChromeDriverProperty() {
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, PathUtils.CHROME_DRIVER_PATH);
    }

    private void setFirefoxDriverProperty() {
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty("webdriver.gecko.driver", PathUtils.FIREFOX_DRIVER_PATH);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
    }

    /**
     * Get driver options
     *
     * @return
     */
    private ChromeOptions getChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--unlimited-storage");
        options.addArguments("--window-size=1325x744");
        options.setHeadless(Utils.getBoolean("HEADLESS"));
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(Utils.getBoolean("HEADLESS"));
        return options;
    }

    /**
     * Take screenshot if test got fail
     */
    public void getScreenShot(WebDriver driver, ITestResult result) {
        String screenshot = PathUtils.SCREENSHOT_FOLDER + result.getName() + ".png";
        System.setProperty("SCREENSHOT_NAME", screenshot);
        if (ITestResult.FAILURE == result.getStatus() && Utils.getBoolean("SCREENSHOT_ON_FAILURE")) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotFile, new File(screenshot));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
