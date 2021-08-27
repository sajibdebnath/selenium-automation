package com.phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(partialLinkText = "Signup")
    WebElement signUpLink;
    @FindBy(partialLinkText = "Login")
    WebElement loginLink;
    @FindBy(id = "cookie_stop")
    WebElement gotItBtn;
    @FindBy(id = "tours-tab")
    WebElement tourTab;
    @FindBy(xpath = "//select[@id='tours_city']/following-sibling::*")
    WebElement searchByCity;
    @FindBy(xpath = "//input[@class='select2-search__field' and @type='search']")
    WebElement searchField;
    @FindBy(id = "date")
    WebElement date;
    @FindBy(partialLinkText = "Travellers")
    WebElement travellerOption;
    @FindBy(id = "tours_adults")
    WebElement adultsNumber;
    @FindBy(id = "submit")
    List<WebElement> searchBtns;
    @FindBy(partialLinkText = "Details")
    List<WebElement> detailsLink;
    @FindBy(xpath = "//*[contains(text(),'Book Now')]")
    WebElement bookNowBtn;

//    @FindBy(xpath = "//li[@role='option']")
//    List<WebElement> options;

    @FindBy(xpath = "//li[@role='option' or contains(@class,'select2-results__option')]")
    List<WebElement> searchResults;
    By searching = By.xpath("//li[@role='option' and text()='Searching…']");

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

    public void searchDestination(String destination) {
        waitForDisplayed(searchByCity);
        searchByCity.click();
        searchByText(destination);
        clickSearchResults(destination);
    }

    public void setDate(String dd) {
        date.sendKeys(dd);
    }

    public void clickTravelsOption() {
        waitForDisplayed(travellerOption);
        travellerOption.click();
    }

    public void setAdultsNumber(int number) {
        setValue(adultsNumber, number);
    }

    public void clickSearchBtn() {
        for (WebElement element : searchBtns) {
            if (element.isDisplayed()) {
                element.click();
                break;
            }
        }
    }

    public void searchTravelTour(String destination, String date, int adultNumber) {
        searchDestination(destination);
        setDate(date);
        clickTravelsOption();
        setAdultsNumber(adultNumber);
        clickSearchBtn();
    }

    public void clickDetailsLink(String name) {
        executor.executeScript("window.scrollBy(0, 100)");
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

    public void searchByText(String text) {
        searchField.sendKeys(text);
        waitForDisappeared(searching);
        clickSearchResults(text);
    }

    public void clickSearchResults(String text) {
        for (WebElement option : searchResults) {
            if (option.getText().toLowerCase().contains(text.toLowerCase())) {
                option.click();
                break;
            }
        }
    }

    public boolean signupBtnDisplayed() {
        return signUpLink.isDisplayed();
    }
}