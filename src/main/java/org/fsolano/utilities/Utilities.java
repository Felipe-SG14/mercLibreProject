package org.fsolano.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Utilities {

    public static String getTestCaseName(ITestResult result)
    {
        String testName = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        String paramPart = "";
        if (parameters != null && parameters.length > 0) {
            paramPart = Arrays.stream(parameters)
                    .map(Object::toString)
                    .collect(Collectors.joining("_"));
        }
        return testName + (paramPart.isEmpty() ? "" : "_"+paramPart);
    }

    public static void cleanTestOutputDirectory()
    {
        File outputDir = new File(Paths.OUTPUT_DIRECTORY);
        try {
            if (outputDir.exists()) {
                FileUtils.cleanDirectory(outputDir);
            } else {
                outputDir.mkdirs();
            }
        } catch (IOException e) {
            throw new RuntimeException(Paths.OUTPUT_DIRECTORY + " was not created", e);
        }
    }

    public static void saveScreenshot(WebDriver driver, String path)
    {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
