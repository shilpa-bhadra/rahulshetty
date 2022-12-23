package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath = "//*[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectCountry;
	@FindBy(css = ".action__submit")
	WebElement submit;

	public void enterCountry(String countryName) {
		country.sendKeys(countryName);
		selectCountry.click();
	}

	public ConfirmationPage submitTheOrder() throws InterruptedException {

		waitForWebElementToAppear(submit);

		submit.click();
		return new ConfirmationPage(driver);
	}

}
