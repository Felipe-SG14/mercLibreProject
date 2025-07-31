package org.fsolano.resources;

import org.fsolano.config.ConfigManager;
import org.fsolano.driver.DriverManager;
import org.testng.annotations.BeforeMethod;

public class TestBase extends BaseTest{

    @BeforeMethod(alwaysRun = true)
    protected void setUp()
    {
        DriverManager.getDriver().get(ConfigManager.getProperty("mainPage"));
    }
}
