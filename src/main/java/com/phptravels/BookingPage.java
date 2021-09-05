package com.phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WindowHandler;

import java.util.List;

import static utils.LocatorUtils.getElements;

public class BookingPage extends HomePage {
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
    private WebElement traveller1_title;
    @FindBy(name = "firstname_1")
    private WebElement traveller1_fName;
    @FindBy(name = "lastname_1")
    private WebElement traveller1_lName;
    @FindBy(name = "title_2")
    private WebElement traveller2_title;
    @FindBy(name = "firstname_2")
    private WebElement traveller2_fName;
    @FindBy(name = "lastname_2")
    private WebElement traveller2_lName;
    @FindBy(id = "gateway_pay-later")
    private WebElement paylater;
    @FindBy(id = "agreechb")
    private WebElement agreeCheckMark;
    @FindBy(id = "booking")
    private WebElement bookingConfirmBtn;

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    private void setFirstName(String fName) {
        setText(firstName, fName);
    }

    private void setLastName(String lName) {
        setText(lastName, lName);
    }

    private void setEmail(String email_addrs) {
        setText(email, email_addrs);
    }

    private void setPhone(String number) {
        setText(phoneNumber, number);
    }

    private void setAddress(String address_name) {
        setText(address, address_name);
    }

    private void setCountry(String country) {
        scrollAndClick(dropdownOption.get(0));
        searchByText(country);
    }

    private void setNationality(String nationality) {
        dropdownOption.get(1).click();
        searchByText(nationality);
    }

    private void selectTitle(String title) {
        List<WebElement> titleElements = getElements(driver, By.xpath("//option[text()='" + title + "']"));
        for (WebElement element : titleElements) {
            if (element.isDisplayed()) {
                element.click();
                break;
            }
        }
    }

    public void setCustomerDetails(String firstName, String lastName,
                                   String email, String phone, String addrs,
                                   String country, String nationality) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
        setAddress(addrs);
        setCountry(country);
        setNationality(nationality);
    }

    public void fillTraveller_1(String title, String fName, String lName) {
        scrollAndClick(traveller1_title);
        selectTitle(title);
        setText(traveller1_fName, fName);
        setText(traveller1_lName, lName);
    }

    public void fillTraveller_2(String title, String fName, String lName) {
        scrollAndClick(traveller2_title);
        selectTitle(title);
        setText(traveller2_fName, fName);
        setText(traveller2_lName, lName);
    }

    public void selectPayment() {
        scrollAndClick(paylater);
    }

    public void clickAgreeBtn() {
        if (!agreeCheckMark.isSelected())
            scrollAndClick(agreeCheckMark);
    }

    public void confirmBooking() {
        scrollAndClick(bookingConfirmBtn);
        sleep(5);   // Waiting for new page loaded and return to main window
        new WindowHandler(driver).switchToTab(1);
    }
}
