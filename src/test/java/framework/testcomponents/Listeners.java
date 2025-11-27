package framework.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import framework.abstractcomponents.DriverFactory;
import framework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getExtentReportObject();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> threadSafeTest =  new ThreadLocal<ExtentTest>(); //thread safe test variable

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		threadSafeTest.set(test);  // unique thread id : creates map of thread id -> object
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		threadSafeTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		threadSafeTest.get().fail(result.getThrowable());

		String screenshotPath = null;

//		try {
//			//getField method gets ONLY public fields
//			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")  
//					.get(result.getInstance());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		WebDriver driver = DriverFactory.getDriver();
		
		try {
			screenshotPath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		threadSafeTest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();  //****IMPORTANT
	}

}
