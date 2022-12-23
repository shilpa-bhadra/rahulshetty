package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.abstractcomponents.AbstractComponents;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.testcomponents.BaseTest;
//@Listeners(rahulshettyacademy.testcomponents.Listeners.class)
public class SubmitOrderTest extends BaseTest{
	
	@Test(dataProvider ="getData")
	
	public void submitOrdrer(HashMap<String,String> input) throws InterruptedException, IOException {
		//String productName = "ZARA COAT 3";
		String countryName = "india";
		
		//landingPage =launchApplication();
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addProductToCart(input.get("item"));
		Thread.sleep(7000);
		CartPage cartPage =productCatalog.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("item"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.enterCountry(countryName);
		Thread.sleep(10000);
		ConfirmationPage confirmationPage = checkOutPage.submitTheOrder();
		Thread.sleep(10000);
		String messageWeGet = confirmationPage.getConfirmtionMessage();
		Assert.assertTrue(messageWeGet.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	public String getScreenshot(String testCaseName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir"+"\\reports\\"+testCaseName +".png"));
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir"+"\\reports\\"+testCaseName +".png");

	
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
	

		List<HashMap<String, String>> data =getJSonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}};
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "rahuls@gmail.com");
//		map.put("password", "Rahul@123");
//		map.put("item", "ZARA COAT 3");	
		
	}
	
	
}
