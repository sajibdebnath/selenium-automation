package test;

import com.phptravels.BookingPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.Customer;
import testdata.Tour;

public class BookingTest extends BaseTest {
    BookingPage detailsPage;

    @Test
    public void bookingTicketAsGuestUserTest() {
        homePage.clickToursTab();
        homePage.searchTourCity(Tour.CITY, Tour.DATE, Tour.ADULTS);
//        homePage.selectTour(Tour.TITLE);
//        detailsPage = homePage.bookingTour();
//        detailsPage.setCustomerDetails(
//                Customer.FNAME, Customer.LNAME, Customer.EMAIL, Customer.PHONE,
//                Customer.ADDRESS, Customer.COUNTRY, Customer.NATIONALITY);
//        detailsPage.fillTraveller_1(Customer.TITLE_1, Customer.TRAVELLER1_FNAME, Customer.TRAVELLER1_LNAME);
//        detailsPage.fillTraveller_2(Customer.TITLE_2, Customer.TRAVELLER2_FNAME, Customer.TRAVELLER2_LNAME);
//        detailsPage.selectPayment();
//        detailsPage.clickAgreeBtn();
//        detailsPage.confirmBooking();
//        Assert.assertEquals(driver.getTitle(), Tour.TITLE);
    }
}
