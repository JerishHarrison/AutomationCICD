package framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.AbstractComponents;
import framework.abstractcomponents.DriverFactory;

import java.util.List;

//import org.openqa.selenium.WebDriver;

public class CheckoutPage extends AbstractComponents{
	
//	WebDriver driver;
	Actions action;
	
	public CheckoutPage() {
		
		PageFactory.initElements(DriverFactory.getDriver(), this);   //Tells PageFactory to find elements using driver and store in object of this class
		action = new Actions(DriverFactory.getDriver());
	}
	
	
	
	@FindBy(css="input[placeholder*='Country']")
	WebElement countryAutoSuggestBox;
	
	@FindBy(css=".ta-results .ta-item span")
	WebElement autoSuggestResults;
	
	@FindBy(css=".ta-results .ta-item span")
	List<WebElement> countriesList;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	
	
	public String selectCountry(String requiredCountry) {
		
	
		action.sendKeys(countryAutoSuggestBox, requiredCountry).perform();  
		
		waitForElementVisibility(autoSuggestResults);
		
		WebElement foundCountry = countriesList.stream()
				.filter(country -> country.getText().equalsIgnoreCase(requiredCountry))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Country not found: " + requiredCountry));
		
		String selectedCountry =  foundCountry.getText();
		action.click(foundCountry).perform();
		
		return selectedCountry;
	}
	
	public ConfirmationPage placeOrder() {
		
		action.click(placeOrderButton).perform();
		return new ConfirmationPage();
	}
	

}
