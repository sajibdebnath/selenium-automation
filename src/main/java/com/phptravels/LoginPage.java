package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends HomePage {
    @FindBy(name = "email")
    private WebElement email;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginBtn;
    @FindBy(xpath = "//div[@class='alert alert-success signup']")
    private WebElement alertSuccess;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    void setEmail(String email_addrs) {
        setValue(email, email_addrs);
    }

    void setPassword(String pass) {
        setValue(password, pass);
    }

    public void fillUserCredential(String email, String pass) {
        setEmail(email);
        setPassword(pass);
    }

    public DashboardPage clickLogInBtn() {
        loginBtn.click();
        return new DashboardPage(driver);
    }

    public String successAlertMessage() {
        return alertSuccess.getText();
    }
}
