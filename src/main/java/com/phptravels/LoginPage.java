package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends SignUpPage {
    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginBtn;
    @FindBy(xpath = "//div[@class='alert alert-success signup']")
    private WebElement alertSuccess;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage clickLogInBtn() {
        loginBtn.click();
        return new DashboardPage(driver);
    }

    public String successAlertMessage() {
        return alertSuccess.getText();
    }
}
