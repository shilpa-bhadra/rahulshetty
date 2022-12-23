package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productsList;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.xpath("//div[@class='card-body']/button[2]");
	By toastFactory = By.cssSelector("#toast-container");

	public List<WebElement> getProductsList() {

		waitForElementToAppear(productBy);
		return productsList;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream().filter(
				product -> product.findElement(By.xpath("//div[@class='card-body']//b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastFactory);

	}

}
