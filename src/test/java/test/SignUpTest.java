package test;

import com.phptravels.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.SignupData;
import testdata.SiteData;

public class SignUpTest extends BaseTest {
    SignUpPage signUp;

    @Test
    public void signUpCustomerTest() {
        signUp = homePage.clickSignUpLink();
        signUp.fillCustomerInfo(
                SignupData.FIRST_NAME,
                SignupData.LAST_NAME,
                SignupData.PHONE_NUMBER,
                SignupData.EMAIL,
                SignupData.PASSWORD,
                SignupData.ACCOUNT_TYPE);
        signUp.clickSignUpBtn();

        Assert.assertEquals(signUp.successAlertMessage(), SiteData.SUCCESSFUL_SIGNUP_ALERT_MESSAGE);
        Assert.assertEquals(driver.getTitle(), SiteData.LOGIN_PAGE_TITLE);
    }
}
