package rahulshettyacademy.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public static ExtentReports getReportObject() {
		String filepath= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
	ExtentReports extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Rahul shetty");
	return extent;
	
	
	}
	
	
	
}
