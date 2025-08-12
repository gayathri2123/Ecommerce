package Ecommerce.TestComponent;

import SeleniumFramework.Resources.ExtendReportsNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
//    ExtentReports extent= ExtendReportsNG.getReporter();
//    ExtentTest test;
    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtendReportsNG.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
        public void onTestStart(ITestResult result) {
            ExtentTest test = ExtendReportsNG.getReporter()
                    .createTest(result.getMethod().getMethodName());
            ExtendReportsNG.setTest(test); // Save it in ThreadLocal
        }

        @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtendReportsNG.getTest().fail(result.getThrowable());
        Object testInstance = result.getInstance();
        WebDriver driver = ((BaseTest) testInstance).driver;

        String base64Screenshot = ((BaseTest) testInstance).getBase64Screenshot(driver);

        try {
            ExtendReportsNG.getTest().fail("Test failed , see screenshot",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtendReportsNG.getReporter().flush();

    }
}
