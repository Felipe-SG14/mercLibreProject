package org.fsolano.driver;

import org.fsolano.config.ConfigManager;
import org.fsolano.enums.DriverType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static void initializeDriver() {
        if (driverThread.get() != null) return;
        String driverName = System.getProperty("driver", ConfigManager.getProperty("driver"));
        DriverType driverType = DriverType.valueOf(driverName.toUpperCase());
        String driverOptions = ConfigManager.getProperty("driverOptions");
        createDriver(driverType, driverOptions);
    }

    private static void createDriver(DriverType browser, String options) {
        if (driverThread.get() == null) {
            WebDriver driver = DriverFactory.getDriver(browser, options);
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driverThread.set(driver);
        }
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThread.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized. Call createDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}
