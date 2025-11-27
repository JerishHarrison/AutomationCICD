package framework.stepdefenitions;

import java.io.IOException;

import org.testng.Assert;

import framework.pageobjects.CartPage;
import framework.pageobjects.CheckoutPage;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.LandingPage;
import framework.pageobjects.ProductCatalogue;
import framework.testcomponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinitionImplementation extends BaseTest {
	
	LandingPage landingPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationpage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		launchApplication();
		landingPage = startApp();
	}
	
	@Given("^Logged in with emailId (.+) and password (.+)$")
	public void Logged_in_with_username_password(String email, String password) {
		
		productCatalogue = landingPage.loginApplication(email, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productname) {
		
		productCatalogue.addProductToCart(productname);
	}
	
	@And("^Checkout (.+) and select (.+) and submit the order$")
	public void Checkout_product_and_submit_order(String productName, String country) {
		
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean productFound = cartPage.verifyProductIsDisplayed(productName);
		Assert.assertTrue(productFound);  //Assertions should not be in page object files
		
		CheckoutPage checkoutpage = cartPage.goToCheckoutPage();	
		String selectedCountry = checkoutpage.selectCountry(country);
		Assert.assertTrue(selectedCountry.equalsIgnoreCase(country));
	 
		confirmationpage = checkoutpage.placeOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void success_message_displayed_on_ConfirmationPage(String message) {
		
		String confirmMessage = confirmationpage.getConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));  
		
	}
	
	@Then("{string} message is displayed")
    public void something_message_is_displayed(String error) throws Throwable {
   
    	Assert.assertEquals(error, landingPage.getErrorMessage());
    	
    }	

}
