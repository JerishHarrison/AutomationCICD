package framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.AbstractComponents;
import framework.abstractcomponents.DriverFactory;

import java.util.List;

//import org.openqa.selenium.WebDriver;

public class CartPage extends AbstractComponents{
	
//	WebDriver driver;
	
	public CartPage() {
		
		PageFactory.initElements(DriverFactory.getDriver(), this);   //Tells PageFactory to find elements using driver and store in object of this class
	}
	
	@FindBy(css=".cartSection h3")
	WebElement cartProductList;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public boolean verifyProductIsDisplayed(String productName) {
		
		
		waitForElementVisibility(cartProductList);
		boolean productFound = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return productFound;
	}
	
	public CheckoutPage goToCheckoutPage() {
		
		
		checkoutButton.click();
		
		return new CheckoutPage();
		
//		CheckoutPage checkoutpage = new CheckoutPage(driver);
//		return checkoutpage;
		
	}
	

}
