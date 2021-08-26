package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(name = "firstname")
    WebElement firstName;

    @FindBy(name = "lastname")
    WebElement lastName;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "phone")
    WebElement phoneNumber;

    @FindBy(name = "address")
    WebElement address;

    @FindBy(className = "select2 select2-container select2-container--default")
    List<WebElement> dropdownOption;
//    @FindBy(className = "select2 select2-container select2-container--default select2-container--focus")
//    List<WebElement> dropdownOption2;

//    @FindBy(id = "select2-nationality-uw-container")
//    WebElement nationalityField;

    @FindBy(name = "country_code")
    WebElement countryName; //Bangladesh

    @FindBy(name = "nationality")
    WebElement nationalityName; //Bangladesh


    @FindBy(id = "title_1")
    WebElement travellerTitle; //Bangladesh

    @FindBy(id = "firstname_1")
    WebElement travellerFirstName; //Bangladesh

    @FindBy(id = "lastname_1")
    WebElement travellerLastName; //Bangladesh


    @FindBy(id = "title_2")
    WebElement traveller2Title; //Bangladesh

    @FindBy(id = "firstname_2")
    WebElement traveller2FirstName; //Bangladesh

    @FindBy(id = "lastname_2")
    WebElement traveller2LastName; //Bangladesh


    @FindBy(id = "gateway_pay-later")
    WebElement paylater; //Bangladesh

    @FindBy(id = "agreechb")
    WebElement agreeCheckMark; //Bangladesh

    @FindBy(id = "booking")
    WebElement bookingConfirmBtn; //Bangladesh

    public void setFirstName(String fName) {
        setValue(firstName, fName);
    }

    public void setLastName(String lName) {
        setValue(lastName, lName);
    }

    public void setEmail(String email_addrs) {
        setValue(email, email_addrs);
    }

    public void setPhoneNumber(String number) {
        setValue(phoneNumber, number);
    }

    public void setAddress(String address_name) {
        setValue(address, address_name);
    }

    public void setCountryName(String country) {
        waitForDisplayed(dropdownOption.get(0));
        dropdownOption.get(0).click();
        countryName.sendKeys(country);
    }

    public void setNationalityName(String nationality) {
        dropdownOption.get(1).click();
        nationalityName.sendKeys(nationality);
    }

    public void setTravellerTitle(String title) {
        selectByVisibleText(travellerTitle, title);
    }

    public void setTravellerFirstName(String tFirstName) {
        selectByVisibleText(travellerFirstName, tFirstName);
    }

    public void setTravellerLastName(String tLastName) {
        selectByVisibleText(travellerLastName, tLastName);
    }


    public void setTraveller2Title(String title) {
        selectByVisibleText(traveller2Title, title);
    }

    public void setTraveller2FirstName(String tFirstName) {
        selectByVisibleText(traveller2FirstName, tFirstName);
    }

    public void setTraveller2LastName(String tLastName) {
        selectByVisibleText(traveller2LastName, tLastName);
    }


    public void clickPayLater() {
        paylater.click();
    }

    public void clickAgreeBtn() {
        System.out.println("=========> " + agreeCheckMark.isSelected());
    }

    public void clickBookingConfirm() {
        bookingConfirmBtn.click();
    }
}
