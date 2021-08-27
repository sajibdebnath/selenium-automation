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
    @FindBy(xpath = "//*[@class='input-items']")
    List<WebElement> searchByCity;


//    @FindBy(xpath = "//input[@class='select2-search__field' and @type='search']")
//    WebElement searchField;

    @FindBy(xpath = "//input[@class='select2-search__field' and @type='search']")
    WebElement searchField;

//    @FindBy(className = "select2-search__field")
//    WebElement searchField;

    @FindBy(xpath = "//*[contains(@class,'select2-results__option')]")
    List<WebElement> searchResults;
    @FindBy(id = "date")
    WebElement date;
    @FindBy(partialLinkText = "Travellers")
    WebElement travellerOption;
    @FindBy(id = "tours_adults")
    WebElement adultsNumber;
    @FindBy(id = "submit")
    List<WebElement> searchBtn;
    @FindBy(partialLinkText = "Details")
    List<WebElement> detailsLink;
    @FindBy(xpath = "//*[contains(text(),'Book Now')]")
    WebElement bookNowBtn;

//    @FindBy(xpath = "//li[@role='option']")
//    List<WebElement> options;

    @FindBy(xpath = "//li[@role='option' or contains(@class,'select2-results__option')]")
    List<WebElement> options;

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
        waitForDisplayed(searchByCity.get(2));
        searchByCity.get(2).click();
        searchField.sendKeys(destination);
        sleep(3);
        searchResults.get(0).click();
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

    public void clickSubmitBtn() {
        searchBtn.get(1).click();
    }

    public void searchTravelTour(String destination, String date, int adultNumber) {
        searchDestination(destination);
        setDate(date);
        clickTravelsOption();
        setAdultsNumber(adultNumber);
        clickSubmitBtn();
    }

    public void clickDetailsLink(String name) {
        scrollToDown(100, 7);
        WebElement tourName = driver.findElement(By.xpath("//li[@id='" + name + "']"));
        waitForDisplayed(tourName);
        tourName.findElement(By.linkText("Details")).click();
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

    public void selectOptionBySearch(String text) {
        searchField.sendKeys(text);
        selectOption(text);
    }

    public void selectOption(String text) {
        searchField.sendKeys(text);

        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(text)) {
                sleepInMillis(200);
                option.click();
                break;
            }
        }
    }

    public boolean signupBtnDisplayed() {
        return signUpLink.isDisplayed();
    }
}