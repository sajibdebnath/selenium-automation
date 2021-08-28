package com.phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WindowHandler;

import java.util.List;

public class BookingDetailsPage extends HomePage {
    @FindBy(name = "firstname")
    private WebElement firstName;
    @FindBy(name = "lastname")
    private WebElement lastName;
    @FindBy(name = "email")
    private WebElement email;
    @FindBy(name = "phone")
    private WebElement phoneNumber;
    @FindBy(name = "address")
    private WebElement address;
    @FindBy(xpath = "//div[@class='input-items w-auto']")
    private List<WebElement> dropdownOption;
    @FindBy(name = "title_1")
    private WebElement travellerTitle;
    @FindBy(name = "firstname_1")
    private WebElement travellerFirstName;
    @FindBy(name = "lastname_1")
    private WebElement travellerLastName;
    @FindBy(name = "title_2")
    private WebElement traveller2Title;
    @FindBy(name = "firstname_2")
    private WebElement traveller2FirstName;
    @FindBy(name = "lastname_2")
    private WebElement traveller2LastName;
    @FindBy(id = "gateway_pay-later")
    private WebElement paylater;
    @FindBy(id = "agreechb")
    private WebElement agreeCheckMark;
    @FindBy(id = "booking")
    private WebElement bookingConfirmBtn;

    public BookingDetailsPage(WebDriver driver) {
        super(driver);
    }

    private void setFirstName(String fName) {
        setValue(firstName, fName);
    }

    private void setLastName(String lName) {
        setValue(lastName, lName);
    }

    private void setEmail(String email_addrs) {
        setValue(email, email_addrs);
    }

    private void setPhoneNumber(String number) {
        setValue(phoneNumber, number);
    }

    private void setAddress(String address_name) {
        setValue(address, address_name);
    }

    private void setCountryName(String country) {
        scrollToDown(100, 2);
        dropdownOption.get(0).click();
        searchByText(country);
    }

    private void setNationalityName(String nationality) {
        dropdownOption.get(1).click();
        searchByText(nationality);
    }

    private void setTravellerTitle(String title) {
        scrollToDown(100, 5);
        travellerTitle.click();
        selectTitle(title);
    }

    private void setTravellerFirstName(String tFirstName) {
        setValue(travellerFirstName, tFirstName);
    }

    private void setTravellerLastName(String tLastName) {
        setValue(travellerLastName, tLastName);
    }

    private void setTraveller2Title(String title) {
        scrollToDown(100, 4);
        traveller2Title.click();
        selectTitle(title);
    }

    private void setTraveller2FirstName(String tFirstName) {
        setValue(traveller2FirstName, tFirstName);
    }

    private void setTraveller2LastName(String tLastName) {
        setValue(traveller2LastName, tLastName);
    }

    private void selectTitle(String title) {
        List<WebElement> titleElements = driver.findElements(By.xpath("//option[text()='" + title + "']"));
        for (WebElement element : titleElements) {
            if (element.isDisplayed()) {
                element.click();
                break;
            }
        }
    }

    public void fillCustomerDetailsInfo(
            String firstName, String lastName,
            String email, String phone, String addrs,
            String country, String nationality) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phone);
        setAddress(addrs);
        setCountryName(country);
        setNationalityName(nationality);
    }

    public void fillTraveller_1_Info(String title, String fName, String lName) {
        setTravellerTitle(title);
        setTravellerFirstName(fName);
        setTravellerLastName(lName);
    }

    public void fillTraveller_2_Info(String title, String fName, String lName) {
        setTraveller2Title(title);
        setTraveller2FirstName(fName);
        setTraveller2LastName(lName);
    }

    public void clickPayLater() {
        scrollToDown(100, 10);
        paylater.click();
    }

    public void clickAgreeBtn() {
        if (!agreeCheckMark.isSelected())
            customClick(agreeCheckMark);
    }

    public void clickBookingConfirm() {
        customClick(bookingConfirmBtn);
        sleep(5);   // Waiting for new page loaded and return to main window
        new WindowHandler(driver).switchToTab(1);
    }
}
