package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.testcomponents.BaseTest;
//@Listeners(rahulshettyacademy.testcomponents.Listeners.class)
public class ErrorValidation extends BaseTest {

	@Test
	
	public void errorMessage() {
		
		
		landingPage.loginApplication("rahs@gmail.com", "Raul@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	
}