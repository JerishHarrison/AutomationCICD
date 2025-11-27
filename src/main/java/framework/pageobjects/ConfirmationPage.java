package framework.pageobjects;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.AbstractComponents;
import framework.abstractcomponents.DriverFactory;

//import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends AbstractComponents{
	
//	WebDriver driver;
	
	
	public ConfirmationPage() {
		
		
		PageFactory.initElements(DriverFactory.getDriver(), this);   //Tells PageFactory to find elements using driver and store in object of this class

	}
	
	
	
	@FindBy(className="hero-primary")
	WebElement confirmMessage;
	
	
	
	public String getConfirmation() {
		
		waitForElementVisibility(confirmMessage);
		return confirmMessage.getText();
		
	}
	
	
	

}
