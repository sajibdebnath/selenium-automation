package test;

import com.phptravels.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.DriverUtils;
import utils.LocatorUtils;
import utils.BundleUtils;

import java.lang.reflect.Method;

public class BaseTest implements Test {
    HomePage homePage;
    WebDriver driver;
    private DriverUtils driverUtils = new DriverUtils();

    @BeforeClass
    public void openBrowser() {
        boolean remote = System.getProperty("remote") != null && System.getProperty("remote").equals("true");
        String browser = System.getProperty("browser") == null ?
                BundleUtils.getString("BROWSER") : System.getProperty("browser");
        driver = driverUtils.startDriver(browser, remote);
    }

    @AfterClass
    public void closeBrowser() {
        driverUtils.stopDriver();
    }

    @BeforeMethod
    public void navigateToHomePage(Method method) {
        driver.get(BundleUtils.getString("BASE_URL"));
        homePage = new HomePage(driver);
        homePage.cookieHandler();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus() || BundleUtils.getBoolean("PASS_SCREENSHOT"))
            driverUtils.getScreenShot(driver, result.getName());

        if (BundleUtils.getBoolean("DEBUG_LOG"))
            LocatorUtils.printAvailableLocators();
    }
}
