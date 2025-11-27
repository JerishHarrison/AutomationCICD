package framework.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.List;

import java.util.Properties;

import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import framework.abstractcomponents.DriverFactory;
import framework.pageobjects.LandingPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {


	public WebDriver initializeDriver() throws IOException {

		WebDriver driver = null;
		// Properties Class reads .properties files
		Properties prop = new Properties();

//		System.getProperty("user.dir") - stores directory of project
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\framework\\resources\\GlobalData.properties");
		prop.load(fis);
		
//		String browser;
//		if(System.getProperty("browser")!=null) {
//			browser = System.getProperty("browser");
//		}
//		
//		else {
//			browser = prop.getProperty("browser");
//		}
		
		//Above block can be wriiten using ternary oprator as follows
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if (browserName.toLowerCase().contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			
			if (browserName.contains("true")) {
				
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");        
//				options.addArguments("--start-maximized");      //Does not work for headless mode
				options.addArguments("--no-sandbox"); 
				options.addArguments("--disable-dev-shm-usage");  
				driver = new ChromeDriver(options);
			}   
			else {
				driver = new ChromeDriver();
				driver.manage().window().maximize();   //Do not do this for headless mode. Set window-size instead
			}
			
			

		} else if (browserName.toLowerCase().contains("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			
			if (browserName.contains("true")) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");        
				options.addArguments("--no-sandbox"); 
				options.addArguments("--disable-dev-shm-usage");  
				driver = new FirefoxDriver(options);
			}  
			else {
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}
			

		} else if (browserName.toLowerCase().contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			
			if (browserName.contains("true")) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");        
				options.addArguments("--no-sandbox"); 
				options.addArguments("--disable-dev-shm-usage");  
				driver = new EdgeDriver(options);
			} 
			else {
				driver = new EdgeDriver();
				driver.manage().window().maximize();
			}
			
		}

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//		driver.manage().window().setSize(new Dimension(2560,1600));
		return driver;
	}

	// Method to convert JSON File to List of HashMaps
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		// Convert JSON from file to one String
		String jsonData = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// Coverting JSON String to list of HashMaps using Jackson DataBind
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

//		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileHandler.copy(src, new File("D://myScreenshot.png"));

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		File screenshot = new File(screenshotPath);
		FileHandler.copy(srcFile, screenshot);

		return screenshotPath;

	}
	
	public LandingPage startApp() {
		LandingPage landingPage  = new LandingPage();
 		landingPage.goTo();
 		return landingPage;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {

		WebDriver driver = initializeDriver();
		DriverFactory.setDriver(driver);
	
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		DriverFactory.removeDriver();

	}

}
