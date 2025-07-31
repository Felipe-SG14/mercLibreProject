package org.fsolano.utilities.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.fsolano.driver.DriverManager;
import org.fsolano.utilities.Utilities;
import org.fsolano.utilities.reports.ExtentReports.ExtentTestManager;
import org.testng.Assert;

public final class LoggerManager {

    private static void ExtentReportPass(String message)
    {
        String screenshot = Utilities.getScreenshotBase64(DriverManager.getDriver());
        ExtentTestManager.getTest().pass(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
    }

    public static void pass(String message)
    {
        ExtentReportPass(message);
    }

    public static void assertTrue(boolean condition, String message)
    {
        Assert.assertTrue(condition);
        ExtentReportPass(message);
    }

    public static void assertEquals(Object objectOne, Object objectTwo, String message)
    {
        Assert.assertEquals(objectOne, objectTwo);
        ExtentReportPass(message);
    }

}
