package utils;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class WindowHandler {
    private WebDriver driver;
    private WebDriver.Navigation navigate;
    private ArrayList<String> tabs;

    public WindowHandler(WebDriver driver) {
        this.driver = driver;
        navigate = driver.navigate();
        tabs = new ArrayList<>(driver.getWindowHandles());
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

    public void switchToTab(String tabTitle) {
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            if (driver.getTitle().equalsIgnoreCase(tabTitle))
                break;
        }
    }

    public void switchToTab(int tabIndex) {
        driver.switchTo().window(tabs.get(tabIndex));
    }
}
