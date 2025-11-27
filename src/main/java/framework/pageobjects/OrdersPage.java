package framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.AbstractComponents;
import framework.abstractcomponents.DriverFactory;

import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;

public class OrdersPage extends AbstractComponents{
	
//	WebDriver driver;
	
	public OrdersPage() {
		
		
		PageFactory.initElements(DriverFactory.getDriver(), this);   //Tells PageFactory to find elements using driver and store in object of this class
	}
	
	@FindBy(css="tr[class='ng-star-inserted']")
	WebElement ordersList;
	
	@FindBy(css="tr[class='ng-star-inserted']")
	List<WebElement> orders;
	
	By orderedProductName = By.cssSelector("td:nth-child(3)");
	
	
	
	public boolean verifyOrderIsDisplayed(String productName) {
		

		
		waitForElementVisibility(ordersList);
		WebElement firstOrder = orders.stream()
									.findFirst()
									.orElseThrow(() -> new RuntimeException("No Orders Found"));
		
		boolean productFound = firstOrder.findElement(orderedProductName).getText().equals(productName);
				
		return productFound;
	}
	
	
	

}
