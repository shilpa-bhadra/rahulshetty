package rahulshettyacademy.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	public  WebDriver initializeTheDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fip);
		String browserName =prop.getProperty("browser");
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			//edge
		}	
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			//firefox
		}
		else if(browserName.equalsIgnoreCase("opera")) {
			//opera
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJSonDataToMap(String filePath) throws IOException {

		String jsonContent =FileUtils.readFileToString(new File(
		filePath),
				StandardCharsets.UTF_8);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data =	objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
	});
		return data;
		
		
		
//List<HashMap<String, String>> data =objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>);

	}
	
	
public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {

TakesScreenshot ts = (TakesScreenshot) driver;
File source =ts.getScreenshotAs(OutputType.FILE);
File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
FileUtils.copyFile(source, file);
return System.getProperty("user.dir")+"//reports//"+ testCaseName +".png";
}	
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
	driver =initializeTheDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		
		
		driver.close();
	}
}
