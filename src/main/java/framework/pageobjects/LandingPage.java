package framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.AbstractComponents;
import framework.abstractcomponents.DriverFactory;

//import org.openqa.selenium.WebDriver;

public class LandingPage extends AbstractComponents {
	

	
	public LandingPage()  {
		
		
		PageFactory.initElements(DriverFactory.getDriver(), this);   //Tells PageFactory to find elements using driver and store in object of this class
	}
	
	
	
//	WebElement email = driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail") 	//Page Factory Design of above statement
	WebElement email;
	

	@FindBy(id="userPassword")  
	WebElement password;
	
	@FindBy(id="login")  
	WebElement loginButton;
	
	@FindBy(css="div[class*='toast-mess']")		
	WebElement errorToastMessage;
	
	public void goTo() {
			
		DriverFactory.getDriver().get("https://rahulshettyacademy.com/client");
		
	}
	
	public ProductCatalogue loginApplication(String userEmail, String userPassword) {
		
		
		
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		
		waitForElementClickable(loginButton);
		
		loginButton.click();
		
		
		
		ProductCatalogue productCatalogue = new ProductCatalogue();
		return productCatalogue;
		
//		return new ProductCatalogue(driver); //Above 2 lines can be written like this

	}
	
	public String getErrorMessage() {
		

		waitForElementVisibility(errorToastMessage);
		return errorToastMessage.getText();
		
	}

}
