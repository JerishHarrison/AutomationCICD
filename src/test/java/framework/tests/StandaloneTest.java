package framework.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandaloneTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		String emailId = "seleniumframework29@gmail.com";
		String password = "29Feb2000";
		driver.findElement(By.id("userEmail")).sendKeys(emailId);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		
		//Above code using pageObject Model
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
//		landingPage.loginApplication(emailId, pass);

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
		
		String requiredProduct = "ZARA COAT 3";
		List<WebElement> productCards = driver.findElements(By.className("mb-3"));
		
		WebElement foundProduct = productCards.stream()
									.filter(product -> product.findElement(By.cssSelector("div.card-body b")).getText().equalsIgnoreCase(requiredProduct))
									.findFirst()
									.orElse(null);
		
		foundProduct.findElement(By.cssSelector("button:last-of-type")).click();
//		foundProduct.findElement(By.cssSelector("button.w-10")).click();
		
		
		//Toast message
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		//Loading Icon
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean productFound = cartProducts.stream()
									.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(requiredProduct));
		
		Assert.assertTrue(productFound);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//********Selecting Country Autosuggestive dropdown NORMAL Version
		
//		driver.findElement(By.cssSelector("input[placeholder*='Country']")).sendKeys("can");
//		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results .ta-item span"))));
//		
//		String requiredCountry = "Canada";
//		List<WebElement> countriesList = driver.findElements(By.cssSelector(".ta-results .ta-item span"));
//		
//		WebElement foundCountry = countriesList.stream()
//									.filter(country -> country.getText().equalsIgnoreCase(requiredCountry))
//									.findFirst()
//									.orElseThrow(() -> new RuntimeException("Country not found: " + requiredCountry));
//		
//		Assert.assertTrue(foundCountry.getText().equalsIgnoreCase(requiredCountry));
//		foundCountry.click();
//		
//		driver.findElement(By.cssSelector(".action__submit")).click();
		
		
		//*******Selecting Country Autosuggestive dropdown using ACTIONS CLASS
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder*='Country']")), "can").perform();   //Have to specify .perform()!!!!
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results .ta-item span"))));
		
		String requiredCountry = "Canada";
		List<WebElement> countriesList = driver.findElements(By.cssSelector(".ta-results .ta-item span"));
		
		WebElement foundCountry = countriesList.stream()
									.filter(country -> country.getText().equalsIgnoreCase(requiredCountry))
									.findFirst()
									.orElseThrow(() -> new RuntimeException("Country not found: " + requiredCountry));
		
		Assert.assertTrue(foundCountry.getText().equalsIgnoreCase(requiredCountry));
		
		action.click(foundCountry).perform();
		action.click(driver.findElement(By.cssSelector(".action__submit"))).perform();
		
		String confirmMessage = driver.findElement(By.className("hero-primary")).getText();  //Gets Text with format from browser not html doc!!!
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));  
		
		driver.quit();
		
	}

}
