package utils;

import org.openqa.selenium.WebDriver;

public class WindowHandler {
    private WebDriver driver;
    private WebDriver.Navigation navigate;

    public WindowHandler(WebDriver driver) {
        this.driver = driver;
        navigate = driver.navigate();
    }

    public void goBack() {
        navigate.back();
    }
}
