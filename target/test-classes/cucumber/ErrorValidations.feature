@tag
Feature: Error validation


  @ErrorValidation
  Scenario Outline: Incorrect E-Mail or Password Login 
    
    Given I landed on Ecommerce Page
    When Logged in with emailId <email> and password <password>
    Then "Incorrect email or password." message is displayed

     Examples: 
      | email  			     |    password		  |
      | anything@gmail.com   |    something       | 