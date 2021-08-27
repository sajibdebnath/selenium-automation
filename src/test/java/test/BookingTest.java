package test;

import com.phptravels.BookingDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.SignupData;
import testdata.TourData;

public class BookingTest extends BaseTest {
    BookingDetailsPage detailsPage;

    @Test
    public void bookingTicketAsGuestUserTest() {
        homePage.clickToursTab();
        homePage.searchTravelTour(
                TourData.DESTINATION,
                TourData.TRAVEL_DATE,
                TourData.ADULTS);
        homePage.clickDetailsLink(TourData.SINGAPORE_DUCK_TOUR);
        detailsPage = homePage.clickBookNowBtn();

        detailsPage.fillCustomerDetailsInfo(
                SignupData.FIRST_NAME,
                SignupData.LAST_NAME,
                SignupData.PHONE_NUMBER,
                SignupData.EMAIL,
                SignupData.ADDRESS,
                SignupData.COUNTRY,
                SignupData.NATIONALITY);

//        detailsPage.fillTraveller_1_Info();
//        detailsPage.fillTraveller_2_Info();

        detailsPage.clickPayLater();
        detailsPage.clickAgreeBtn();
        detailsPage.clickBookingConfirm();

        Assert.assertEquals(driver.getTitle(), TourData.PAGE_TITLE_SINGAPORE_WEBSITE);
    }
}
