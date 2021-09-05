package test;

import com.phptravels.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.DriverUtils;
import utils.Utils;

import java.lang.reflect.Method;

public class BaseTest implements Test {
    HomePage homePage;
    WebDriver driver;
    private DriverUtils driverUtils = new DriverUtils();

    @BeforeClass
    public void openBrowser() {
        boolean remote = System.getProperty("remote") != null && System.getProperty("remote").equals("true");
        String browser = System.getProperty("browser") == null ?
                Utils.getString("BROWSER") : System.getProperty("browser");
        driver = driverUtils.startDriver(browser, remote);
    }

    @AfterClass
    public void closeBrowser() {
        driverUtils.stopDriver();
    }

    @BeforeMethod
    public void navigateToHomePage(Method method) {
        driver.get(Utils.getString("BASE_URL"));
        homePage = new HomePage(driver);
        homePage.cookieHandler();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus() || Utils.getBoolean("PASS_SCREENSHOT"))
            driverUtils.getScreenShot(driver, result.getName());

        if (Utils.getBoolean("DEBUG_LOG"))
            Utils.printAvailableLocators();
    }
}
