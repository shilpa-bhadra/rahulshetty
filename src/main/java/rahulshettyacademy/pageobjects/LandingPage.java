package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	public WebDriver driver;
	public ProductCatalog productCatalog;
	
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement password;
	@FindBy(id = "login")
	WebElement SignIn;
@FindBy(css="[class*='flyInOut']")
WebElement errorMessageis;

	public ProductCatalog loginApplication(String email, String pwd) {

		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		SignIn.click();
		productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}
	
	public  String getErrorMessage() {
		waitForWebElementToAppear(errorMessageis);
		return errorMessageis.getText();
		
	}
	
	
	
	
	
	
}
