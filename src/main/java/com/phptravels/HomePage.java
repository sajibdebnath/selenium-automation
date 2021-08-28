package com.phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = "//*[contains(text(),'Book Now')]")
    private WebElement bookNowBtn;
    @FindBy(xpath = "//li[@role='option' or contains(@class,'select2-results__option')]")
    private List<WebElement> searchResults;
    private By searching = By.xpath("//li[@role='option' and text()='Searching…']");

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
        searchByCity.click();
        searchByText(destination);
        clickSearchResults(destination);
    }

    private void setDate(String dd) {
        date.click();
        date.sendKeys(dd);
    }

    private void setAdultsNumber(int number) {
        waitForDisplayed(travellerOption);
        travellerOption.click();
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

    public void clickDetailsLink(String name) {
        executor.executeScript("window.scrollBy(0, 100)");
        waitForDisplayed(detailsLink.get(0));
        detailsLink.get(0).click();
    }

    public SignUpPage clickSignUpLink() {
        signUpLink.click();
        return new SignUpPage(driver);
    }

    public LoginPage clickLoginLink() {
        if (driver.findElements(By.linkText("Logout")).size() > 0)
            new DashboardPage(driver).clickLogout();
        loginLink.click();
        return new LoginPage(driver);
    }

    public BookingDetailsPage clickBookNowBtn() {
        scrollTo(bookNowBtn);
        bookNowBtn.click();
        return new BookingDetailsPage(driver);
    }

    void searchByText(String text) {
        searchField.sendKeys(text);
        waitForDisappeared(searching);
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
}