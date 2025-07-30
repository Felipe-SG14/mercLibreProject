package org.fsolano.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UIActions {

    private WebDriver driver;
    private Actions actions;

    public UIActions(WebDriver driver)
    {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public UIActionResult waitForElement(UIElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(element.getMaxWaitSeconds()));
        return switch (element.getWaitType()) {
            case VISIBLE:
                WebElement visibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element.getBy()));
                yield new UIActionResult.Builder().setWebElement(visibleElement).setDriver(driver).setSuccess(true).build();
            case INVISIBLE:
                boolean result = wait.until(ExpectedConditions.invisibilityOfElementLocated(element.getBy()));
                yield new UIActionResult.Builder().setSuccess(true).build();
            case PRESENCE:
                WebElement presenceElement = wait.until(ExpectedConditions.presenceOfElementLocated(element.getBy()));
                yield new UIActionResult.Builder().setWebElement(presenceElement).setDriver(driver).setSuccess(true).build();
            default:
                WebElement findElement = driver.findElement(element.getBy());
                yield new UIActionResult.Builder().setWebElement(findElement).build();
        };
    }

    public void goTo(String url)
    {
        driver.get(url);
    }

    public String getText(UIElement element)
    {
        UIActionResult actionResult = waitForElement(element);
        // Log
        return actionResult.webElement.getText();
    }

    public void click(UIElement element)
    {
        UIActionResult actionResult = waitForElement(element);
        actionResult.webElement.click();
    }

    public void enterText(UIElement element, String desiredText)
    {
        UIActionResult actionResult = waitForElement(element);
        actionResult.webElement.sendKeys(desiredText);
    }
}
