package org.fsolano.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.fsolano.driver.DriverManager;
import org.fsolano.utilities.Paths;
import org.fsolano.utilities.Utilities;
import org.fsolano.utilities.reports.ExtentReports.ExtentReportsManager;
import org.fsolano.utilities.reports.ExtentReports.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TextExecutionerListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        String testCaseName = Utilities.getTestCaseName(result);
        String testCaseDescription = result.getMethod().getDescription();
        ExtentTest extentTest = ExtentReportsManager.getExtentReports().createTest(testCaseName, testCaseDescription);
        ExtentTestManager.setTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        ExtentTestManager.getTest().pass("✅ Test successfully passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        String testCaseName = Utilities.getTestCaseName(result);
        String absoluteFilePath = Paths.OUTPUT_FAILED_IMAGES_PATH + testCaseName + ".png";
        String relativePath = "FailedTestsImages" + File.separator + testCaseName + ".png";
        Utilities.saveScreenshot(DriverManager.getDriver(), absoluteFilePath);
        ExtentTestManager.getTest().fail("❌ Test failed:" + result.getThrowable());
        ExtentTestManager.getTest().fail(testCaseName, MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        ExtentReportsManager.getExtentReports().flush();
        ExtentTestManager.removeTest();
    }
}
