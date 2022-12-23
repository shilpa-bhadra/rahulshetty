package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class Standalone {

	public static void main(String[] args) throws InterruptedException {
String item ="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		LandingPage lp = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("rahuls@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@123");

		driver.findElement(By.id("login")).click();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(12));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product -> product
				.findElement(By.xpath("//div[@class='card-body']//b")).getText().equals(item)).findFirst()
				.orElse(null);
		prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
Thread.sleep(7000);
		
	driver.findElement(By.xpath("//*[@routerlink='/dashboard/cart']")).click();
	List<WebElement> elementsInCart =driver.findElements(By.xpath("//div[@class='cartSection']/h3"));	
	
	Boolean match =elementsInCart.stream().anyMatch(s->s.getText().equalsIgnoreCase(item));
	Assert.assertTrue(match);
driver.findElement(By.xpath("//*[@class='totalRow']/button")).click();
driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
driver.findElement(By.xpath("//*[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
Thread.sleep(9000);
driver.findElement(By.xpath("//div[@class='actions']/a")).click();
Thread.sleep(9000);
String successMessage =driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
Assert.assertTrue(successMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
