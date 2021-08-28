package com.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//*[@class='author__title']")
    private WebElement authoTitle;
    @FindBy(linkText = "Logout")
    private WebElement logout;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getAuthorTitle() {
        return authoTitle.getText();
    }

    public LoginPage clickLogout() {
        logout.click();
        return new LoginPage(driver);
    }
}
