package rahulshettyacademy.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.testcomponents.BaseTest;

public class StepDefinitionImp extends BaseTest{

public LandingPage landingPage;	
public ProductCatalog productCatalog;
public ConfirmationPage confirmationPage;


@Given("I landed on Ecommerce Page")
public void I_landed_on_Ecommerce_Page() throws IOException {
	
	landingPage =launchApplication();
	
}

@Given("^Logged in with username (.+) and password (.+)$")
public void logged_in_username_and_password(String username,String password) {
	
	 productCatalog = landingPage.loginApplication(username,password);
	
}

@When("^I add product (.+) to cart$")
public void I_add_product_to_cart(String productName) throws InterruptedException{
List<WebElement> products = productCatalog.getProductsList();
productCatalog.addProductToCart(productName);

}


@When("^And Checkout (.+) and submit the order$")
public void checkOut_submit_the_order(String productName) throws InterruptedException {
	
	CartPage cartPage =productCatalog.goToCartPage();
	
	Boolean match = cartPage.verifyProductDisplay(productName);
	Assert.assertTrue(match);
	CheckOutPage checkOutPage = cartPage.goToCheckOut();
	checkOutPage.enterCountry("india");

	confirmationPage = checkOutPage.submitTheOrder();
	

}

//"THANKYOU FOR THE ORDER." message is displayed on confirmationPage
@Then("{string} message is displayed on confirmationPage")
public void message_displayed_confirmation_page(String string) throws InterruptedException{

	String messageWeGet = confirmationPage.getConfirmtionMessage();
	Assert.assertTrue(messageWeGet.equalsIgnoreCase(string));
	
	
	
}	
	
	
	
}



