package test;

import com.phptravels.DashboardPage;
import com.phptravels.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.SiteData;
import testdata.UserData;

public class LoginTest extends BaseTest {
    private LoginPage login;
    private DashboardPage dashboard;

    @Test
    public void loginAndLogoutTest() {
        login = homePage.clickLoginLink();
        login.fillUserCredential(UserData.EMAIL, UserData.PASSWORD);
        dashboard = login.clickLogInBtn();

        Assert.assertEquals(dashboard.getAuthorTitle(), UserData.USERNAME);
        Assert.assertEquals(driver.getTitle(), SiteData.DASHBOARD_PAGE_TITLE);

        dashboard.clickLogout();
        Assert.assertTrue(login.signupBtnDisplayed());
        Assert.assertEquals(driver.getTitle(), SiteData.LOGIN_PAGE_TITLE);
    }
}
