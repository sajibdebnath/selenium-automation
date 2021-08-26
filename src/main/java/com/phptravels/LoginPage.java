package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends HomePage {
    @FindBy(name = "email")
    WebElement email;
    @FindBy(name = "password")
    WebElement password;
    @FindBy(xpath = "//span[text()='Login']")
    WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmail(String email_addrs) {
        setValue(email, email_addrs);
    }

    public void setPassword(String pass) {
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
}
