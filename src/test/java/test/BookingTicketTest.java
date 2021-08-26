package test;

import com.github.javafaker.Faker;
import com.phptravels.RegistrationPage;
import com.phptravels.SignUpPage;
import org.testng.annotations.Test;
import utils.WindowHandler;

public class BookingTicketTest extends BaseTest {
    @Test
    public void bookingTicketAsGuestUserTest() {
        RegistrationPage registration;
        homePage.clickToursTab();
        homePage.searchDestination("Singapore");            //  Singapore
        homePage.setDate("30-08-2021");
        homePage.clickTravellsOption();
        homePage.setAdultsNumber("2");
        homePage.clickSubmitBtn();
        homePage.clickDetailsLink();
        registration = homePage.clickBookNowBtn();

        registration.setFirstName(new Faker().name().firstName());
        registration.setLastName(new Faker().name().lastName());
        registration.setEmail(new Faker().internet().emailAddress());
        registration.setPhoneNumber(new Faker().number().digits(10));
        registration.setAddress("House# 35, Road# 1, Mirpur-11");
//        registration.setCountryName("Bangladesh");
//        registration.setNationalityName("Bangladesh");

        registration.setTravellerTitle("MR");
        registration.setTravellerFirstName(new Faker().name().firstName());
        registration.setTravellerLastName(new Faker().name().firstName());

        registration.setTraveller2Title("MR");
        registration.setTraveller2FirstName(new Faker().name().firstName());
        registration.setTraveller2LastName(new Faker().name().firstName());

        registration.clickPayLater();
        registration.clickAgreeBtn();
        registration.clickBookingConfirm();

        WindowHandler handler = new WindowHandler(driver);
        handler.goBack();
    }

    @Test
    public void signUpCustomer() {
        SignUpPage signUp;
        signUp = homePage.clickSignUpBtn();
        signUp.setFirstName(new Faker().name().firstName());
        signUp.setLastName(new Faker().name().lastName());
        signUp.setEmail(new Faker().internet().emailAddress());
        signUp.setPhoneNumber(new Faker().number().digits(10));
        signUp.setPassword("pass123456");
//        signUp.clickAccountType();
        signUp.clickSignUpBtn();
    }
}
