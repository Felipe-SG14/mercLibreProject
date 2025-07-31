package org.fsolano.elements;

import org.fsolano.utilities.reports.LoggerManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UIActions {

    private WebDriver driver;
    private Actions actions;

    public UIActions(WebDriver driver)
    {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private Object waitForUIElement(UIElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(element.getMaxWaitTime()));
        return switch (element.getWaitType()) {
            case VISIBLE:
                yield wait.until(ExpectedConditions.visibilityOfElementLocated(element.getBy()));
            case INVISIBLE:
                yield wait.until(ExpectedConditions.invisibilityOfElementLocated(element.getBy()));
            case PRESENCE:
                yield wait.until(ExpectedConditions.presenceOfElementLocated(element.getBy()));
            default:
                yield driver.findElement(element.getBy());
        };
    }

    public List<String> getElementsText(UIElement element)
    {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(element.getMaxWaitTime()))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(driver1 -> {
           List<WebElement> webElements = driver1.findElements(element.getBy());
           return webElements.stream().map(WebElement::getText).toList();
        });
    }

    private void moveToElement(WebElement targetElement)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
        LoggerManager.pass("Moving to element with by: " + targetElement);
    }

    public void click(UIElement element)
    {
        WebElement webElement = (WebElement) waitForUIElement(element);
        String logMessage = "Element with by:" + element.getBy() + " was clicked";
        actions.moveToElement(webElement).click().perform();
        LoggerManager.pass(logMessage);
    }

    public void click(UIElement element, boolean moveToElement)
    {
        WebElement webElement = (WebElement) waitForUIElement(element);
        String logMessage = "Element with by:" + element.getBy() + " was clicked";
        if (moveToElement)
        {
            moveToElement(webElement);
        }
        webElement.click();
        LoggerManager.pass(logMessage);
    }

    public void enterText(UIElement element, String desiredText)
    {
        WebElement webElement = (WebElement) waitForUIElement(element);
        String logMessage = "Text entered: " + desiredText + " - in element with by:" + element.getBy();
        webElement.sendKeys(desiredText);
        LoggerManager.pass(logMessage);
    }
}
