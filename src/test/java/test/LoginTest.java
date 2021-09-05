package test;

import com.phptravels.DashboardPage;
import com.phptravels.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.Site;
import testdata.User;

public class LoginTest extends BaseTest {
    private LoginPage login;
    private DashboardPage dashboard;

    @Test
    public void loginAndLogoutTest() {
        login = homePage.clickLoginLink();
        login.setUserCredential(User.EMAIL, User.PASSWORD);
        dashboard = login.clickLogInBtn();

        Assert.assertEquals(dashboard.getAuthorTitle(), User.USERNAME);
        Assert.assertEquals(driver.getTitle(), Site.DASHBOARD_TITLE);

        dashboard.clickLogout();
        Assert.assertTrue(login.linkDisplayed());
        Assert.assertEquals(driver.getTitle(), Site.LOGIN_TITLE);
    }
}
