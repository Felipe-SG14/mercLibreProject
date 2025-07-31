package org.fsolano.resources;

import org.fsolano.config.ConfigManager;
import org.fsolano.driver.DriverManager;
import org.fsolano.listeners.TextExecutionerListener;
import org.fsolano.utilities.Utilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;


@Listeners(TextExecutionerListener.class)
public abstract class BaseTest {

    @BeforeSuite
    public void cleanTestOutputFolder() {
        Utilities.cleanTestOutputDirectory();
    }

    @BeforeMethod(alwaysRun = true)
    protected void beforeMethod() {
        DriverManager.initializeDriver();
    }

    @AfterMethod(alwaysRun = true)
    protected void afterTest()
    {
        DriverManager.quitDriver();
    }

}
