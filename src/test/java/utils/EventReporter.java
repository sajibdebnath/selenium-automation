package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.List;

public class EventReporter implements WebDriverEventListener {
    static By locator;

    public void beforeAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertDismiss(WebDriver webDriver) {

    }

    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Navigate: \t\t" + s);
    }

    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Title: \t\t\t" + webDriver.getTitle() + "\n");
    }

    public void beforeNavigateBack(WebDriver webDriver) {

    }

    public void afterNavigateBack(WebDriver webDriver) {

    }

    public void beforeNavigateForward(WebDriver webDriver) {

    }

    public void afterNavigateForward(WebDriver webDriver) {

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Find By: \t\t" + by.toString());
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        locator = by;
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        List<WebElement> webElements = webDriver.findElements(locator);
        int index = webElements.size();

        System.out.print("Found: \t\t\tTotal -> " + webDriver.findElements(locator).size() + ", visibility -> { ");
        for (int i = 0; i < index; i++)
            System.out.print("element[" + i + "]: " + webElements.get(i).isDisplayed() + (i != index - 1 ? ", " : " }\n"));

        System.out.println("Click on: \t\t<" + webElement.getTagName() + "> "
                + (!webElement.getText().isEmpty() ? "'" + webElement.getText() + "'\n" : "\n"));
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {

    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.print(webElement.getTagName().equals("input") && !webElement.getAttribute("value").isEmpty()
                ? "Old text: \t\t'" + webElement.getAttribute("value") + "'\n\n"
                : "");
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.print(webElement.getTagName().equals("input") && !webElement.getAttribute("value").isEmpty()
                ? "New text: \t\t'" + webElement.getAttribute("value") + "'\n\n"
                : "");
    }

    public void beforeScript(String s, WebDriver webDriver) {
        System.out.println("Execute js: \t" + s);
    }

    public void afterScript(String s, WebDriver webDriver) {
        System.out.println();
    }

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
        System.out.println("Screenshot: \t\t" + System.getProperty("SCREENSHOT_NAME") + "\n");
    }

    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        System.out.println("Get text: \t\t" + (!s.isEmpty() ? "'" + s + "'\n" : "\n"));
    }
}
