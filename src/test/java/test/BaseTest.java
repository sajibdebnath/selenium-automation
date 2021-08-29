package test;

import com.phptravels.HomePage;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.DirPathUtils;
import utils.EventReporter;
import utils.PropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BaseTest implements Test {
    HomePage homePage;
    EventFiringWebDriver driver;

    @BeforeClass
    public void startDriver() {
        WebDriver webDriver = getWebDriver(PropertiesUtils.getString("BROWSER"));
        driver = getEventFiringWebDriver(webDriver);
        driver.manage().timeouts().implicitlyWait(PropertiesUtils.getInteger("IMPLICIT_WAIT"), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    /**
     * Instantiate webdriver
     *
     * @param browser
     * @return
     */
    WebDriver getWebDriver(String browser) {
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
     *
     * @param webDriver
     * @return
     */
    private EventFiringWebDriver getEventFiringWebDriver(WebDriver webDriver) {
        driver = new EventFiringWebDriver(webDriver);
        if (PropertiesUtils.getBoolean("EVENT_LOG"))
            return driver.register(new EventReporter());
        return driver;
    }

    /**
     * set chrome driver property
     */
    private void setChromeDriverProperty() {
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, DirPathUtils.CHROME_DRIVER_PATH);
    }

    /**
     * Get chrome driver options
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
        options.setHeadless(PropertiesUtils.getBoolean("HEADLESS"));
        return options;
    }

    /**
     * set firefox driver property
     */
    private void setFirefoxDriverProperty() {
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty("webdriver.gecko.driver", DirPathUtils.FIREFOX_DRIVER_PATH);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
    }

    /**
     * Get firefox driver options
     *
     * @return
     */
    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(PropertiesUtils.getBoolean("HEADLESS"));
        return options;
    }

    /**
     * Close all window and quit browser
     */
    @AfterClass
    public void stopDriver() {
        driver.quit();
    }

    /**
     * Navigate to the website home page
     */
    @BeforeMethod
    public void navigateToHomePage(Method method) {
        driver.get(PropertiesUtils.getString("BASE_URL"));
        homePage = new HomePage(driver);
        homePage.cookieHandler();
    }

    /**
     * Take screenshot on test scenario fail
     *
     * @param result
     */
    @AfterMethod
    public void takeScreenShot(ITestResult result) {
        String screenShotName = getScreenshotName(result.getName());
        if (ITestResult.FAILURE == result.getStatus() && PropertiesUtils.getBoolean("SCREENSHOT_ON_FAILURE")) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotFile, new File(screenShotName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generate screenshot name
     *
     * @param name
     * @return
     */
    private String getScreenshotName(String name) {
        String screenshot = DirPathUtils.SCREENSHOT_FOLDER + name + ".png";
        System.setProperty("SCREENSHOT_NAME", screenshot);
        return screenshot;
    }
}
