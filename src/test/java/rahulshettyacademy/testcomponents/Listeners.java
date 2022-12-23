package rahulshettyacademy.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent =ExtentReportNG.getReportObject();
ExtentTest test;
	
ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

@Override		
public void onFinish(ITestContext context) {					
    // TODO Auto-generated method stub				
	extent.flush();	
}		

@Override		
public void onStart(ITestContext context) {					
    // TODO Auto-generated method stub				
    		
}		

@Override		
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {					
    // TODO Auto-generated method stub				
    		
}		

@Override		
public void onTestFailure(ITestResult result) {					
    // TODO Auto-generated method stub				
	extentTest.get().fail(result.getThrowable());
	
	try {
		driver =(WebDriver) result.getTestClass().getRealClass().getField("driver")
			.get(result.getInstance());
	} catch (Exception e1) {
		
		e1.printStackTrace();
	} 
	
	String filePath = null;
	try {
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());		
}		

@Override		
public void onTestSkipped(ITestResult result) {					
    // TODO Auto-generated method stub				
    		
}		

@Override		
public void onTestStart(ITestResult result) {					
    // TODO Auto-generated method stub	
	test =extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);
}		

@Override		
public void onTestSuccess(ITestResult result) {					
    // TODO Auto-generated method stub	
	extentTest.get().log(Status.PASS, "test is pass");
    		
}









































//@Override		
//    public void onFinish(ITestContext context) {					
//       
//		
//		extent.flush();
//        		
//    }		

	
//
// 
//    @Override		
//    public void onTestFailure(ITestResult result) {					
//    
//    	test.fail(result.getThrowable());
//    	
//    	try {
//			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		} catch (Exception e1) {
//			
//			e1.printStackTrace();
//		} 
//    	
//    	String filePath = null;
//		try {
//			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//    	test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
//    }		

  		
//
//    @Override		
//    public void onTestStart(ITestResult result) {					
//      
//    	
//        	test =extent.createTest(result.getMethod().getMethodName());
//        	
//    }		
//
//    @Override		
//    public void onTestSuccess(ITestResult result) {					
//      	
//    	test.log(Status.PASS, "test is pass");
//        		
//    }		






}
