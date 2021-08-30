package com.phptravels;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(partialLinkText = "Signup")
    private WebElement signUpLink;
    @FindBy(partialLinkText = "Login")
    private WebElement loginLink;
    @FindBy(id = "cookie_stop")
    private WebElement gotItBtn;
    @FindBy(id = "tours-tab")
    private WebElement tourTab;
    @FindBy(xpath = "//select[@id='tours_city']/following-sibling::*")
    private WebElement searchByCity;
    @FindBy(xpath = "//input[@class='select2-search__field' and @type='search']")
    private WebElement searchField;
    @FindBy(id = "date")
    private WebElement date;
    @FindBy(partialLinkText = "Travellers")
    private WebElement travellerOption;
    @FindBy(id = "tours_adults")
    private WebElement adultsNumber;
    @FindBy(id = "submit")
    private List<WebElement> searchBtns;
    @FindBy(partialLinkText = "Details")
    private List<WebElement> detailsLink;
    @FindBy(xpath = "//h2[contains(text(),'Search Tours in')]")
    private WebElement tourSearchResults;
    @FindBy(css = "ul>li[data-b='']")
    private List<WebElement> tourLists;
    @FindBy(xpath = "//*[contains(text(),'Book Now')]")
    private WebElement bookNowBtn;
    @FindBy(xpath = "//li[@role='option' or contains(@class,'select2-results__option')]")
    private List<WebElement> searchResults;
    private By searching = By.xpath("//li[@role='option' and text()='Searching…']");
    private By days = By.cssSelector("td[class='day ']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void cookieHandler() {
        if (driver.findElements(By.id("cookie_stop")).size() > 0)
            gotItBtn.click();
    }

    public void clickToursTab() {
        tourTab.click();
    }

    private void searchCity(String destination) {
        waitForVisibility(searchByCity);
        searchByCity.click();
        searchByText(destination);
        clickSearchResults(destination);
    }

    private void setDate(String tourDate) {
        date.click();
        for (WebElement day : driver.findElements(days)) {
            if (day.getText().equals(tourDate.split("-")[0])) {
                day.click();
                break;
            }
        }
//        date.sendKeys(tourDate);      //  sendKeys() method not working because it's readonly.
//        selectTourDate(days);         //  Another way to select tour date
    }

    private void setAdultsNumber(int number) {
        travellerOption.click();
        waitForVisibility(adultsNumber);
        setValue(adultsNumber, number);
    }

    private void clickSearchBtn() {
        for (WebElement element : searchBtns) {
            if (element.isDisplayed()) {
                element.click();
                break;
            }
        }
    }

    public void searchTravelTour(String destination, String date, int adultNumber) {
        searchCity(destination);
        setDate(date);
        setAdultsNumber(adultNumber);
        clickSearchBtn();
    }

    public void clickTourDetailsLink(String name) {
        waitForVisibility(tourSearchResults, 30);
        for (int i = 0; i < tourLists.size(); i++) {
            if (tourLists.get(i).getText().contains(name)) {
                scrollAndClick(detailsLink.get(i));
                break;
            }
        }
    }

    public SignUpPage clickSignUpLink() {
        waitForVisibility(signUpLink);
        signUpLink.click();
        return new SignUpPage(driver);
    }

    public LoginPage clickLoginLink() {
        if (driver.findElements(By.linkText("Logout")).size() > 0)
            new DashboardPage(driver).clickLogout();
        waitForVisibility(loginLink);
        loginLink.click();
        return new LoginPage(driver);
    }

    public BookingDetailsPage clickBookNowBtn() {
        waitForVisibility(bookNowBtn);
        scrollAndClick(bookNowBtn);
        return new BookingDetailsPage(driver);
    }

    void searchByText(String text) {
        scrollAndClick(searchField);
        searchField.sendKeys(text);
        waitForInvisibility(searching);
        clickSearchResults(text);
    }

    void clickSearchResults(String text) {
        for (WebElement option : searchResults) {
            if (option.getText().contains(text)) {
                option.click();
                break;
            }
        }
    }

    public boolean signupBtnDisplayed() {
        return signUpLink.isDisplayed();
    }

    /**
     * dynamically select available tour date
     *
     * @param locator
     */
    void selectTourDate(By locator) {
        List<WebElement> webElements = driver.findElements(locator);
        List<WebElement> days = new ArrayList<>();

        for (WebElement webElement : webElements) {
            if (!webElement.getText().isEmpty())
                days.add(webElement);
        }

        if (days.size() - 2 <= 0) {       // If current date is the last day of month then move to next month
            days.clear();
            List<WebElement> nexts = driver.findElements(By.cssSelector("th[class='next']"));
            for (WebElement next : nexts) {
                if (next.isDisplayed()) {
                    next.click();
                    break;
                }
            }
            for (WebElement webElement : driver.findElements(locator)) {
                if (!webElement.getText().isEmpty())
                    days.add(webElement);
            }
        }
        days.get(new Faker().random().nextInt(0, days.size() - 1)).click();
    }
}