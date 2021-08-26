package test;

import com.phptravels.DashboardPage;
import com.phptravels.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.UserData;

public class LoginTest extends BaseTest {
    @Test
    public void loginAndLogoutTest() {
        LoginPage login;
        DashboardPage dashboard;

        login = homePage.clickLoginLink();
        login.fillUserCredential(UserData.EMAIL, UserData.PASSWORD);
        dashboard = login.clickLogInBtn();
        Assert.assertEquals(dashboard.getAuthorTitle(), UserData.USERNAME);
        Assert.assertEquals(driver.getTitle(), "Dashboard - PHPTRAVELS");

        dashboard.clickLogout();
        Assert.assertTrue(login.signupBtnDisplayed());
        Assert.assertEquals(driver.getTitle(), "Login - PHPTRAVELS");
    }
}
