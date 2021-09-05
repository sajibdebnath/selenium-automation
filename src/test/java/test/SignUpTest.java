package test;

import com.phptravels.LoginPage;
import com.phptravels.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.Customer;
import testdata.Site;

public class SignUpTest extends BaseTest {
    private SignUpPage signUp;
    private LoginPage login;

    @Test
    public void signUpCustomerTest() {
        signUp = homePage.clickSignUpLink();
        signUp.setCustomerDetails(Customer.FNAME,
                Customer.LNAME, Customer.PHONE,
                Customer.EMAIL, Customer.PASSWORD,
                Customer.TYPE);
        login = signUp.clickSignUpBtn();

        Assert.assertEquals(login.successAlertMessage(), Site.SIGNUP_ALERT);
        Assert.assertEquals(driver.getTitle(), Site.LOGIN_TITLE);
    }
}
