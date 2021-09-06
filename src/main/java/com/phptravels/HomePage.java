package com.phptravels;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WindowHandler;

import java.util.ArrayList;
import java.util.List;

import static utils.LocatorUtils.getElements;

public class HomePage extends BasePage {
    @FindBy(xpath = "//li[@role='option' and text()='Searching…']")
    WebElement searching;
    @FindBy(xpath = "//td[@class='day ']")
    WebElement days;
    @FindBy(css = "th[class='next']")
    WebElement nextBtn;
    @FindBy(partialLinkText = "Signup")
    private WebElement signUpLink;
    @FindBy(partialLinkText = "Login")
    private WebElement loginLink;
    @FindBy(id = "cookie_stop")
    private WebElement gotItBtn;
    @FindBy(id = "tours-tab")
    private WebElement tourTab;
    @FindBy(css = "[aria-labelledby='select2-tours_city-container']")
    private WebElement searchByCity;
    @FindBy(xpath = "//input[@class='select2-search__field' and @type='search']")
    private WebElement searchField;
    @FindBy(id = "date")
    private WebElement selectDate;
    @FindBy(partialLinkText = "Travellers")
    private WebElement travellerOption;
    @FindBy(id = "tours_adults")
    private WebElement adults;
    @FindBy(id = "submit")
    private List<WebElement> searchBtns;
    @FindBy(partialLinkText = "Details")
    private List<WebElement> detailsLink;
    @FindBy(css = "img[src*='tour_loading.gif']")
    private WebElement loadingImg;
    @FindBy(className = "sec__title_list")
    private WebElement searchTitle;
    @FindBy(css = "ul>li[data-b='']")
    private List<WebElement> tourLists;
    @FindBy(xpath = "//*[contains(text(),'Book Now')]")
    private WebElement bookNowBtn;
    @FindBy(xpath = "//li[@role='option' or contains(@class,'select2-results__option')]")
    private List<WebElement> searchResults;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void cookieHandler() {
        if (isPresent(gotItBtn))
            gotItBtn.click();
        new WindowHandler(driver).refreshPage();
        waitForInvisibility(gotItBtn);
    }

    public void clickToursTab() {
        tourTab.click();
    }

    public void setSearchText(String city) {
        waitAndClick(searchByCity);
        searchByText(city);
    }

    public void setTourDate(String date) {
        waitAndClick(selectDate);
        selectTourDate(days);
    }

    public void setAdults(int value) {
        waitAndClick(travellerOption);
        waitForVisibility(adults);
        setValue(adults, value);
    }

    public void searchTourCity(String city, String date, int value) {
        setSearchText(city);
//        setTourDate(date);
//        setAdults(value);
//        waitForInvisibility(loadingImg, 30);
//        clickSearchButton();
    }


    public void clickSearchButton() {
        for (WebElement element : searchBtns) {
            if (element.isDisplayed()) {
                element.click();
                break;
            }
        }
    }

    public void selectTour(String name) {
        waitForListToLoad(tourLists);
        for (int i = 0; i < tourLists.size(); i++) {
            if (tourLists.get(i).getText().contains(name)) {
                scrollAndClick(detailsLink.get(i));
                break;
            }
        }
    }

    public SignUpPage clickSignUpLink() {
        waitAndClick(signUpLink);
        return new SignUpPage(driver);
    }

    public LoginPage clickLoginLink() {
        if (isPresent(By.linkText("Logout")))
            new DashboardPage(driver).clickLogout();
        waitAndClick(loginLink);
        return new LoginPage(driver);
    }

    public BookingPage bookingTour() {
        waitForVisibility(bookNowBtn);
        scrollAndClick(bookNowBtn);
        return new BookingPage(driver);
    }

    void searchByText(String text) {
        searchField.sendKeys(text);
        waitForInvisibility(searching);
        click1stItemFromResults();
    }

    void click1stItemFromResults() {
        System.out.println(searchResults.size() + "============>");
        waitForListToLoad(searchResults);
        System.out.println(searchResults.size() + "============>");
        searchResults.get(0).click();
        sleep(5);
    }

    public boolean SingUplinkPresent() {
        return signUpLink.isDisplayed();
    }

    /**
     * dynamically select available tour selectDate
     *
     * @param element
     */
    void selectTourDate(WebElement element) {
        List<WebElement> webElements = getElements(driver, element);
        List<WebElement> days = new ArrayList<>();

        for (WebElement webElement : webElements) {
            if (!webElement.getText().isEmpty())
                days.add(webElement);
        }

        if (days.size() - 2 <= 0) {       // If current selectDate is the last day of month then move to next month
            days.clear();
            List<WebElement> nextBtns = getElements(driver, nextBtn);
            for (WebElement next : nextBtns) {
                if (next.isDisplayed()) {
                    next.click();
                    break;
                }
            }
            for (WebElement webElement : getElements(driver, element)) {
                if (!webElement.getText().isEmpty())
                    days.add(webElement);
            }
        }
        days.get(new Faker().random().nextInt(0, days.size() - 1)).click();
    }
}