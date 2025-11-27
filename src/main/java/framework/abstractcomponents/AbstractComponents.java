package framework.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.pageobjects.CartPage;
import framework.pageobjects.OrdersPage;


public class AbstractComponents {
	
	WebDriverWait wait;
	
	
	
	public AbstractComponents() {
		
		
		wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(5));
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement headerCartButton;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement headerOrdersButton;

	public void waitForElementVisibility(WebElement element) {
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisibility(WebElement element) {
		
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementClickable(WebElement element) {
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public CartPage goToCartPage() {
		
		waitForElementVisibility(headerCartButton);
		headerCartButton.click();
		
		return new CartPage();
		
		//Above line expanded
//		CartPage cartPage = new CartPage(driver);
//		return cartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		
		waitForElementVisibility(headerOrdersButton);
		headerOrdersButton.click();
		
		return new OrdersPage();
		

	}
	
	
}
