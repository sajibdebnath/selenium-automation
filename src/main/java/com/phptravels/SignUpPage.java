package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SignUpPage extends LoginPage {
    @FindBy(name = "first_name")
    WebElement firstName;
    @FindBy(name = "last_name")
    WebElement lastName;
    @FindBy(name = "phone")
    WebElement phoneNumber;
    @FindBy(xpath = "//div[@class='input-box']")
    List<WebElement> typeOption;
    @FindBy(id = "account_type")
    WebElement accountType;
    @FindBy(xpath = "//span[text()='Signup']")
    WebElement signUpBtn;
    @FindBy(xpath = "//div[@class='alert alert-success signup']")
    WebElement alertSuccess;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String fName) {
        setValue(firstName, fName);
    }

    public void setLastName(String lName) {
        setValue(lastName, lName);
    }

    public void setPhoneNumber(String number) {
        setValue(phoneNumber, number);
    }

    public void selectAccountType(String type) {
        scrollToDown(100, 2);
        typeOption.get(5).click();
        clickSearchResults(type);
    }

    public void fillCustomerInfo(String firstName, String lastName, String phone, String email, String pass, String accType) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phone);
        setPassword(pass);
        selectAccountType(accType);
    }

    public void clickSignUpBtn() {
        signUpBtn.click();
    }

    public String successAlertMessage() {
        return alertSuccess.getText();
    }
}
