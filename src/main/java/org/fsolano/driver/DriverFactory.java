package org.fsolano.driver;

import org.fsolano.enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;

public class DriverFactory {

    public static WebDriver getDriver(DriverType browser, String driverOptions)
    {
        String[] options = (driverOptions == null || driverOptions.isEmpty()) ? new String[0] : driverOptions.split(",");
        return switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                Arrays.stream(options).forEach(chromeOptions::addArguments);
                yield new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                Arrays.stream(options).forEach(firefoxOptions::addArguments);
                yield new FirefoxDriver(firefoxOptions);
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                Arrays.stream(options).forEach(edgeOptions::addArguments);
                yield new EdgeDriver(edgeOptions);
        };
    };
}
