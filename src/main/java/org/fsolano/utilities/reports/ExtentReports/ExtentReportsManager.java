package org.fsolano.utilities.reports.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.fsolano.utilities.Paths;

public class ExtentReportsManager {
    private static final ExtentReports extent = createInstance();

    private static ExtentReports createInstance() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(Paths.OUTPUT_DIRECTORY + "ExtentReport.html");
        reporter.config().setReportName("Automation Report");
        reporter.config().setDocumentTitle("Execution Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Felipe");

        return extent;
    }

    public static ExtentReports getExtentReports() {
        return extent;
    }
}
