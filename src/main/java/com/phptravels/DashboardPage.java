package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='author__title']")
    WebElement authoTitle;

    @FindBy(linkText = "Logout")
    WebElement logout;

    public String getAuthorTitle() {
        return authoTitle.getText();
    }

    public LoginPage clickLogout() {
        logout.click();
        return new LoginPage(driver);
    }
}
