package framework.tests;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.pageobjects.CartPage;
import framework.pageobjects.LandingPage;
import framework.pageobjects.ProductCatalogue;
import framework.testcomponents.BaseTest;
import framework.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest{
	
	@Test (groups="Error Handling",retryAnalyzer = Retry.class)
	public void logInError() throws IOException{


		String wrongEmailId = "anything@gmail.com";
		String wrongPass = "something";
		
		LandingPage landingPage  = startApp();

		landingPage.loginApplication(wrongEmailId, wrongPass);

		
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
		
	}
	
	@Test (groups="Error Handling")
	public void productNotDisplayedInCartError() {
		
		String emailId = "jerishselenium@gmail.com";
		String pass = "JerishSelenium123";
		String requiredProduct = "ZARA COAT 3";  
		
		LandingPage landingPage  = startApp();
	
		ProductCatalogue productCatalogue = landingPage.loginApplication(emailId, pass);
		
		productCatalogue.addProductToCart(requiredProduct);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		boolean productFound = cartPage.verifyProductIsDisplayed("Wrong Product Name");
		Assert.assertFalse(productFound);
	}
	
	

}
