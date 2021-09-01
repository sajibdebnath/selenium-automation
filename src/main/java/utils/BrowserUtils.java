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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
        WebDriver webDriver = getWebDriver();
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
    private WebDriver getWebDriver() {
        String browser = Utils.getString("BROWSER");
        String remoteUrl = String.format("http://%s/wd/hub", Utils.getString("HUB_ADDRESS"));
        boolean remote = System.getProperty("remote") != null && System.getProperty("remote").equals("true");
        if (remote) {
            try {
                switch (browser) {
                    case "chrome":
                        return new RemoteWebDriver(new URL(remoteUrl), getChromeOptions());
                    case "firefox":
                        return new RemoteWebDriver(new URL(remoteUrl), getFirefoxOptions());
                    default:
                        System.out.println("[" + browser + "] is not a correct browser name.");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else {
            switch (browser) {
                case "chrome":
                    return getChromeDriver();
                case "firefox":
                    return getFirefoxDriver();
                default:
                    System.out.println("[" + browser + "] is not a correct browser name.");
            }
        }
        return null;
    }

    /**
     * set properties and start chrome webdriver
     */
    private ChromeDriver getChromeDriver() {
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, PathUtils.CHROME_DRIVER_PATH);
        return new ChromeDriver(getChromeOptions());
    }

    /**
     * set properties and start firefox webdriver
     */
    private FirefoxDriver getFirefoxDriver() {
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty("webdriver.gecko.driver", PathUtils.FIREFOX_DRIVER_PATH);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        return new FirefoxDriver(getFirefoxOptions());
    }

    /**
     * get event firing webdriver
     */
    private EventFiringWebDriver getEventFiringWebDriver(WebDriver webDriver) {
        driver = new EventFiringWebDriver(webDriver);
        return driver.register(new EventListener());
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
