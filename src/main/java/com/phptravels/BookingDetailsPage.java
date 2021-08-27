package com.phptravels;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WindowHandler;

import java.util.List;

public class BookingDetailsPage extends HomePage {
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

    @FindBy(xpath = "//div[@class='input-items w-auto']")
    List<WebElement> dropdownOption;

    @FindBy(name = "title_1")
    WebElement travellerTitle;

    @FindBy(id = "firstname_1")
    WebElement travellerFirstName;

    @FindBy(id = "lastname_1")
    WebElement travellerLastName;

    @FindBy(id = "title_2")
    WebElement traveller2Title;

    @FindBy(id = "firstname_2")
    WebElement traveller2FirstName;

    @FindBy(id = "lastname_2")
    WebElement traveller2LastName;

//    @FindBy(xpath = "//*[@for='gateway_pay-later']")
//    WebElement paylater;

    @FindBy(id = "gateway_pay-later")
    WebElement paylater;

    @FindBy(id = "agreechb")
    WebElement agreeCheckMark;

    @FindBy(id = "booking")
    WebElement bookingConfirmBtn;

    public BookingDetailsPage(WebDriver driver) {
        super(driver);
    }

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
        scrollToDown(100, 2);
        dropdownOption.get(0).click();
        searchByText(country);
    }

    public void setNationalityName(String nationality) {
        dropdownOption.get(1).click();
        searchByText(nationality);
    }

    public void setTravellerTitle(String title) {
        scrollToDown(100, 3);
        travellerTitle.click();
        selectByVisibleText(travellerTitle, title);
    }

    public void setTravellerFirstName(String tFirstName) {
        setValue(travellerFirstName, tFirstName);
    }

    public void setTravellerLastName(String tLastName) {
        setValue(travellerLastName, tLastName);
    }


    public void setTraveller2Title(String title) {
        scrollToDown(100, 3);
        traveller2Title.click();
        selectByVisibleText(traveller2Title, title);
    }

    public void setTraveller2FirstName(String tFirstName) {
        setValue(traveller2FirstName, tFirstName);
    }

    public void setTraveller2LastName(String tLastName) {
        setValue(traveller2LastName, tLastName);
    }

    public void clickPayLater() {
        scrollToDown(100, 10);
        paylater.click();
    }

    public void clickAgreeBtn() {
        if (!agreeCheckMark.isSelected())
            agreeCheckMark.click();
    }

    public void fillCustomerDetailsInfo(String firstName, String lastName, String email,
                                        String phone, String addrs, String country, String nationality) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phone);
        setAddress(addrs);
        setCountryName(country);
        setNationalityName(nationality);
    }

    public void fillTraveller_1_Info() {
        setTravellerTitle("MR");
        setTravellerFirstName(new Faker().name().firstName());
        setTravellerLastName(new Faker().name().firstName());
    }

    public void fillTraveller_2_Info() {
        setTraveller2Title("MR");
        setTraveller2FirstName(new Faker().name().firstName());
        setTraveller2LastName(new Faker().name().firstName());
    }

    public void clickBookingConfirm() {
        scrollToDown(100, 3);
        clickUsingJsExecutor(bookingConfirmBtn);
        new WindowHandler(driver).switchToTab("Singapore Transfer: Hotel to Singapore Cruise Centre");
    }
}
