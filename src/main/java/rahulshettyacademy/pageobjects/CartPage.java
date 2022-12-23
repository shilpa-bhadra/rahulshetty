package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> elementsInCart;
	@FindBy(xpath = "//*[@class='totalRow']/button")
	WebElement checkOut;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = elementsInCart.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckOut() {
		checkOut.click();
		return new CheckOutPage(driver);
	}

}
