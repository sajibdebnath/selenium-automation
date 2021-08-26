package com.phptravels;

import com.github.javafaker.Faker;
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
        selectOption(type);
    }

    public void fillCustomerInfo(String email, String pass) {
        setFirstName(new Faker().name().firstName());
        setLastName(new Faker().name().lastName());
        setEmail(email);
        setPhoneNumber(new Faker().number().digits(10));
        setPassword(pass);
        selectAccountType("Customer");
    }

    public void clickSignUpBtn() {
        signUpBtn.click();
    }

    public String successAlertMessage() {
        return alertSuccess.getText();
    }
}
