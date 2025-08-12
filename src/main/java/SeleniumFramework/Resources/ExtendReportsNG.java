package SeleniumFramework.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReportsNG {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getReporter() {
        if (extent == null) {
            String path = System.getProperty("user.dir") + "/reports/index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setEncoding("utf-8");
            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setReportName("Web Automation Results");
            reporter.config().setDocumentTitle("Test results");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("SDET", "GAYATHRI");
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
