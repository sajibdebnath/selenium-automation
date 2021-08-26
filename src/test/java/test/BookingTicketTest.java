package test;

import com.github.javafaker.Faker;
import com.phptravels.BookingDetailsPage;
import com.phptravels.DashboardPage;
import com.phptravels.LoginPage;
import com.phptravels.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.UserData;
import utils.WindowHandler;

public class BookingTicketTest extends BaseTest {
    @Test
    public void bookingTicketAsGuestUserTest() {
        int adults = 2;
        String destination = "Singapore";
        String travellDate = "30-08-2021";
        String country = "Bangladesh";
        String nationality = "Bangladesh";
        String address = "House# 35, Road# 1, Mirpur-11";
        BookingDetailsPage bookingDetails;

        homePage.clickToursTab();
        homePage.searchTravellTour(destination, travellDate, adults);
        homePage.clickDetailsLink();
        bookingDetails = homePage.clickBookNowBtn();

        bookingDetails.fillBookingDetailsInfo(address, country, nationality);
//        bookingDetails.fillTraveller_1_Info();
//        bookingDetails.fillTraveller_2_Info();
        bookingDetails.selectPaymentOptionAndAgree();
        bookingDetails.clickBookingConfirm();

        WindowHandler handler = new WindowHandler(driver);
        handler.goBack();
        System.out.println(driver.getTitle());
    }
}
