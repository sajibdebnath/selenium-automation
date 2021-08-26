package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends RegistrationPage {
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "first_name")
    WebElement firstName;

    @FindBy(name = "last_name")
    WebElement lastName;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "phone")
    WebElement phoneNumber;

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


    @FindBy(name = "password")
    WebElement password;

    @FindBy(className = "select2-selection select2-selection--single")
    WebElement accountType;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signUpBtn;

    public void setPassword(String pass) {
        password.sendKeys(pass);
    }

    public void clickAccountType() {
        accountType.click();
    }

    public void clickSignUpBtn() {
        scrollToElement(signUpBtn);
        sleep(3);
        signUpBtn.click();
    }
}
