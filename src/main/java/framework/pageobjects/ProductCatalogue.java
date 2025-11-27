package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.AbstractComponents;
import framework.abstractcomponents.DriverFactory;

public class ProductCatalogue extends AbstractComponents{
	
//	WebDriver driver;
	
	public ProductCatalogue() {
		
		
		PageFactory.initElements(DriverFactory.getDriver(), this);   //Tells PageFactory to find elements using driver and store in object of this class
	}
	
	@FindBy(className="mb-3")
	WebElement productCard;
	
//	List<WebElement> productCards = driver.findElements(By.className("mb-3"));
	@FindBy(className="mb-3")
	List<WebElement> productCards;
	
	@FindBy(id="toast-container")
	WebElement toast;
	
	@FindBy(className="ng-animating")
	WebElement loadingIcon;
	
	
	By addToCartButton = By.cssSelector("button:last-of-type");  // ****Cannot declare Locators using PageFactory!!
	
	
	public List<WebElement> getProductList() {
	
		waitForElementVisibility(productCard);
		return productCards;
	}
	
	public WebElement getProductByName(String productName) {
		
	
		WebElement foundProduct = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("div.card-body b")).getText().equalsIgnoreCase(productName))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Product not found: " + productName));
		
		return foundProduct;

	}
	
	public void addProductToCart(String productName) {
		
	
		WebElement foundProduct = getProductByName(productName);
		foundProduct.findElement(addToCartButton).click();
		
		waitForElementVisibility(toast);	//Toast message
		waitForElementInvisibility(loadingIcon);	//Loading Icon
		
//		waitForElementInvisibility(toast);  //Can Wait Until Toast disappears instead of above 2 waits
		
	}

	
	
	
}
