
@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page
  
  
  
@tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username  <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name                      | password          | productName        |
      | rahuls@gmail.com          | Rahul@123         | ZARA COAT 3        |
     
