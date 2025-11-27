
@tag
Feature: Purchase the order from Ecommerce Website

	Background:
	Given I landed on Ecommerce Page

  @SubmitOrderTest
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with emailId <email> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and select <country> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | email  								|  password		    |	productName   	  |   country		|
      | seleniumframework29@gmail.com 		|  29Feb2000   		|   ADIDAS ORIGINAL   |   Canada		| 