package test;

import com.phptravels.HomePage;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.BrowserUtils;
import utils.Utils;

import java.lang.reflect.Method;

public class BaseTest implements Test {
    HomePage homePage;
    EventFiringWebDriver driver;
    BrowserUtils browser = new BrowserUtils();

    @BeforeClass
    public void openBrowser() {
        driver = browser.startDriver();
    }

    @AfterClass
    public void closeBrowser() {
        browser.stopDriver();
    }

    @BeforeMethod
    public void navigateToHomePage(Method method) {
        driver.get(Utils.getString("BASE_URL"));
        homePage = new HomePage(driver);
        homePage.cookieHandler();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) {
        browser.getScreenShot(driver, result);
        new Utils().printEventLog();
    }

    @AfterSuite
    public void printCustomReports() {

    }
}
