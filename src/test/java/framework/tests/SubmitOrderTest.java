package framework.tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.pageobjects.CartPage;
import framework.pageobjects.CheckoutPage;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.LandingPage;
import framework.pageobjects.OrdersPage;
import framework.pageobjects.ProductCatalogue;
import framework.testcomponents.BaseTest;


public class SubmitOrderTest extends BaseTest{
	
	String emailId = "seleniumframework29@gmail.com";
	String pass = "29Feb2000";
	
	String requiredProduct = "ADIDAS ORIGINAL";
	
	@Test (dataProvider = "getData",groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException{
		
		LandingPage landingPage  = startApp();
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("emailId"), input.get("pass"));
		
		productCatalogue.addProductToCart(input.get("requiredProduct"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		boolean productFound = cartPage.verifyProductIsDisplayed(input.get("requiredProduct"));
		Assert.assertTrue(productFound);  //Assertions should not be in page object files
		CheckoutPage checkoutpage = cartPage.goToCheckoutPage();
		
		
		String selectedCountry = checkoutpage.selectCountry(input.get("requiredCountry"));
		Assert.assertTrue(selectedCountry.equalsIgnoreCase(input.get("requiredCountry")));
		ConfirmationPage confirmationpage = checkoutpage.placeOrder();
		
		String confirmMessage = confirmationpage.getConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));  
		
		
	}
	
	@Test(dependsOnMethods = "submitOrder")
	public void verifyOrderDisplay() {
		
		LandingPage landingPage  = startApp();
		ProductCatalogue productCatalogue = landingPage.loginApplication(emailId, pass);
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		boolean orderFound = ordersPage.verifyOrderIsDisplayed(requiredProduct);
		Assert.assertTrue(orderFound);
		
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][]  {{"seleniumframework29@gmail.com","29Feb2000","ADIDAS ORIGINAL","Canada"},
//								{"seleniumframework29@gmail.com","29Feb2000","ZARA COAT 3","India"}};
//		
//	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		HashMap<String,String> map= new HashMap<String,String>();
//		
//		map.put("emailId", "seleniumframework29@gmail.com");
//		map.put("pass", "29Feb2000");
//		map.put("requiredProduct", "ZARA COAT 3");
//		map.put("requiredCountry", "Canada");
//		
//		HashMap<String,String> map1= new HashMap<String,String>();
//		
//		map1.put("emailId", "seleniumframework29@gmail.com");
//		map1.put("pass", "29Feb2000");
//		map1.put("requiredProduct", "ADIDAS ORIGINAL");
//		map1.put("requiredCountry", "India");
//		
//		return new Object[][]  {{map},
//								{map1}};
//		
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		String jsonFilePath = System.getProperty("user.dir")+"\\src\\test\\java\\framework\\data\\PurchaseOrder.json";
		List<HashMap<String,String>> dataList = getJsonDataToMap(jsonFilePath);
		return new Object[][]  {{dataList.get(0)},
								{dataList.get(1)}};
		
	}
	
	


}
