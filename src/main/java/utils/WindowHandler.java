package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

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

    public void goForward() {
        navigate.forward();
    }

    public void refreshPage() {
        navigate.refresh();
    }

    public void goTo(String url) {
        navigate.to(url);
    }

    public void switchToTab() {
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!parentWindow.equalsIgnoreCase(window))
                driver.switchTo().window(window);
            break;
        }
    }

    public void switchToTab(String tabTitle) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            if (tabTitle.equals(driver.getTitle()))
                break;
        }
    }
}
