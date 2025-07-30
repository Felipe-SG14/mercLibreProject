package org.fsolano.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UIActionResult {

    WebElement webElement;
    WebDriver driver;
    public boolean success;


    public UIActionResult (Builder builder)
    {
        this.webElement = builder.webElement;
        this.driver = builder.driver;
        this.success = builder.success;
    }

    public static class Builder
    {
        private WebElement webElement;
        private WebDriver driver;
        private boolean success;

        public Builder setWebElement(WebElement webElement)
        {
            this.webElement = webElement;
            return this;
        }

        public Builder setDriver(WebDriver driver)
        {
            this.driver = driver;
            return this;
        }

        public Builder setSuccess(boolean success)
        {
            this.success = success;
            return this;
        }

        public UIActionResult build()
        {
            return new UIActionResult(this);
        }

    }

}
