package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SignUpPage extends LoginPage {
    @FindBy(name = "first_name")
    private WebElement firstName;
    @FindBy(name = "last_name")
    private WebElement lastName;
    @FindBy(name = "phone")
    private WebElement phoneNumber;
    @FindBy(xpath = "//div[@class='input-box']")
    private List<WebElement> typeOption;
    @FindBy(id = "account_type")
    private WebElement accountType;
    @FindBy(xpath = "//span[text()='Signup']")
    private WebElement signUpBtn;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    private void setFirstName(String fName) {
        setValue(firstName, fName);
    }

    private void setLastName(String lName) {
        setValue(lastName, lName);
    }

    private void setPhoneNumber(String number) {
        setValue(phoneNumber, number);
    }

    private void selectAccountType(String type) {
        scrollToDown(100, 2);
        typeOption.get(5).click();
        clickSearchResults(type);
    }

    public void fillCustomerInfo(
            String firstName, String lastName,
            String phone, String email,
            String pass, String accType) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phone);
        setPassword(pass);
        selectAccountType(accType);
    }

    public LoginPage clickSignUpBtn() {
        scrollToDown(100, 5);
        customClick(signUpBtn);
        return new LoginPage(driver);
    }
}
