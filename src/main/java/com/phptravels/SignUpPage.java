package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SignUpPage extends HomePage {
    @FindBy(name = "email")
    private WebElement emailField;
    @FindBy(name = "password")
    private WebElement password;
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
        setText(firstName, fName);
    }

    private void setLastName(String lName) {
        setText(lastName, lName);
    }

    private void setPhoneNumber(String number) {
        setText(phoneNumber, number);
    }

    private void selectAccountType(String type) {
        waitForListToLoad(typeOption);
        scrollAndClick(typeOption.get(5));
        clickSearchResults();
    }

    void setEmail(String email) {
        setText(emailField, email);
    }

    void setPassword(String pass) {
        setText(password, pass);
    }

    public void setUserCredential(String email, String pass) {
        setEmail(email);
        setPassword(pass);
    }

    public void setCustomerDetails(
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
        scrollAndClick(signUpBtn);
        return new LoginPage(driver);
    }
}
