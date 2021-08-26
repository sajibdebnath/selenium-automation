package test;

import com.github.javafaker.Faker;
import com.phptravels.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.UserData;

public class SignUpTest extends BaseTest {
    @Test
    public void signUpCustomerTest() {
        SignUpPage signUp;
        String email = new Faker().internet().emailAddress();
        String password = UserData.PASSWORD;

        signUp = homePage.clickSignUpLink();
        signUp.fillCustomerInfo(email, password);
        signUp.clickSignUpBtn();

        Assert.assertEquals(signUp.successAlertMessage(), "Signup successfull please login.");
        Assert.assertEquals(driver.getTitle(), "Login - PHPTRAVELS");
    }
}
