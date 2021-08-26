package com.phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(partialLinkText = "Signup")
    WebElement signUpLink;

    @FindBy(id = "cookie_stop")
    WebElement gotItBtn;

    @FindBy(id = "tours-tab")
    WebElement tourTab;

    @FindBy(xpath = "//*[@class='input-items']")
    List<WebElement> searchByCity;

    @FindBy(className = "select2-search__field")
    WebElement searchField;

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


    private void clickGotItBtn() {
        if (driver.findElements(By.id("cookie_stop")).size() > 0)
            gotItBtn.click();
    }

    public void clickToursTab() {
        clickGotItBtn();
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

    public void clickTravellsOption() {
        waitForDisplayed(travellerOption);
        travellerOption.click();
    }

    public void setAdultsNumber(String number) {
        setValue(adultsNumber, number);
    }

    public void clickSubmitBtn() {
        searchBtn.get(1).click();
    }

    public void clickDetailsLink() {
        waitForDisplayed(detailsLink.get(0));
        detailsLink.get(0).click();
    }

    public SignUpPage clickSignUpBtn() {
        clickGotItBtn();
        signUpLink.click();
        return new SignUpPage(driver);
    }

    public RegistrationPage clickBookNowBtn() {
        scrollToElement(bookNowBtn);
        bookNowBtn.click();
        return new RegistrationPage(driver);
    }
}